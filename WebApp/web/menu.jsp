<%-- 
    Document   : manu
    Created on : 30/10/2019, 21:04:39
    Author     : alumno
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;" charset=UTF-8">
        <style>
            .topnav {
            margin: 0;
            overflow: hidden;
            background-color: coral;
            font-family: sans-serif;
          }
          
          .title {
              position: absolute;
              margin: 1%;
          }

          .topnav a {
            float: right;
            color: #222222;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
          }
          
          .logout:hover {
              background-color: black;
              color: red;
          }
          
          .topnav a.active {
            background-color: #222222;
            color: #EEEEEE;
          }
          .topnav a:hover {
              background-color: black;
              color: white;
          }
          
          .topnav.logout {
              background-color: #222222;
              color: #EEEEEE;
          }
      
          
          footer {
              position: fixed;
              left: 0;
              bottom: 0;
              width: 100%;
              background-color: #222222;
              color: #AAAAAA;
              text-align: center;
              padding: 10px;
              font-size: 8pt;
          }
        </style>
        
    </head>
    <body>
        
            <div class="topnav"> 
                <header>                    
                <b class="title">Universidad Virtual UTN</b>
                    <c:choose>
                    <c:when test="${empty user || persona == 'invitado'}">
                        <a class="active" href="login">Iniciar Sesión</a>
                        <a href="recursos">Recursos públicos</a>
                    </c:when>
                    <c:otherwise>
                         <div id="botones" class="botones">
                              <a href="logout" class="logout">⏻</a>
                              <a href="perfil?pers=${persona}&id=${user.id}">${user.nombre} ${user.apellido}</a>
                              <c:if test="${persona == 'alumno'}">                                  
                                <a href="inscripcion">Inscripciones</a>
                              </c:if>
                              <a href="materias">Materias</a>
                        </div>                       
                    </c:otherwise>
                </c:choose>
                </header> 
            </div>
     
            <footer>
                Trabajo Práctico Integrador - Laboratorio IV <br>
                Iván Facundo Rosas - 109780 - 2W2 <br>
                Universidad Tecnológica Nacional                
            </footer>
        
        
    </body>
</html>
