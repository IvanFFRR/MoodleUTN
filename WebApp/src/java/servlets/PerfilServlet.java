/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controllers.DataAccess;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Alumno;
import models.Profesor;

/**
 *
 * @author ALUMNO
 */
@WebServlet(name = "PerfilServlet", urlPatterns = {"/perfil"})
public class PerfilServlet extends HttpServlet {
    

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
        String perfil = request.getParameter("pers");
        int id = Integer.parseInt(request.getParameter("id"));
        
        
        if(perfil.equals("alumno")) {
            for (Alumno a : data.getAlumnos()) {
                if (a.getId() == id) {
                    session.setAttribute("perfil", a);
                    session.setAttribute("perfilPersona", "alumno");
                    getServletContext().getRequestDispatcher("/perfil.jsp").forward(request, response);
                } 
            } 
        } else {
                    if(perfil.equals("profesor")) {
                        for (Profesor p : data.getProfesores()) {
                            if (p.getId() == id) {
                                session.setAttribute("perfil", p);
                                session.setAttribute("perfilPersona", "profesor");                                
                                getServletContext().getRequestDispatcher("/perfil.jsp").forward(request, response);
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
