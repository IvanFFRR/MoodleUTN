/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controllers.DataAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Alumno;
import models.Credenciales;
import models.Materia;
import models.Profesor;

/**
 *
 * @author IVAN
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        
        String user = request.getParameter("txtUser");
        String pass = request.getParameter("txtPass");
        String persona = request.getParameter("Persona");
        DataAccess data = new DataAccess();
        
        if(persona.equals("alumno")) {
            ArrayList<Alumno> alumnos = data.getAlumnos();
            for (Alumno a : alumnos) {
                if (user.equals(Integer.toString(a.getLegajo())) && pass.equals(Integer.toString(a.getDocumento()))) {
                    session.setAttribute("user", a);
                    session.setAttribute("persona", persona);
                    break;
                }
            }
        } else {
            if (persona.equals("profesor")) {
                ArrayList<Profesor> profesores = data.getProfesores();
                for (Profesor p : profesores) {
                    if (user.equals(Integer.toString(p.getLegajo())) && pass.equals(Integer.toString(p.getDocumento()))) {
                        String nombreUsuario = String.format("%s %s", p.getNombre(), p.getApellido());
                        session.setAttribute("persona", persona);
                        session.setAttribute("user", p);
                        break;
                    }
                }
            }
        }
                        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
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
