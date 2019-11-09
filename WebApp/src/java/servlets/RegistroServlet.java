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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Alumno;
import models.Profesor;
import models.TiposDocumento;

/**
 *
 * @author IVAN
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/registro"})
public class RegistroServlet extends HttpServlet {

    

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
        DataAccess data = new DataAccess();
        ArrayList<TiposDocumento> lista = data.getTiposDocumento();
        getServletContext().setAttribute("tiposDocumento", lista);
        request.getSession().invalidate();
        getServletContext().getRequestDispatcher("/registro.jsp").forward(request, response);
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
        DataAccess data = new DataAccess();
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");        
        int legajo = Integer.parseInt(request.getParameter("legajo"));
        int idTipoDoc = Integer.parseInt(request.getParameter("tDoc"));
        int documento = Integer.parseInt(request.getParameter("documento"));
        Date nacimiento = java.sql.Date.valueOf(request.getParameter("nacimiento"));
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String persona = request.getParameter("persona");
        
        boolean flag = false;
        
        if (persona.equals("alumno")) {
            Alumno a = new Alumno();
            a.setNombre(nombre); a.setApellido(apellido);
            a.setLegajo(legajo); a.setDocumento(documento);
            a.setFechaDeNacimiento(nacimiento);
            a.setTipoDocumento(idTipoDoc);
            a.setEmail(email); a.setTelefono(telefono);
            
            flag = data.setAlumno(a);
        } 
        if (persona.equals("profesor")) {
            Profesor p = new Profesor();
            p.setNombre(nombre); p.setApellido(apellido);
            p.setLegajo(legajo); p.setDocumento(documento);
            p.setEmail(email); p.setTelefono(telefono);
            p.setFechaDeNacimiento(nacimiento);
            p.setTipoDocumento(idTipoDoc);
            
            flag = data.setProfesor(p);            
        }

        response.sendRedirect("login");
        
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
