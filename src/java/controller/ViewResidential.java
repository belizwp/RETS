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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;
import model.Residential;

/**
 *
 * @author Belize
 */
@WebServlet(name = "ViewResidential", urlPatterns = {"/residential"})
public class ViewResidential extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            String id = request.getParameter("id");

            // query and set value to instance
            Connection conn = (Connection) getServletContext().getAttribute("connection");

            // residetial table
            String sql = "SELECT *\n"
                    + "FROM residential\n"
                    + "NATURAL JOIN employees\n"
                    + "NATURAL JOIN details\n"
                    + "NATURAL JOIN location\n"
                    + "NATURAL JOIN province\n"
                    + "NATURAL JOIN amphur\n"
                    + "NATURAL JOIN district\n"
                    + "WHERE Res_id = ?;";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(id));
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                Residential res = new Residential();
                Employee emp = new Employee();
                
                emp.setNumber(rs.getInt("Emp_num"));
                emp.setFname(rs.getString("Fname"));
                emp.setLname(rs.getString("Lname"));
                emp.setPhone(rs.getString("phone"));
                
                res.setId(Integer.parseInt(id));
                
                res.setType(rs.getString("announce_for"));
                res.setPropType(rs.getString("types"));
                res.setProvince(rs.getShort("province_id"));
                res.setAmphur(rs.getShort("amphur_id"));
                res.setDistrict(rs.getShort("district_id"));
                
                res.setProvinceName(rs.getString("province_name"));
                res.setAmphurName(rs.getString("amphur_name"));
                res.setDistrictName(rs.getString("district_name"));
                
                res.setTitle(rs.getString("Res_name"));
                res.setDetail(rs.getString("remark"));
                res.setName(rs.getString("buliding_name"));
                res.setAddress(rs.getString("address"));
                
                res.setPrice(rs.getInt("price"));
                
                res.setFloor(rs.getString("floor"));
                res.setElectricity(rs.getString("electric_bill"));
                res.setWater(rs.getString("water_bill"));
                res.setFacilities(rs.getString("facilities"));
                
                res.setDt_time(rs.getString("dt_modified"));

                // forwoard instance through req attribute
                request.setAttribute("residential", res);
                request.setAttribute("emp_info", emp);
                request.getRequestDispatcher("/residential.jsp").forward(request, response);
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
