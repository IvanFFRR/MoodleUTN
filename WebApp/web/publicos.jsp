<%-- 
    Document   : publicos
    Created on : 09-nov-2019, 16:35:04
    Author     : IVAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <jsp:include page="menu.jsp"></jsp:include>
        <title>Recursos públicos</title>
    </head>
    <body>
        <h2>Recursos públicos</h2>
        <table>
            <tr>
                <th></th>
                <th>Fecha de subida</th>
                <th>Archivo</th>
                <th>Descargar</th>
            </tr>           
                
                        <c:forEach items="${recursosPublicos}" var="rp">
                            <tr>
                                <td><img src="https://img.icons8.com/android/144/000000/file.png"></td>
                                <td> ${rp.fecha}  </td> 
                                <td> ${rp.ruta}</td> 
                                <td><a href="descargas?=${r.id}"><img src="https://img.icons8.com/material-sharp/24/000000/download.png"></a></td>
                            </tr> 
                        </c:forEach>
        </table>
    </body>
</html>
