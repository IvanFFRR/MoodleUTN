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
    <b>Profesor:</b> <a href="profesor?id=${materia.profesor.id}">${materia.profesor.apellido}, ${materia.profesor.nombre}</a>
    
    
    <p>Alumnos inscritos a esta materia: </p>
    <c:set var = "match" value="false"/>
    <table>
        <tr><th>Legajo</th><th>Nombre</th></tr>
        <c:forEach items="${alumnos}" var="a">
            <tr>
                <td>${a.legajo}</td>
                <td>${a.apellido}, ${a.nombre}</td>
                <c:if test = "${a.legajo == user.legajo}">
                <c:set var="match" value="true"/>
                </c:if>
            </tr>
        </c:forEach>        
    </table>
    
  
        <c:if test="${match == true}">
            <p> Ya estás inscrito a esta materia</p>
        </c:if>
            <c:if test="${match == false}">
                <form method="POST" action="materias">
                        <input type="submit" value="Inscribirse a esta materia"> 
                </form>
            </c:if>

    </body>
</html>
