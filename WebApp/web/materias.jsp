<%-- 
    Document   : materias
    Created on : 30-oct-2019, 10:18:14
    Author     : IVAN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Materias</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <title>Materias</title>
        <jsp:include page="menu.jsp" />           
    </head>
    <body>
        <h1>Materias</h1>
        <table>
            <tr>
                <th>Materia</th>
                <th>Profesor</th>
            </tr>
                <c:forEach items="${materias}" var="m">
                <tr>
                    <td><a href="materias?id=${m.id}">${m.nombre}</a></td>
                    <td><a href="perfil?id=${m.profesor.id}&pers=profesor">${m.profesor.apellido}, ${m.profesor.nombre}</a></td>
                </tr>
                </c:forEach>
          </table>
        <c:if test="${persona == 'alumno'}">
            <form method="GET" action="inscripcion">
                <input type="submit" value="Inscribirse a otras materias"> 
            </form>
        </c:if>
        
    </body>
</html>
