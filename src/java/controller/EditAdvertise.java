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
import model.Advertise;
import model.Employee;

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
            String title = request.getParameter("title");
            String detail = request.getParameter("detail");
            String res_id = request.getParameter("res_id");
            
            Advertise ads = (Advertise) request.getSession().getAttribute("employee");
            
            Connection connection = (Connection) getServletContext().getAttribute("connection");
            Employee emp = (Employee) request.getSession().getAttribute("employee");
            
            String sql = "UPDATE advertised SET topic = ?, detail = ?, Res_id = ?, Emp_num = ? WHERE Ads_id = ?;";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, !topic.equals("") ? topic : ads.gettopic());
            stm.setString(2, !detail.equals("") ? detail : ads.getdetail());
            stm.setString(3, !res_id.equals("") ? res_id : ads.res_id());
            stm.setInt(4, emp.getNumber());

            int row = stm.executeUpdate();

            if (row > 0) {
                emp.setFname(!fname.equals("") ? fname : emp.getFname());
                emp.setLname(!lname.equals("") ? lname : emp.getLname());
                emp.setPhone(!phone.equals("") ? phone : emp.getPhone());

                response.sendRedirect("/RETS/index");
            } else {
                response.sendRedirect("/RETS/edit_profile?error=");
            }
    }
    catch(Exception e) {
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
