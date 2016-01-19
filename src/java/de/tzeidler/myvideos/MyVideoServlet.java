package de.tzeidler.myvideos;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author Toni Zeidler
 */
@WebServlet("/allVideos")
public class MyVideoServlet extends HttpServlet {

    @EJB
    private MyVideoService videoServise;
    
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
        
        // if database is empty create new datasets
        if(videoServise.isEmpty()){
            List<byte[]> images = new ArrayList<byte[]>();
            images.add(readImageFile("/img/Zorro-1975-poster.jpg"));
            try {
                videoServise.createNewVideo(new MyVideo("Zorro", "sehr guter Wester", GENRES.Western, 5, new SerialBlob(images.get(0)), "Leaving Room under the Pilow"));
            } catch (SQLException ex) {
                Logger.getLogger(MyVideoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyVideoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyVideoServlet at " + request.getContextPath() + "</h1>");
            
            for (MyVideo myVideo : videoServise.getAllVideos()) {
                out.println(myVideo.getTitel()+ "<br>");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Read the file and returns the byte array
     * 
     * @param imageFile
     * @return the bytes of the file
     */
    private byte[] readImageFile(String imageFile) {
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(imageFile);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
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
