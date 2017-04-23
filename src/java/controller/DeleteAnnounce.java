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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Belize
 */
@WebServlet(name = "DeleteAnnounce", urlPatterns = {"/DeleteAnnounce"})
public class DeleteAnnounce extends HttpServlet {

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

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Connection conn = (Connection) getServletContext().getAttribute("connection");

            String sql1 = "DELETE FROM `image of detail` WHERE Res_id = ?";
            PreparedStatement stm1 = conn.prepareCall(sql1);
            stm1.setInt(1, id);
            int row1 = stm1.executeUpdate();

            String sql2 = "DELETE FROM `details` WHERE Res_id = ?";
            PreparedStatement stm2 = conn.prepareCall(sql2);
            stm2.setInt(1, id);
            int row2 = stm2.executeUpdate();

            String sql3 = "DELETE FROM `residential` WHERE Res_id = ?";
            PreparedStatement stm3 = conn.prepareCall(sql3);
            stm3.setInt(1, id);
            int row3 = stm3.executeUpdate();

            if (row1 > 0 && row2 > 0 && row3 > 0) {
                response.sendRedirect("/RETS/menu?tab=announce");
            } else {
                response.sendRedirect("/RETS/error");
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
