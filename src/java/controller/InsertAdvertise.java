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
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Advertise;
import model.Employee;

/**
 *
 * @author dell
 */
@WebServlet(name = "InsertAdvertise", urlPatterns = {"/InsertAdvertise"})
@MultipartConfig
public class InsertAdvertise extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        Employee emp = (Employee) request.getSession().getAttribute("employee");

        String submit = request.getParameter("submit");

        if (submit.equals("นำเสนอ")) {
            try {
                String topic = request.getParameter("title");
                String detail = request.getParameter("detail");
                int res_id = Integer.parseInt(request.getParameter("res_id"));
                Part part = request.getPart("ads_img");

                Advertise ads = new Advertise(topic, detail, part, res_id);

                int row = ads.present(connection, emp.getNumber());

                if (row > 0) {
                    response.sendRedirect("/RETS/menu?tab=ads");
                } else {
                    response.sendRedirect("/RETS/menu?tab=ads&error");
                }

            } catch (IOException | SQLException e) {
                response.sendRedirect("/RETS/error.jsp");
            }
        } else if (submit.equals("บันทึก")) {
            try {
                int ads_id = Integer.parseInt(request.getParameter("ads_id"));
                String topic = request.getParameter("title");
                String detail = request.getParameter("detail");
                int res_id = Integer.parseInt(request.getParameter("res_id"));
                Part part = request.getPart("ads_img");

                Advertise ads = new Advertise(topic, detail, part, res_id);

                int row = ads.update(connection, ads_id);

                if (row > 0) {
                    response.sendRedirect("/RETS/menu?tab=ads");
                } else {
                    response.sendRedirect("/RETS/menu?tab=ads&error");
                }

            } catch (IOException | SQLException e) {
                response.sendRedirect("/RETS/error.jsp");
            }

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
