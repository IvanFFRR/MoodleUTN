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
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Alumno;
import models.Inscripcion;
import models.Materia;

/**
 *
 * @author IVAN
 */
@WebServlet(name = "InscripcionServlet", urlPatterns = {"/inscripcion"})
public class InscripcionServlet extends HttpServlet {

    
   

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
        String persona = (String)session.getAttribute("persona");
        DataAccess data = new DataAccess();
            if("alumno".equals(persona)) {
                Alumno a = (Alumno)session.getAttribute("user");
                ArrayList<Materia> noMaterias = data.getNoMaterias(a);
            
            session.setAttribute("nomaterias", noMaterias);              
            request.getRequestDispatcher("inscripcion.jsp").forward(request, response);
            
            
            } else {
                if("profesor".equals(persona)) {
                    
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
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
                    
        HttpSession session = request.getSession();
        DataAccess data = new DataAccess();
        try {
            String[] input = request.getParameterValues("chkMaterias");
            int[] materias = new int[input.length];

            for (int i = 0; i < materias.length; i++) {
                materias[i] = Integer.parseInt(input[i]);
            }

            for (int i : materias) {
                Materia m = data.getMateria(i);
                Inscripcion insc = new Inscripcion(new java.sql.Date(Calendar.getInstance().getTime().getTime()), m, (Alumno) session.getAttribute("user"));
                data.setInscripcion(insc);
            }
        } catch (Exception e) {
            
        }
        
        response.sendRedirect(request.getContextPath() + "/materias");
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
