/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ImageMeta;
import model.Residential;

/**
 *
 * @author Belize
 */
@WebServlet(name = "EditAnnounce", urlPatterns = {"/edit_announce"})
public class EditAnnounce extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        try {
            String id = request.getParameter("id");

            // query and set value to instance
            Connection conn = (Connection) getServletContext().getAttribute("connection");

            // residetial table
            String sql = "SELECT *\n"
                    + "FROM residential\n"
                    + "JOIN owner ON (residential.Owner_Own_id = owner.Own_id)\n"
                    + "NATURAL JOIN details\n"
                    + "NATURAL JOIN location\n"
                    + "WHERE Res_id = ?;";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(id));
            ResultSet rs = stm.executeQuery();

            if (rs.next()) { // create instance and push to session for editting                
                Residential res = new Residential();

                res.setEdit(true);

                res.setId(Integer.parseInt(id));
                res.setEmp_num(rs.getInt("Emp_num"));
                res.setOwner_id(rs.getInt("Owner_Own_id"));
                res.setLoc_id(rs.getInt("Loc_id"));
                res.setDetails_id(rs.getInt("details_id"));

                res.setType(rs.getString("announce_for"));
                res.setPropType(rs.getString("types"));
                res.setProvince(rs.getShort("province_id"));
                res.setAmphur(rs.getShort("amphur_id"));
                res.setDistrict(rs.getShort("district_id"));

                res.setTitle(rs.getString("Res_name"));
                res.setDetail(rs.getString("remark"));
                res.setName(rs.getString("buliding_name"));
                res.setAddress(rs.getString("address"));

                res.setPrice(rs.getInt("price"));

                res.setFname(rs.getString("Fname"));
                res.setLname(rs.getString("Lname"));
                res.setPhone(rs.getString("phone"));
                res.setEmail(rs.getString("email"));

                res.setFloor(rs.getString("floor"));
                res.setElectricity(rs.getString("electric_bill"));
                res.setWater(rs.getString("water_bill"));
                res.setFacilities(rs.getString("facilities"));

                String imageSQL = "SELECT image from `image of detail` WHERE Res_id = ?;";
                PreparedStatement imgSTM = conn.prepareStatement(imageSQL);
                imgSTM.setInt(1, res.getId());
                ResultSet imgRS = imgSTM.executeQuery();

                List<ImageMeta> files = res.getFiles();
                ImageMeta temp = null;
                while (imgRS.next()) {
                    temp = new ImageMeta();
                    temp.setImg(ImageIO.read(imgRS.getBinaryStream("image")));
                    files.add(temp);
                }

                HttpSession session = request.getSession(false);

                String process_id = AnnounceCreator.genProcessID(request);
                session.setAttribute(process_id, res);

                response.sendRedirect("NewAnnounce?process_id=" + process_id);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/RETS/error");
        }

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
