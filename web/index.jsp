<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Nota"%>
<%
    if (session.getAttribute("listagenda") == null) {
        ArrayList<Nota> listaux = new ArrayList<Nota>();
        session.setAttribute("listagenda", listaux);
    }
    ArrayList<Nota> lista = (ArrayList<Nota>) session.getAttribute("listagenda");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Agenda Personal</h1>
        <a href="Controlador?op=nuevo">Registrar Nueva Actividad</a>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>HORA</th>
                <th>ACTIVIDAD</th>
                <th>ESTADO</th>
                <th></th>
                <th></th>
            </tr>
            <%
                if (lista != null) {
                    for (Nota item : lista) {

            %>
            <tr>
                <td><%= item.getId()%></td>
                <td><%= item.getHora()%></td>
                <td><%= item.getActividad()%></td>
                <td><%= item.getEstado()%></td>
                
                <td><a href="Controlador?op=editar&id=<%= item.getId()%>">Modificar</a></td>
                <td><a href="Controlador?op=eliminar&id=<%= item.getId()%>"
                       onclick='return confirm("Esta seguro de eliminar la Actividad ??");'>Eliminar</a></td>

            </tr>
            <%
                    }
                }
            %>
        </table>
    </body>
</html>
