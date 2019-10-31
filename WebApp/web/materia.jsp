<%-- 
    Document   : materia
    Created on : 30/10/2019, 20:12:45
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${materia.nombre}</title>
        <jsp:include page="menu.jsp"></jsp:include>
    </head>
    <body>
        <h1>${materia.nombre}</h1>
    Profesor: <a href="profesor?id=${materia.profesor.id}">${materia.profesor.apellido}, ${materia.profesor.nombre}</a>
    
    <p>Alumnos inscritos a esta materia: </p>
    <table>
        <tr><th>Legajo</th><th>Nombre</th></tr>
        <c:forEach items="${alumnos}" var="a">
            <tr>
                <td>${a.legajo}</td>
                <td>${a.apellido}, ${a.nombre}</td>            
            </tr>
        </c:forEach>
        
    </table>
    </body>
</html>
