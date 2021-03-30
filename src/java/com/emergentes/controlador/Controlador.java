
package com.emergentes.controlador;

import com.emergentes.modelo.Nota;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("op");
        Nota objnota = new Nota();
        int id, pos;
        HttpSession ses = request.getSession();
        ArrayList<Nota> lista = (ArrayList<Nota>) ses.getAttribute("listagenda");

        switch (opcion) {
            case "nuevo":
                //evia objeto a editar
                request.setAttribute("miobjnota", objnota);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPorIndice(request, id);
                objnota = lista.get(pos);
                request.setAttribute("miobjnota", objnota);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                //obtener posociondel elemento de la collecion
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPorIndice(request, id);
                if (pos >= 0) {
                    lista.remove(pos);
                }
                //Actuaslizamos la lista
                request.setAttribute("listagenda", lista);
                response.sendRedirect("index.jsp");
                break;
            case "marcar":
                
                break;
            default:
                request.setAttribute("listagenda", lista);
                response.sendRedirect("index.jsp");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses = request.getSession();
        ArrayList<Nota> lista = (ArrayList<Nota>) ses.getAttribute("listagenda");
        Nota objnota = new Nota();
        objnota.setId(id);
        objnota.setHora(request.getParameter("hora"));
        objnota.setActividad(request.getParameter("actividad"));
        objnota.setEstado(request.getParameter("estado"));
        System.out.println("El ID es " + id);
        if (id == 0) {
            //Coloca el id
            int idNuevo = obtenerId(request);
            objnota.setId(idNuevo);

            lista.add(objnota);
            System.out.println(objnota.toString());

        } else {
            //edicion
            int pos = buscarPorIndice(request, id);
            lista.set(pos, objnota);
            System.out.println(objnota.toString());

        }

        System.out.println("Enviando as index");
        request.setAttribute("listagenda", lista);
        response.sendRedirect("index.jsp");
        
    }
    
        public int buscarPorIndice(HttpServletRequest request, int id) {
        HttpSession ses = request.getSession();
        List<Nota> lista = (List<Nota>) ses.getAttribute("listagenda");
        int pos = -1;
        if (lista != null) {
            for (Nota ele : lista) {
                ++pos;
                if (ele.getId() == id) {
                    break;
                }
            }
        }
        return pos;
    }

    public int obtenerId(HttpServletRequest request) {
        HttpSession ses = request.getSession();
        ArrayList<Nota> lista = (ArrayList<Nota>) ses.getAttribute("listagenda");
        int idn = 0;
        for (Nota ele : lista) {
            idn = ele.getId();
        }
        return idn + 1;
    }

}
