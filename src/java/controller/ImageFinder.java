/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Residential;
import model.ImageMeta;

/**
 *
 * @author Belize
 */
@WebServlet("/image/*")
public class ImageFinder extends HttpServlet {

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

        String process_id = request.getParameter("process_id");
        String type = request.getParameter("type");
        String id = request.getParameter("id");

        if (process_id != null) {

            Residential ann = (Residential) request.getSession().getAttribute(process_id);

            if (type.equals("preview")) {
                int index = Integer.parseInt(request.getParameter("index"));

                ImageMeta meta = ann.getFiles().get(index);
                Image img = meta.getImg();

                Dimension imgSize = new Dimension(img.getWidth(null), img.getHeight(null));
                Dimension boundary = new Dimension(320, 320);
                Dimension scaledDimension = getScaledDimension(imgSize, boundary);

                response.setContentType(meta.getFileType());
                ImageIO.write(toBufferedImage(img.getScaledInstance(scaledDimension.width, scaledDimension.height, Image.SCALE_FAST)), "png", response.getOutputStream());

            } else if (type.equals("thumbnail")) {

                ImageMeta meta = ann.getFiles().get(0);
                Image img = meta.getImg();

                response.setContentType(meta.getFileType());
                ImageIO.write(toBufferedImage(img.getScaledInstance(200, 200, Image.SCALE_FAST)), "png", response.getOutputStream());
            }
        } else if (id != null) {
            if (type.equals("thumbnail")) {
                try {
                    Connection connection = (Connection) getServletContext().getAttribute("connection");

                    String sql = "SELECT image FROM `image of detail` WHERE Res_id = ? LIMIT 1";

                    PreparedStatement stm = connection.prepareStatement(sql);
                    stm.setInt(1, Integer.parseInt(id));
                    ResultSet rs = stm.executeQuery();

                    if (rs.next()) {
                        Blob imageBlob = rs.getBlob("image");
                        Image img = ImageIO.read(imageBlob.getBinaryStream());
                        response.setContentType("image/jpeg");
                        ImageIO.write(toBufferedImage(img.getScaledInstance(200, 200, Image.SCALE_FAST)), "png", response.getOutputStream());
                    } else {
                        response.sendRedirect("/RETS/assets/img/no-photo-placeholder.png");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (type.equals("gallery")) {
                try {
                    Connection connection = (Connection) getServletContext().getAttribute("connection");

                    String sql = "SELECT image FROM `image of detail` WHERE image_id = ?";

                    PreparedStatement stm = connection.prepareStatement(sql);
                    stm.setInt(1, Integer.parseInt(id));
                    ResultSet rs = stm.executeQuery();

                    if (rs.next()) {
                        Blob imageBlob = rs.getBlob("image");
                        Image img = ImageIO.read(imageBlob.getBinaryStream());

                        Dimension imgSize = new Dimension(img.getWidth(null), img.getHeight(null));
                        Dimension boundary = new Dimension(850, 400);
                        Dimension scaledDimension = getScaledDimension(imgSize, boundary);

                        response.setContentType("image/jpeg");

                        ImageIO.write(toBufferedImage(img.getScaledInstance(scaledDimension.width, scaledDimension.height, Image.SCALE_FAST)), "png", response.getOutputStream());
                    } else {
                        response.sendRedirect("/RETS/assets/img/no-photo-placeholder.png");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (type.equals("nav")) {
                try {
                    Connection connection = (Connection) getServletContext().getAttribute("connection");

                    String sql = "SELECT image FROM `image of detail` WHERE image_id = ?";

                    PreparedStatement stm = connection.prepareStatement(sql);
                    stm.setInt(1, Integer.parseInt(id));
                    ResultSet rs = stm.executeQuery();

                    if (rs.next()) {
                        Blob imageBlob = rs.getBlob("image");
                        Image img = ImageIO.read(imageBlob.getBinaryStream());

                        Dimension imgSize = new Dimension(img.getWidth(null), img.getHeight(null));
                        Dimension boundary = new Dimension(100, 50);
                        Dimension scaledDimension = getScaledDimension(imgSize, boundary);

                        response.setContentType("image/jpeg");

                        ImageIO.write(toBufferedImage(img.getScaledInstance(scaledDimension.width, scaledDimension.height, Image.SCALE_FAST)), "png", response.getOutputStream());
                    } else {
                        response.sendRedirect("/RETS/assets/img/no-photo-placeholder.png");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    private Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
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
