/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Announce;

/**
 *
 * @author Belize
 */
@WebServlet(name = "NewAnnounce", urlPatterns = {"/NewAnnounce"})
public class NewAnnounce extends HttpServlet {

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

        String process_id = request.getParameter("process_id");
        String process = request.getParameter("process");
        String submit = request.getParameter("submit");

        if (process_id == null) {
            newAnnounce(request, response);
        } else {
            changeProcess(request, response, process_id, process, submit);
        }
    }

    private void newAnnounce(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(true);

        Announce announce = new Announce();
        String process_id = genProcessID(request);
        session.setAttribute(process_id, announce);

        request.getRequestDispatcher("new_announce1.jsp?process_id=" + process_id).forward(request, response);
    }

    private void changeProcess(HttpServletRequest request, HttpServletResponse response,
            String process_id, String process, String submit)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Announce announce = (Announce) session.getAttribute(process_id);

        if (submit.equals("ถัดไป")) {
            switch (process) {
                case "basic":
                    announce.saveBasicVal(request);
                    request.getRequestDispatcher("new_announce2.jsp?process_id=" + process_id).forward(request, response);
                    break;
                case "detail":
                    announce.saveDetailVal(request);
                    request.getRequestDispatcher("new_announce3.jsp?process_id=" + process_id).forward(request, response);
                    break;
                case "media":
                    announce.saveMediaVal(request);
                    request.getRequestDispatcher("new_announce4.jsp?process_id=" + process_id).forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("new_announce1.jsp?process_id=" + process_id).forward(request, response);
                    break;
            }

        } else if (submit.equals("กลับ")) {
            switch (process) {
                case "detail":
                    announce.saveDetailVal(request);
                    request.getRequestDispatcher("new_announce1.jsp?process_id=" + process_id).forward(request, response);
                    break;
                case "media":
                    announce.saveMediaVal(request);
                    request.getRequestDispatcher("new_announce2.jsp?process_id=" + process_id).forward(request, response);
                    break;
                case "summary":
                    request.getRequestDispatcher("new_announce3.jsp?process_id=" + process_id).forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("new_announce1.jsp?process_id=" + process_id).forward(request, response);
                    break;
            }
        } else if (submit.equals("บันทึก")) {
            updateAnnounce();
        } else if (submit.equals("ลงประกาศ")) {
            announce();
        }
    }

    // update announce record
    private void updateAnnounce() {

    }

    // create a new record of announce
    private void announce() {

    }

    //  generate by current hex time combine with 4 first session_id
    private String genProcessID(HttpServletRequest request) {
        return Long.toHexString(System.currentTimeMillis()) + request.getSession().getId().substring(0, 4);
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
