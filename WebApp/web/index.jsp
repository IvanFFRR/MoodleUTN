<%-- 
    Document   : index
    Created on : 29-oct-2019, 18:43:42
    Author     : IVAN
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:choose>
        <c:when test="${empty user}">
                   
                <form method="POST" action="Login">
                    <p></br>
                        <label for="txtUsuario">Usuario:</label>
                        <input name="txtUser"/>
                    </p>
                    <p>
                        <label for="txtPass">Contraseña:</label>
                        <input name="txtPass"/>
                        </p>
                    <input  type="submit" class="button" value="Ingresar">                
                </form> 
           
        </c:when>
        <c:otherwise>
            <h1>
                Bienvenido ${user}
            </h1>
        </c:otherwise>
    </c:choose>
    </body>
</html>
