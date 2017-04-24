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
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Belize
 */
@WebServlet(name = "ShowAds", urlPatterns = {"/ShowAds"})
public class ShowAds extends HttpServlet {

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

            String sql1 = "SELECT role_ads FROM advertised WHERE Ads_id = ?;";
            PreparedStatement stm1 = conn.prepareStatement(sql1);
            stm1.setInt(1, id);
            ResultSet rs = stm1.executeQuery();

            boolean show = false;

            if (rs.next()) {
                show = rs.getBoolean("role_ads");
            }

            String sql = "UPDATE advertised SET role_ads = ? WHERE Ads_id = ?;";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setBoolean(1, show ? false : true);
            stm.setInt(2, id);

            int row = stm.executeUpdate();

            if (row > 0) {
                response.sendRedirect("/RETS/menu?tab=ads");
            } else {
                response.sendRedirect("/RETS/menu?tab=ads&error");
            }

        } catch (IOException | SQLException e) {
            response.sendRedirect("/RETS/error.jsp");
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
