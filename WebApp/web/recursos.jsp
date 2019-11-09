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
        <h2>Recursos</h2>
        <table>
            <tr>
                <th></th>
                <th>Fecha de subida</th>
                <th>Archivo</th>
                <th>Descargar</th>
            </tr>           
                <c:choose>
                    <c:when test="${persona == 'invitado'}">               
                        <c:forEach items="${recursosPublicos}" var="rp">
                            <tr>
                                <td><img src="https://img.icons8.com/android/144/000000/file.png"></td>
                                <td> ${rp.fecha}  </td> 
                                <td> ${rp.ruta}</td> 
                                <td><a href="descargas?=${r.id}"><img src="https://img.icons8.com/material-sharp/24/000000/download.png"></a></td>
                            </tr> 
                        </c:forEach>
                    </c:when>
                        <c:otherwise>
                            <c:forEach items="${recursos}" var="r">
                                <tr>
                                    <td><img src="https://img.icons8.com/android/144/000000/file.png"></td>
                                    <td>"${r.fecha}"</td>
                                    <td>"${r.ruta}"</td>
                                    <td><a href="descargas?=${r.id}"><img src="https://img.icons8.com/material-sharp/24/000000/download.png"></a></td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                </c:choose>
        </table>   
    </body>
</html>
