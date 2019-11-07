/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controllers.DataAccess;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Materia;
import models.Recurso;

/**
 *
 * @author IVAN
 */
@WebServlet(name = "DescargasServlet", urlPatterns = {"/descargas"})
public class DescargasServlet extends HttpServlet {


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
            getServletContext().getRequestDispatcher("/login").forward(request, response);
        }
        
        HttpSession session = request.getSession();
        boolean logged = session.getAttribute("user") != null;
        DataAccess data = new DataAccess();
        String id = request.getParameter("id");
        
        
        if(id != null && !id.isEmpty()) {
            for(Recurso recurso : data.getRecursos((Materia)session.getAttribute("materia"))) {
                if (recurso.getId() == Integer.parseInt(id)) {
                    if (logged || !logged && !recurso.esPrivado()) {
                        String path = recurso.getRuta();
                        File archivo = new File(path);
                        response.setContentType("application/octet-stream");
                        response.setHeader("Content-disposition","attachment; filename= " + path);
                        
                        try {
                            OutputStream out = response.getOutputStream();
                            FileInputStream in = new FileInputStream(archivo);
                            byte[] buffer = new byte[4096];
                            int length;
                            while ((length = in.read(buffer)) > 0){
                               out.write(buffer, 0, length);
                            }
                            in.close();
                            out.flush();
                            
                            
                            
                        } catch (IOException e) {
                            String error = e.getMessage();
                            System.out.println("Error al descargar un archivo: ERROR: " + error);
                        }
                        
                        
                    }
                }
            }
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
