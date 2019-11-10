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
        <meta http-equiv="Content-Type" content="text/html;" charset=UTF-8">
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="resources/tables.css">
        <link rel="stylesheet" type="text/css" href="resources/buttons.css">
        <c:choose> 
            <c:when test="${empty user}">
                <title>Iniciar sesión</title>
            </c:when>
            <c:otherwise>
                <title>Bienvenido</title>
            </c:otherwise>
        </c:choose>
        
        <jsp:include page="menu.jsp" />
    </head>
    <body>
        <c:choose>
            <c:when test="${empty user}">
                    <form method="POST" action="login">
                        <table>
                            <tr>
                                <th>Usuario</th>
                                <td><input name="txtUser"/></td>
                            </tr>
                            <tr>
                                <th>Persona</th>
                                <td>
                                    <select name="Persona" style="width: 100%; height: 100%">
                                        <option value="alumno">Alumno</option>
                                        <option value="profesor">Profesor</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>Contraseña</th>
                                <td><input type="password" name="txtPass"></td>
                            </tr>
                            <tr>
                                <td colspan="2" class="buttonColumn"> <input type="submit" class="button" value="Ingresar" style="float:right"> </td>
                            </tr>
                        </table>
                    <p>
                        ¿No tienes una cuenta? <a href="registro">Crea una</a> o <a href="login?pers=inv">entra como invitado</a>.
                    </p>
            </c:when>
            <c:otherwise>
                <h1 align="center"> Bienvenido/a ${user.nombre} ${user.apellido}</h1>
               
            </c:otherwise>
        </c:choose>
    </body>
</html>


