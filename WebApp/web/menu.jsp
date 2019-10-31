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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            float: left;
            color: darkgrey;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
          }
          .topnav a.active {
            background-color: #333333;
            color: white;
          }
          .topnav a:hover {
              background-color: black;
          }

        </style>
    </head>
    <body>
        
        <div class="topnav"> 
            <b class="title">Universidad Virtual UTN</b>
                <c:choose>
                <c:when test="${empty user}">
                    <a class="active" href="login" style="float: right">Iniciar Sesión</a>
                </c:when>
                <c:otherwise>
                    <a href="materias">Materias</a>
                    ${user.nombre} <a href="logout">Cerrar Sesión</a>
                </c:otherwise>
            </c:choose>
        
        
        </div>
    </body>
</html>
