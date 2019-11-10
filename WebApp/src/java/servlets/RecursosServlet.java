/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controllers.DataAccess;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import models.Materia;
import models.Recurso;

/**
 *
 * @author IVAN
 */
@MultipartConfig
@WebServlet(name = "RecursosServlet", urlPatterns = {"/recursos"})
public class RecursosServlet extends HttpServlet {

    

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
        
        if(request.getSession().getAttribute("user") == null) {
            response.sendRedirect("login");
        }
        
            DataAccess data = new DataAccess();
            ArrayList<Recurso> recursos = new ArrayList<>();
        if(!request.getSession().getAttribute("persona").equals("invitado")) { 
            HttpSession session = request.getSession();
            Materia materia = (Materia)session.getAttribute("materia");

            recursos = data.getRecursos(materia);
            session.setAttribute("recursos", recursos);
            getServletContext().getRequestDispatcher("/recursos.jsp").forward(request, response);
            
        } else {
            recursos = data.getRecursosPublicos();
            getServletContext().setAttribute("recurosPublicos", recursos);
            getServletContext().getRequestDispatcher("/publicos.jsp").forward(request, response);
        }
       
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
        
        if(request.getSession().getAttribute("user") == null) {
            getServletContext().getRequestDispatcher("/login").forward(request, response);
        }
        
        HttpSession session = request.getSession();
        DataAccess data = new DataAccess();
        final String ruta = getServletContext().getRealPath("/resources/archivos");
        final Part filePart = request.getPart("archivo");
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream in = null;
        final PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(new File(ruta + File.separator + fileName));
            in = filePart.getInputStream();

            int read;
            final byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            writer.println("Error in file upload  ERROR:" + e.getMessage());

        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (writer != null) {
                writer.close();
            }
            getServletContext().getRequestDispatcher("descargas").forward(request, response);
        }
    }
    
    private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
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
