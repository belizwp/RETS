/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
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
@WebServlet(name = "Contact", urlPatterns = {"/Contact"})
public class Contact extends HttpServlet {

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
            String emp_num = request.getParameter("emp_num");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String desc = request.getParameter("desc");

            Connection conn = (Connection) getServletContext().getAttribute("connection");

            String sql1 = "INSERT INTO customer (Fname, Lname, phone, email) VALUES (?, ?, ?, ?)";
            String sql2 = "SET @cus_id := LAST_INSERT_ID();";
            String sql3 = "INSERT INTO contact (Cus_id, Emp_num, cont_desc) VALUES (@cus_id, ?, ?)";

            PreparedStatement stm1 = conn.prepareStatement(sql1);
            stm1.setString(1, fname);
            stm1.setString(2, lname);
            stm1.setString(3, phone);
            stm1.setString(4, email);
            stm1.executeUpdate();

            PreparedStatement stm2 = conn.prepareStatement(sql2);
            stm2.executeQuery();

            PreparedStatement stm3 = conn.prepareStatement(sql3);
            stm3.setInt(1, Integer.parseInt(emp_num));
            stm3.setString(2, desc);
            int row = stm3.executeUpdate();

            if (row > 0) {
                response.sendRedirect("/RETS/residential?" + request.getQueryString() + "&contact=succeed");
            } else {
                response.sendRedirect("/RETS/residential?" + request.getQueryString() + "&contact=failed");
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
