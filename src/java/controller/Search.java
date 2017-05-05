/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Residential;
import model.SearchDAO;

/**
 *
 * @author Belize
 */
@WebServlet(name = "Search", urlPatterns = {"/search"})
public class Search extends HttpServlet {

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
            String province = request.getParameter("province");
            String amphur = request.getParameter("amphur");
            String district = request.getParameter("district");
            String ann = request.getParameter("announce_type");
            String type = request.getParameter("property_type");
            String min = request.getParameter("min_price");
            String max = request.getParameter("max_price");
            String page = request.getParameter("page");

            Connection conn = (Connection) getServletContext().getAttribute("connection");

            SearchDAO searchDao = new SearchDAO();

            searchDao.setP_id(province != null && !province.equals("") ? Integer.parseInt(province) : null);
            searchDao.setA_id(amphur != null && !amphur.equals("") ? Integer.parseInt(amphur) : null);
            searchDao.setD_id(district != null && !district.equals("") ? Integer.parseInt(district) : null);
            searchDao.setAnn(ann);
            searchDao.setType(type);
            searchDao.setMin(min != null && !min.equals("") ? Integer.parseInt(min) : null);
            searchDao.setMax(max != null && !max.equals("") ? Integer.parseInt(max) : null);

            searchDao.setPage(page != null && !page.equals("") ? Integer.parseInt(page) : null);

            searchDao.setLimit(5); // default

            LinkedList<Residential> result = searchDao.search(conn);

            request.setAttribute("found_rows", searchDao.getFound_rows());
            request.setAttribute("limit", searchDao.getLimit());
            request.setAttribute("page", searchDao.getPage());
            request.setAttribute("result", result);
            request.getRequestDispatcher("result.jsp").forward(request, response);

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
