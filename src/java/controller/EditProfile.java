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
import model.Employee;

/**
 *
 * @author Belize
 */
@WebServlet(name = "EditProfile", urlPatterns = {"/EditProfile"})
public class EditProfile extends HttpServlet {

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
            String fname = request.getParameter("first_name");
            String lname = request.getParameter("last_name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            String new_password = request.getParameter("new_password");
            String new_password_confirmation = request.getParameter("new_password_confirmation");

            Employee emp = (Employee) request.getSession().getAttribute("employee");

            if (password.equals(emp.getPassword()) && new_password.equals(new_password_confirmation)) {
                Connection connection = (Connection) getServletContext().getAttribute("connection");

                String sql = "UPDATE employees SET fname = ?, lname = ?, phone = ?, email = ?, password = ? WHERE Emp_num = ?;";

                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, !fname.equals("") ? fname : emp.getFname());
                stm.setString(2, !lname.equals("") ? lname : emp.getLname());
                stm.setString(3, !phone.equals("") ? phone : emp.getPhone());
                stm.setString(4, !email.equals("") ? email : emp.getEmail());
                stm.setString(5, !new_password.equals("") ? new_password : emp.getPassword());
                stm.setInt(6, emp.getNumber());

                int row = stm.executeUpdate();

                if (row > 0) {
                    emp.setFname(!fname.equals("") ? fname : emp.getFname());
                    emp.setLname(!lname.equals("") ? lname : emp.getLname());
                    emp.setPhone(!phone.equals("") ? phone : emp.getPhone());
                    emp.setEmail(!email.equals("") ? email : emp.getEmail());
                    emp.setPassword(!new_password.equals("") ? new_password : emp.getPassword());

                    response.sendRedirect("/RETS/edit_profile?success=");
                } else {
                    response.sendRedirect("/RETS/edit_profile?error=");
                }

            } else {
                // no confirmation password
                response.sendRedirect("/RETS/edit_profile?error=");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/RETS/edit_profile?error=");
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
