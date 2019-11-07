<%-- 
    Document   : registro
    Created on : 07-nov-2019, 0:42:40
    Author     : IVAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <title>Registro</title>
    </head>
    <body>
         <form method="POST" action="registro">
            <table>
                <tr>
                    <h2>Registro</h2>
                </tr>
                    <tr>
                        <th>Legajo</th> 
                        <td><input type="number" name="legajo"></td>
                    </tr>
                    <tr>
                        <th>Documento</th> 
                        <td><input type="number" name="documento"></td>
                    </tr>
                    <tr>
                        <th>Fecha de nacimiento</th> 
                        <td><input type="date" name="nacimiento" value="${user.fechaDeNacimiento}"></td>
                    </tr>
                    <tr>   <th>Persona</th>
                        <td>                     
                            <select name="Persona">
                                        <option value="alumno">Alumno</option>
                                        <option value="profesor">Profesor</option>
                            </select>
                        </td>
                    </tr> 
                        
                        
                        <tr>
                            <th>Email</th> <td><input type="email" name="email" value="${user.email}"></td>
                        </tr>
                        <tr>
                            <th>Teléfono</th>  <td><input type="tel" name="telefono" value="${user.telefono}"></td>
                        </tr>
                    </tr>  
                    
            </table>
                    <input type="submit" name="btnSubmit" value="Registrarse">
        </form>
    </body>
</html>
