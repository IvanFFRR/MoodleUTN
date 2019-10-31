<%-- 
    Document   : materia
    Created on : 30/10/2019, 20:12:45
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </body>
</html>
