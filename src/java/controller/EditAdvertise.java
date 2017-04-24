/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.Advertise;
import model.Employee;
import model.ImageMeta;
import model.Residential;

/**
 *
 * @author dell
 */
@WebServlet(name = "EditAdvertise", urlPatterns = {"/EditAdvertise"})
public class EditAdvertise extends HttpServlet {

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
            String topic = request.getParameter("title");
            String detail = request.getParameter("detail");
            String res_id = request.getParameter("res_id");

            Advertise ads = (Advertise) request.getSession().getAttribute("employee");

            Connection connection = (Connection) getServletContext().getAttribute("connection");
            Employee emp = (Employee) request.getSession().getAttribute("employee");

            String sql = "UPDATE advertised SET topic = ?, detail = ?, Res_id = ?, Emp_num = ? WHERE Ads_id = ?;";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, !topic.equals("") ? topic : ads.getTopic());
            stm.setString(2, !detail.equals("") ? detail : ads.getDetail());
            stm.setString(3, !res_id.equals("") ? res_id : ads.getRes_id());
            stm.setInt(4, emp.getNumber());

            int row = stm.executeUpdate();

            if (row > 0) {
                ads.setTopic(!topic.equals("") ? topic : ads.getTopic());
                ads.setDetail(!detail.equals("") ? detail : ads.getDetail());
                ads.setRes_id(!res_id.equals("") ? res_id : ads.getRes_id());

                response.sendRedirect("/RETS/index");
            } else {
                response.sendRedirect("/RETS/edit_profile?error=");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/RETS/edit_profile?error=");
        }
        try {
            String ads_id = request.getParameter("ads_id");

            // query and set value to instance
            Connection conn = (Connection) getServletContext().getAttribute("connection");

            // residetial table
            String sql = "SELECT image, topic, detail, res_id from advertised WHERE ads_id = ?;";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(ads_id));
            ResultSet rs = stm.executeQuery();

            if (rs.next()) { // create instance and push to session for editting                
                Residential res = new Residential();
                Advertise ads = new Advertise();
                
                ads.setEdit(true);

                

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
