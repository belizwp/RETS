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
import javax.servlet.http.HttpSession;
import model.Employee;
import model.Information;


/**
 *
 * @author dell
 */
@WebServlet(name = "CreateInformation", urlPatterns = {"/CreateInformation"})
public class CreateInformation extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String res_id = request.getParameter("res_id");

            Connection connection = (Connection) getServletContext().getAttribute("connection");

            String sql = "select ad.image, ad.topic, ad.present_date, r.Res_name, r.announce_for, d.buliding_name, d.types,\n"
                    + "d.floor, d.price, d.water_bill, d.electric_bill, d.facilities,\n"
                    + "d.remark, l.address, p.province_name, a.amphur_name, dis.district_name\n"
                    + "from advertised ad\n"
                    + "join residential r\n"
                    + "on (ad.`Res_id` = r.`Res_id`)\n"
                    + "join details d\n"
                    + "on (r.`Res_id` = d.`Res_id`)\n"
                    + "join location l\n"
                    + "on (r.`Loc_id` = l.`Loc_id`)\n"
                    + "join province p\n"
                    + "on (l.province_id = p.province_id)\n"
                    + "join amphur a \n"
                    + "on (p.province_id = a.province_id)\n"
                    + "join district dis\n"
                    + "on (a.amphur_id = dis.amphur_id)\n"
                    + "where Res_id = ?;";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, res_id);

            ResultSet rs = stm.executeQuery();

            }
        }
    
    private String genProcessID(HttpServletRequest request) {
        return Long.toHexString(System.currentTimeMillis() / 1000L) + request.getSession().getId().substring(0, 4);
    }
    
    private void saveBasicVal(HttpServletRequest request, Information info) {
        
        info.setAddress(request.getParameter("address") != null ? request.getParameter("address") : null);
        info.setProvince(request.getParameter("province") != null ? Short.parseShort(request.getParameter("province")) : null);
        info.setAmphur(request.getParameter("amphur") != null ? Short.parseShort(request.getParameter("amphur")) : null);
        info.setDistrict(request.getParameter("district") != null ? Short.parseShort(request.getParameter("district")) : null);

        info.setTopic(request.getParameter("ad.topic") != null ? request.getParameter("ad.topic") : null);
        info.setPresent_date(request.getParameter("ad.present_date") != null ? request.getParameter("ad.present_date") : null);
        info.setName(request.getParameter("name") != null ? request.getParameter("name") : null);
        info.setAnnounce_for(request.getParameter("r.announce_for") != null ? request.getParameter("r.announce_for") : null);
        info.setType(request.getParameter("d.types") != null ? request.getParameter("d.types") : null);

        info.setPrice(!request.getParameter("price").equals("") ? Integer.parseInt(request.getParameter("price")) : null);
    }

    private void saveDetailVal(HttpServletRequest request, Information info) {
        info.setFloor(!request.getParameter("d.floor").equals("") ? Short.parseShort(request.getParameter("floor")) : null);
        info.setElectricity(!request.getParameter("d.electrict_bill").equals("") ? Integer.parseInt(request.getParameter("electrict_bill")) : null);
        info.setWater(!request.getParameter("d.water_bill").equals("") ? Integer.parseInt(request.getParameter("d.water_bill")) : null);
        info.setFacilities(request.getParameter("d.facilities") != null ? request.getParameter("d.facilities") : null);
        info.setRemark(request.getParameter("d.remark") != null ? request.getParameter("d.remark") : null);
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
