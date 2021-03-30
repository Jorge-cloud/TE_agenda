<%@page import="com.emergentes.modelo.Nota"%>
<%
    Nota nota = (Nota) request.getAttribute("miobjnota");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registrar Nueva Actividad</h1>
        <form action="Controlador" method="post">
            <table>
                <tr>
                    <td>ID</td>
                    <td><input type="text" name="id" value="<%= nota.getId()%>" size="2" readonly/></td>
                </tr>

                <tr>
                    <td>HORA</td>
                    <td><input type="time" name="hora" value="<%= nota.getHora()%>" /></td>
                </tr>

                <tr>
                    <td>ACTIVIDAD</td>
                    <td><input type="text" name="actividad" value="<%= nota.getActividad()%>" /></td>
                </tr>

                <tr>
                    <td>ESTADO</td>
                    <%-- <td><input type="text" name="estado" value="<%= nota.getEstado()%>"/></td>--%>
                    <td>
                        <%
                            if (nota.getEstado().equals("cumplido")) {

                        %>
                                <input type="radio" name="estado" value="cumplido" checked>Cumplido<br>
                                <input type="radio" name="estado" value="no cumplido">No Cumplido
                        <%
                            }else{    
                        %>
                                <input type="radio" name="estado" value="cumplido" >Cumplido<br>
                                <input type="radio" name="estado" value="no cumplido" checked>No Cumplido
                        <%
                            }   
                        %>

                    </td>
                </tr>

                <tr>

                    <td></td>
                    <td><br><input type="submit" value="Guardar"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
