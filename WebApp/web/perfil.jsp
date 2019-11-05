<%-- 
    Document   : perfil
    Created on : 04/11/2019, 20:42:04
    Author     : ALUMNO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
    <c:choose>
        <c:when test="${empty user}"
           
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    ${user.nombre} ${user.apellido}
                </tr>
                <tr>
                    <th>Legajo</th>
                    <th>Documento</th>
                    <th>Fecha de nacimiento</th>
                <c:if test='${persona == 'alumno'}'>
                    <th>Dirección</th>
                </c:if>
                    <th>Email</th>
                    <th>Teléfono</th>
                </tr>
                <tr>
                    <td>${user.legajo}</td>
                    <td>${user.documento}</td>
                    <td>${user.fechaDeNacimiento}</td>
                <c:if test='${persona == 'alumno'}'>
                    <td>${user.calle} ${user.altura} - ${user.codigoPostal}</td>
                </c:if>
                    <td>${user.email}</td>
                    <td>${user.telefono}</td>
                </tr>
            </table>
        </c:otherwise>
    </c:choose>
    </body>
</html>
