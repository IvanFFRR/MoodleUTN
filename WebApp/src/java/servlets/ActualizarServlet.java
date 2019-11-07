/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controllers.DataAccess;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Alumno;
import models.Profesor;

/**
 *
 * @author IVAN
 */
@WebServlet(name = "ActualizarServlet", urlPatterns = {"/actualizar"})
public class ActualizarServlet extends HttpServlet {

   
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
        
        getServletContext().getRequestDispatcher("/actualizar.jsp").forward(request, response);
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
       
         DataAccess data = new DataAccess();
         
       java.sql.Date nacimiento = (java.sql.Date)request.getAttribute("dNacimiento");      
       String email = (String)request.getAttribute("email");
       String telefono = (String)request.getAttribute("txtTelefono");
       
        if(request.getSession().getAttribute("persona").equals("alumno")) {
           Alumno a = (Alumno)request.getSession().getAttribute("user");   
           
           String calle = (String)request.getAttribute("txtCalle");
           String altura = (String)request.getAttribute("nmbAltura");
           String codigoPostal = (String)request.getAttribute("nmbCP");
           
           //código hediondo, usar HashMap o TupleList?
           if(calle!=null & !calle.isEmpty())
            a.setCalle(calle);
           if(nacimiento!=null)
            a.setFechaDeNacimiento(nacimiento);
           if(altura!=null & !altura.isEmpty())
            a.setAltura(Integer.parseInt(altura));
           if(codigoPostal!=null & !codigoPostal.isEmpty())
            a.setCodigoPostal(Integer.parseInt(codigoPostal));
           if(email!=null & !email.isEmpty())
            a.setEmail(email);
           if(telefono!=null & !telefono.isEmpty())
            a.setTelefono(telefono);
           data.updatePersona(a);
           
           getServletContext().getRequestDispatcher(String.format("/perfil?id=%s&pers=alumno", a.getId()));      
           
       } else {
            if(request.getSession().getAttribute("persona").equals("profesor")) {
                Profesor a = (Profesor)request.getSession().getAttribute("user");

                if(nacimiento!=null)
                 a.setFechaDeNacimiento(nacimiento);
                if(email!=null & !email.isEmpty())
                 a.setEmail(email);
                if(telefono!=null & !telefono.isEmpty())
                 a.setTelefono(telefono);
                data.updatePersona(a);
                
                getServletContext().getRequestDispatcher(String.format("/perfil?id=%s&pers=profesor", a.getId()));     
            }
        }
            
        
       
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
