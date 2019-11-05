/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controllers.DataAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
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
        HttpSession session = request.getSession();
        DataAccess data = new DataAccess();
        Materia materia = (Materia)session.getAttribute("materia");
        
       
        ArrayList<Recurso> recursos = data.getRecursos(materia);
        session.setAttribute("recursos", recursos);
        getServletContext().getRequestDispatcher("/recursos.jsp").forward(request, response);
       
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
        HttpSession session = request.getSession();
        DataAccess data = new DataAccess();
        for (Part part : request.getParts()) {
            String fileName = getFileName(part);
            if (!fileName.isEmpty()) {
                part.write(fileName);
                Boolean privado = request.getParameter("esPrivado") != null;
                
                Materia materia = (Materia)session.getAttribute("materia");
                Date ahora = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                Recurso r = new Recurso(ahora, materia, fileName, privado);
                data.setRecurso(r);
            }
        }
    }
    
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                                      
                return content.substring(content.lastIndexOf("\\") + 1, content.length() - 1);
            }
        }
        return "";
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
