
package Controlador;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import Modelo.*;

public class GestionUsuarios extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        // Petición desde iniciar_sesion.jsp
        if (request.getParameter("iniciar_sesion") != null) {
            String username = request.getParameter("form_username");
            String password = request.getParameter("form_password");
            try {
                if (ControladorBD.validarUsuario(username, password)) {
                    Usuario usuario = ControladorBD.obtenerUsuario(username);
                    session.setAttribute("username", usuario.getUsername());
                    gotoPage("/perfil.jsp", request, response);
                } else {
                    mostrarPaginaError("La combinación de usuario y contraseña es errónea", "iniciar_sesion.jsp", session, request, response);
                }
            } catch (Exception e) {
                mostrarPaginaError(e.getMessage(), "iniciar_sesion.jsp", session, request, response);
            }
        }

        // Peticion desde el perfil.jsp
        if (request.getParameter("cerrar_sesion") != null) {
            try {
                if (session.getAttribute("username") != null) {
                    session.removeAttribute("username");
                }
                gotoPage("/index.jsp", request, response);
            } catch (Exception e) {
                mostrarPaginaError(e.getMessage(), "index.jsp", session, request, response);
            }
        }
        if (request.getParameter("mostrar_historial_compras") != null) {
            try {
                // MOSTRAR HISTORIAL DE COMPRAS
            } catch (Exception e) {
                mostrarPaginaError(e.getMessage(), "perfil.jsp", session, request, response);
            }
        }

    }

    
    private void mostrarPaginaError (String error, String paginaVolver, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session.setAttribute("error", error);
        session.setAttribute("volver", paginaVolver);
        gotoPage("/error.jsp", request, response); // Imprimo el error en una pagina
    }

    private void gotoPage (String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
