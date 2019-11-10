<%-- 
    Document   : registro
    Created on : 07-nov-2019, 0:42:40
    Author     : IVAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;" charset=UTF-8">
        <jsp:include page="menu.jsp"></jsp:include>
        <link rel="stylesheet" type="text/css" href="resources/tables.css">
        <link rel="stylesheet" type="text/css" href="resources/buttons.css">
        <title>Registro</title>
    </head>
    <body>
         <form method="POST" action="registro">
            <table>
                <tr>
                    <h2>Registro</h2>
                </tr>
                <tr>
                        <th>Nombre</th> 
                        <td><input type="text" name="nombre"></td>
                    </tr>
                    <tr>
                        <th>Apellido</th> 
                        <td><input type="text" name="apellido" value=""></td>
                    </tr>
                    <tr>
                        <th>Legajo</th> 
                        <td><input type="number" name="legajo"></td>
                    </tr>
                    <tr>
                        <th>Tipo de documento</th>
                        <td>
                            <select name="tDoc"> 
                            <c:forEach items="${tiposDocumento}" var="td">
                                <option value="${td.id}">${td.tipo}</option>
                            </c:forEach>
                            
                            </select>
                        </td>
                    </tr>
                    
                    <tr>
                        <th>Documento</th> 
                        <td><input type="number" name="documento"></td>
                    </tr>
                    <tr>
                        <th>Fecha de nacimiento</th> 
                        <td><input type="date" name="nacimiento"></td>
                    </tr>
                    <tr>   <th>Persona</th>
                        <td>                     
                            <select name="persona">
                                        <option value="alumno">Alumno</option>
                                        <option value="profesor">Profesor</option>
                            </select>
                        </td>
                    </tr> 
                        
                        
                        <tr>
                            <th>Email</th> <td><input type="email" name="email"></td>
                        </tr>
                        <tr>
                            <th>Teléfono</th>  <td><input type="tel" name="telefono"></td>
                        </tr>
                    </tr>  
                    
            </table>
                    <input type="submit" class="button" value="Registrarse">
        </form>
    </body>
</html>
