/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Residential;
import model.ImageMeta;

/**
 *
 * @author Belize
 */
@WebServlet(name = "FileUpload", urlPatterns = {"/upload"})
@MultipartConfig
public class ImageUpload extends HttpServlet {

    //  upload an image 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String process_id = request.getParameter("process_id");
        String type = request.getParameter("type");

        if (process_id != null) {
            HttpSession session = request.getSession();

            Residential ann = (Residential) session.getAttribute(process_id);

            if (type.equals("images")) {

                List<ImageMeta> files = ann.getFiles();

                Collection<Part> parts = request.getParts();

                ImageMeta temp = null;

                for (Part part : parts) {

                    if (part.getContentType() != null) {
                        temp = new ImageMeta();
                        temp.setFileName(getFilename(part));
                        temp.setFileSize(part.getSize() / 1024 + " Kb");
                        temp.setFileType(part.getContentType());
                        temp.setImg(ImageIO.read(part.getInputStream()));
                        files.add(temp);
                    }
                }

                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(response.getOutputStream(), files);

            } else if (type.equals("remove")) {
                try {
                    int index = Integer.parseInt(request.getParameter("index"));
                    ann.getFiles().remove(index);

                } catch (NumberFormatException e) {

                }
            }
        }
    }

    // this method is used to get file name out of request headers
    private String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
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
