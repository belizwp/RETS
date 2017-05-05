/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Employee;
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
            try {
                switch (process) {
                    case "basic":
                        saveBasicVal(request, announce);
                        break;
                    case "detail":
                        saveDetailVal(request, announce);
                        break;
                }
                updateAnnounce(announce, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("/RETS/error");
            }
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
    private void updateAnnounce(Residential res, HttpServletResponse response) throws SQLException, IOException {
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        res.update(connection);
        response.sendRedirect("/RETS/menu?tab=announce");

    }

    // create a new record of announce
    private void announce(Residential ann, Employee emp, HttpServletResponse response) throws SQLException, IOException {
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        int row = ann.create(connection, emp);
        if (row > 0) {
            response.sendRedirect("/RETS/menu?tab=announce");
        }
    }

    //  generate by current epoch hex time combine with 4 first session_id
    public static String genProcessID(HttpServletRequest request) {
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
        ann.setFloor(request.getParameter("floor") != null ? request.getParameter("floor") : null);
        ann.setElectricity(request.getParameter("electricity") != null ? request.getParameter("electricity") : null);
        ann.setWater(request.getParameter("water") != null ? request.getParameter("water") : null);
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
