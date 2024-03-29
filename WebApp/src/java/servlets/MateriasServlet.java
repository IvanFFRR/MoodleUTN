/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controllers.DataAccess;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Alumno;
import models.Materia;
import models.Profesor;

/**
 *
 * @author IVAN
 */
@WebServlet(name = "MateriasServlet", urlPatterns = {"/materias"})
public class MateriasServlet extends HttpServlet {

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
        
        DataAccess data = new DataAccess();
        ArrayList<Materia> materias = new ArrayList<>();
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        String id = request.getParameter("id");
        String persona = (String)session.getAttribute("persona");
        
        if(session.getAttribute("user") != null) {
             if ("alumno".equals(persona)) {
            Alumno a = (Alumno)session.getAttribute("user");
            materias = data.getMaterias(a);  
            session.setAttribute("materias", materias);
            
            
            } else {
            if("profesor".equals(persona)) {
                Profesor p = (Profesor)session.getAttribute("user");
                materias = data.getMaterias(p);
                session.setAttribute("materias", materias);
                
                }
            }
        } else {
               rd = getServletContext().getRequestDispatcher("/login");
               rd.forward(request, response);
            }
        
        
        if(id != null) {
            for (Materia materia : materias) {
                if(Integer.parseInt(id) == materia.getId()) {
                    ArrayList<Alumno> alumnos = data.getAlumnos(materia);
                    session.setAttribute("alumnos", alumnos);                                        
                    session.setAttribute("materia", materia);
                    rd = getServletContext().getRequestDispatcher("/materia.jsp");
                    rd.forward(request, response);
                    break;
                }
            }
        }
        rd = getServletContext().getRequestDispatcher("/materias.jsp");
        rd.forward(request, response);
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
