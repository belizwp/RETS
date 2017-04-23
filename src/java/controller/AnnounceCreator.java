/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Employee;
import model.ImageMeta;
import model.Residential;

/**
 *
 * @author Belize
 */
@WebServlet(name = "NewAnnounce", urlPatterns = {"/NewAnnounce"})
@MultipartConfig
public class AnnounceCreator extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String process_id = request.getParameter("process_id");
        String process = request.getParameter("process");
        String submit = request.getParameter("submit");

        if (process_id == null) {
            newAnnounce(request, response);
        } else {
            changeProcess(request, response, process_id, process, submit);
        }

    }

    private void newAnnounce(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        Residential announce = new Residential();
        String process_id = genProcessID(request);
        session.setAttribute(process_id, announce);

        response.sendRedirect("NewAnnounce?process_id=" + process_id);
    }

    private void changeProcess(HttpServletRequest request, HttpServletResponse response,
            String process_id, String process, String submit)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Residential announce = (Residential) session.getAttribute(process_id);
        Employee emp = (Employee) session.getAttribute("employee");

        if (process == null) {
            response.sendRedirect("basic?process_id=" + process_id);
        } else if (submit.equals("ถัดไป")) {
            switch (process) {
                case "basic":
                    saveBasicVal(request, announce);
                    response.sendRedirect("detail?process_id=" + process_id);
                    break;
                case "detail":
                    saveDetailVal(request, announce);
                    response.sendRedirect("media?process_id=" + process_id);
                    break;
                case "media":
                    response.sendRedirect("summary?process_id=" + process_id);
                    break;
                default:
                    response.sendRedirect("basic?process_id=" + process_id);
                    break;
            }

        } else if (submit.equals("กลับ")) {
            switch (process) {
                case "detail":
                    saveDetailVal(request, announce);
                    response.sendRedirect("basic?process_id=" + process_id);
                    break;
                case "media":
                    response.sendRedirect("detail?process_id=" + process_id);
                    break;
                case "summary":
                    response.sendRedirect("media?process_id=" + process_id);
                    break;
                default:
                    response.sendRedirect("basic?process_id=" + process_id);
                    break;
            }
        } else if (submit.equals("ข้อมูลทั่วไป") || submit.equals("รายละเอียดเพิ่มเติม") || submit.equals("รูปภาพ") || submit.equals("สรุป")) {

            if (process.equals("basic")) {
                saveBasicVal(request, announce);
            } else if (process.equals("detail")) {
                saveDetailVal(request, announce);
            }

            switch (submit) {
                case "ข้อมูลทั่วไป":
                    response.sendRedirect("basic?process_id=" + process_id);
                    break;
                case "รายละเอียดเพิ่มเติม":
                    response.sendRedirect("detail?process_id=" + process_id);
                    break;
                case "รูปภาพ":
                    response.sendRedirect("media?process_id=" + process_id);
                    break;
                case "สรุป":
                    response.sendRedirect("summary?process_id=" + process_id);
                    break;
                default:
                    response.sendRedirect("basic?process_id=" + process_id);
                    break;
            }

        } else if (submit.equals("บันทึก")) {
            updateAnnounce(announce);
        } else if (submit.equals("ลงประกาศ")) {
            try {
                announce(announce, emp, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("/RETS/error");
            }

        }
    }

    // update announce record
    private void updateAnnounce(Residential announce) {

    }

    // create a new record of announce
    private void announce(Residential ann, Employee emp, HttpServletResponse response) throws SQLException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connection");

        String sql1 = "INSERT INTO owner(Fname, Lname, email, phone) VALUES(?, ?, ?, ?);";
        String sql2 = "SET @owner_id := LAST_INSERT_ID();";
        String sql3 = "INSERT INTO location(address, province_id, amphur_id, district_id) VALUES(?, ?, ?, ?);";
        String sql4 = "SET @loc_id := LAST_INSERT_ID();";
        String sql5 = "INSERT INTO residential(Res_name, announce_for, Emp_num, Owner_Own_id, Loc_id) VALUES(?, ?, ?, @owner_id, @loc_id);";
        String sql6 = "SET @res_id := LAST_INSERT_ID();";
        String sql7 = "INSERT INTO details(buliding_name, types, floor, price, water_bill, electric_bill, facilities, remark, Res_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, @res_id);";
        String sql8 = "SET @detail_id := LAST_INSERT_ID();";
        String sql9 = "INSERT INTO `image of detail`(image, details_id, Res_id) VALUES (?, @detail_id, @res_id);";

        PreparedStatement stm1 = connection.prepareCall(sql1);
        // owner info
        stm1.setString(1, ann.getFname());
        stm1.setString(2, ann.getLname());
        stm1.setString(3, ann.getEmail());
        stm1.setString(4, ann.getPhone());
        stm1.executeUpdate();

        // owner id
        PreparedStatement stm2 = connection.prepareCall(sql2);
        stm2.executeQuery();

        PreparedStatement stm3 = connection.prepareCall(sql3);
        // location info
        stm3.setString(1, ann.getAddress());
        stm3.setInt(2, ann.getProvince());
        stm3.setInt(3, ann.getAmphur());
        stm3.setInt(4, ann.getDistrict());
        stm3.executeUpdate();

        // loc id
        PreparedStatement stm4 = connection.prepareCall(sql4);
        stm4.executeQuery();

        PreparedStatement stm5 = connection.prepareCall(sql5);
        // resident info
        stm5.setString(1, ann.getTitle());
        stm5.setString(2, ann.getType());
        stm5.setInt(3, emp.getNumber());
        stm5.executeUpdate();

        // res id
        PreparedStatement stm6 = connection.prepareCall(sql6);
        stm6.executeQuery();

        PreparedStatement stm7 = connection.prepareCall(sql7);
        // detail info
        stm7.setString(1, ann.getName());
        stm7.setString(2, ann.getPropType());
        stm7.setString(3, ann.getFloor() + "");
        stm7.setInt(4, ann.getPrice());
        stm7.setString(5, ann.getWater() + "");
        stm7.setString(6, ann.getElectricity() + "");
        stm7.setString(7, ann.getFacilities());
        stm7.setString(8, ann.getDetail());
        int row = stm7.executeUpdate();

        // detail id
        PreparedStatement stm8 = connection.prepareCall(sql8);
        stm8.executeQuery();

        // image file
        PreparedStatement stm9 = connection.prepareCall(sql9);

        LinkedList<ImageMeta> files = ann.getFiles();

        int i = 0;

        for (ImageMeta file : files) {
            stm9.setBlob(1, file.getInputStream());

            stm9.addBatch();
            i++;

            if (i % 100 == 0 || i == files.size()) {
                stm9.executeBatch(); // Execute every 100 items.
            }
        }

        if (row > 0) {
            response.sendRedirect("/RETS/menu?tab=announce");
        }
    }

    //  generate by current epoch hex time combine with 4 first session_id
    private String genProcessID(HttpServletRequest request) {
        return Long.toHexString(System.currentTimeMillis() / 1000L) + request.getSession().getId().substring(0, 4);
    }

    private void saveBasicVal(HttpServletRequest request, Residential ann) {
        ann.setType(request.getParameter("type") != null ? request.getParameter("type") : null);
        ann.setPropType(request.getParameter("propType") != null ? request.getParameter("propType") : null);
        ann.setProvince(request.getParameter("province") != null ? Short.parseShort(request.getParameter("province")) : null);
        ann.setAmphur(request.getParameter("amphur") != null ? Short.parseShort(request.getParameter("amphur")) : null);
        ann.setDistrict(request.getParameter("district") != null ? Short.parseShort(request.getParameter("district")) : null);

        ann.setTitle(request.getParameter("title") != null ? request.getParameter("title") : null);
        ann.setDetail(request.getParameter("detail") != null ? request.getParameter("detail") : null);
        ann.setName(request.getParameter("name") != null ? request.getParameter("name") : null);
        ann.setAddress(request.getParameter("address") != null ? request.getParameter("address") : null);

        ann.setFname(request.getParameter("first_name") != null ? request.getParameter("first_name") : null);
        ann.setLname(request.getParameter("last_name") != null ? request.getParameter("last_name") : null);
        ann.setPhone(request.getParameter("phone") != null ? request.getParameter("phone") : null);
        ann.setEmail(request.getParameter("email") != null ? request.getParameter("email") : null);

        ann.setPrice(!request.getParameter("price").equals("") ? Integer.parseInt(request.getParameter("price")) : null);
    }

    private void saveDetailVal(HttpServletRequest request, Residential ann) {
        ann.setFloor(!request.getParameter("floor").equals("") ? Short.parseShort(request.getParameter("floor")) : null);
        ann.setElectricity(!request.getParameter("electricity").equals("") ? Integer.parseInt(request.getParameter("electricity")) : null);
        ann.setWater(!request.getParameter("water").equals("") ? Integer.parseInt(request.getParameter("water")) : null);
        ann.setFacilities(request.getParameter("facilities") != null ? request.getParameter("facilities") : null);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
