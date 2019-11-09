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
        <meta http-equiv="Content-Type" content="text/html;" charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <jsp:include page="menu.jsp"></jsp:include>
        <style>
            table {
                margin: 10pt;
            }
            
            h2 {
                margin: 10pt;
            }            
            
            th {
                background-color: coral;
                color: #222222;
                text-align: left;
                margin: 5pt;
            }
        </style>
        <title>
            ${perfil.nombre} ${perfil.apellido}
        </title>
    </head>
    <body>
    <c:choose>
        <c:when test="${perfil.legajo == user.legajo}">
            <table>
                <tr>
                    <h2>Tu perfil</h2>
                </tr>
                    <tr>
                        <th>Legajo</th> <td>${user.legajo}</td>
                    </tr>
                    <tr>
                        <th>Documento</th> <td>${user.documento}</td>
                    </tr>
                    <tr>
                        <th>Fecha de nacimiento</th> <td>${user.fechaDeNacimiento}</td>
                    </tr>
                    <c:if test="${persona == 'alumno'}"> 
                        <tr>
                            <th>Dirección</th> <td>${user.calle} ${user.altura} - ${user.codigoPostal}</td>
                        </tr>
                    </c:if>
                        <tr>
                        <th>Email</th> <td>${user.email}</td>
                        </tr>
                        <tr>
                        <th>Teléfono</th>  <td>${user.telefono}</td>
                        </tr>
                    </tr>  
            </table>
                        <form method="GET" action="actualizar">
                            <input type="submit" value="Modificar datos" />
                        </form>
        </c:when>
        <c:otherwise>
            <table align="left">
                <tr>
                    <h2>Perfil de ${perfil.nombre} ${perfil.apellido}</h2>
                </tr>
                    <tr>
                        <th>Legajo</th> <td>${perfil.legajo}</td>
                    </tr>
                    <tr>
                        <th>Documento</th> <td>${perfil.documento}</td>
                    </tr>
                    <tr>
                        <th>Fecha de nacimiento</th> <td>${perfil.fechaDeNacimiento}</td>
                    </tr>
                    <c:if test="${perfilPersona == 'alumno'}"> 
                        <tr>
                            <th>Dirección</th> <td>${perfil.calle} ${perfil.altura} - ${perfil.codigoPostal}</td>
                        </tr>
                    </c:if>
                        <tr>
                        <th>Email</th> <td>${perfil.email}</td>
                        </tr>
                        <tr>
                        <th>Teléfono</th>  <td>${perfil.telefono}</td>
                        </tr>
                    </tr>
                    
            </table>
            
        </c:otherwise>
    </c:choose>
    </body>
</html>
