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
        <meta http-equiv="Content-Type" content="text/html;" charset=UTF-8">
        <title>${materia.nombre}</title>
        <jsp:include page="menu.jsp"></jsp:include>
        <script>
            $(document).ready(function(){
              $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
        <link rel="stylesheet" type="text/css" href="resources/tables.css">
        <link rel="stylesheet" type="text/css" href="resources/buttons.css">
    </head>
    <body>
        <h1>${materia.nombre}</h1>
    <b>Profesor:</b> <a href="perfil?pers=profesor&id=${materia.profesor.id}">${materia.profesor.apellido}, ${materia.profesor.nombre}</a>
    
    
    <p>Alumnos inscritos a esta materia: </p>
    <c:set var = "match" value="false"/>
    <table>
        <tr><th>Legajo</th><th>Nombre</th></tr>
        <c:forEach items="${alumnos}" var="a">
            <tr>
                <td>${a.legajo}</td>
                <td><a href="perfil?pers=alumno&id=${a.id}">${a.apellido}, ${a.nombre}</a></td>
                <c:if test = "${a.legajo == user.legajo}">
                    <c:set var="match" value="true"/>
                </c:if>
            </tr>
        </c:forEach>        
    </table>
    
    <c:choose>
        <c:when test="${persona == 'alumno'}">
        <c:if test="${match == true}">
            <p> Ya estás inscrito a esta materia</p>
            <div class="recursos">
                
                <jsp:include page="recursos.jsp"></jsp:include>
               
            </div>
        </c:if>
            <c:if test="${match == false}">
                <form method="POST" action="materias">
                        <input type="submit" value="Inscribirse a esta materia"> 
                </form>
            </c:if>
        </c:when>
        <c:otherwise>
            <c:if test="${user.id == materia.profesor.id}">
                Eres profesor de esta materia.<br />
                <h2>Subir recurso a esta materia</h2>
                <form method="POST" action="recursos" enctype="multipart/form-data">
                    <input type="file" name="archivo">
                    <label data-toggle="tooltip" title="Si el recurso es privado, sólo los alumnos registrados pueden verlo y descargarlo. (Destildar para hacerlo público)"><input type="checkbox" name="esPrivado" checked> ¿Recurso privado? </label>
                    <input type="submit" value="Subir">
                </form>
            </c:if>
        </c:otherwise>
    </c:choose>
    </body>
</html>
