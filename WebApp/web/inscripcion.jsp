<%-- 
    Document   : inscripcion
    Created on : 02-nov-2019, 17:21:43
    Author     : IVAN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;" charset=UTF-8">
        <title>Inscripción</title>
        <jsp:include page="menu.jsp"></jsp:include>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script>
            $(document).ready(function(){
              $('[data-toggle="tooltip"]').tooltip();
            });
        </script> <%-- tooltip en el signo de pregunta de la forma --%>
    </head>
    <body>
        
        Materias a las que no estás inscrito
        
        <form method="POST">
            <table>
                <tr>
                    <c:choose>
                        <c:when test="${empty nomaterias}">
                           Ya estás inscrito a todas las materias. 
                        </c:when>
                        <c:otherwise>                            
                    <th><u data-toggle="tooltip" title="Tilde la caja junto a la materia a la que quiera inscribirse">?</u></th>
                    <th>Materia</th>
                    <th>Profesor</th>
                </tr>
                <c:forEach items="${nomaterias}" var="nm">
                <tr>
                    <td><input type="checkbox" name="chkMaterias" value="${nm.id}"></td>
                    <td>${nm.nombre}</td>
                    <td>${nm.profesor.apellido}, ${nm.profesor.nombre}</td>
                </tr>
                </c:forEach>
            </table>                
            <input type="Submit" value="Inscribirse">
        </form>
                        </c:otherwise>
                    </c:choose>
    </body>
</html>
