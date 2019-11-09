<%-- 
    Document   : recursos
    Created on : 30-oct-2019, 12:53:49
    Author     : IVAN
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;" charset=UTF-8">
        <title>Recursos</title>
        
    </head>
    <body>
        <table>
            <tr>
                <th></th>
                <th>Fecha de subida</th>
                <th>Archivo</th>
                <th>Descargar</th>
            </tr>
            <c:forEach items="${recursos}" var="r">
                <c:choose>
                    <c:when test="${empty user}">
                        <c:if test="${r.esPrivado}">                            
                            <tr>
                            <td><img src="https://img.icons8.com/android/144/000000/file.png"></td>
                            <td>"${r.fecha}"</td>
                            <td>"${r.ruta}"</td>
                            <td><a href="descargas?=${r.id}"><img src="https://img.icons8.com/material-sharp/24/000000/download.png"></a></td>
                            </tr>
                        </c:if>
                    </c:when>
                        <c:otherwise>
                             <tr>
                            <td><img src="https://img.icons8.com/android/144/000000/file.png"></td>
                            <td>"${r.fecha}"</td>
                            <td>"${r.ruta}"</td>
                            <td><a href="descargas?=${r.id}"><img src="https://img.icons8.com/material-sharp/24/000000/download.png"></a></td>
                            </tr>
                        </c:otherwise>
                </c:choose>
            </c:forEach>
        </table>
   
    </body>
</html>
