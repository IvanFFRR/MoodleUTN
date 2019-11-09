<%-- 
    Document   : actualizar
    Created on : 06-nov-2019, 22:37:43
    Author     : IVAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Actualizar datos</title>
    </head>
    <body>
        
        <form method="POST" action="actualizar">
            <table>
                <tr>
                    <h2>Tu perfil</h2>
                </tr>
                    <tr>
                        <th>Legajo</th> 
                        <td>${user.legajo}</td>
                    </tr>
                    <tr>
                        <th>Documento</th> 
                        <td>${user.documento}</td>
                    </tr>
                    <tr>
                        <th>Fecha de nacimiento</th> 
                        <td><input type="date" name="dNacimiento" value="${user.fechaDeNacimiento}"></td>
                    </tr>
                    <c:if test="${persona == 'alumno'}"> 
                        <tr>
                            <th>Dirección</th> 
                            <td>Calle: <input type="text" name="txtCalle" value="${user.calle}"> Altura <input type="number" name="nmbAltura" value="${user.altura}"> CP: <input type="number" name="nmbCP" value="${user.codigoPostal}"></td>
                        </tr>
                    </c:if>
                        <tr>
                            <th>Email</th> <td><input type="email" name="email" value="${user.email}"></td>
                        </tr>
                        <tr>
                            <th>Teléfono</th>  <td><input type="tel" name="txtTelefono" value="${user.telefono}"></td>
                        </tr>
                    </tr>  
                    
            </table>
            <input type="submit" class="button" value="Modificar">
        </form>
    </body>
</html>
