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
        <title>Login</title>
        <link rel="stylesheet" href="" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <jsp:include page="menu.jsp"></jsp:include>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty user}">
                    <form method="POST" action="login">
                        <p></br>
                            <label for="txtUser">Usuario:</label>
                            <input name="txtUser"/>
                        </p>
                        <p>
                            <select name="Persona">
                                <option value="alumno">Alumno</option>
                                <option value="profesor">Profesor</option>
                            </select>
                        </p>
                        <p>
                            <label for="txtPass">Contraseña:</label>
                            <input type="password" name="txtPass"/>
                        </p>
                        <input type="submit" class="button" value="Ingresar">                
                    </form> 
            </c:when>
            <c:otherwise>
                <h1> Bienvenido ${user.nombre}</h1>
                <a href="logout">Cerrar sesión</a>
            </c:otherwise>
        </c:choose>
    </body>
</html>
