
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
                    session.setAttribute("user", usuario);
                    gotoPage("/perfil.jsp", request, response);
                } else {
                    mostrarPaginaError("La combinación de usuario y contraseña es errónea", "iniciar_sesion.jsp", session, request, response);
                }
            } catch (Exception e) {
                mostrarPaginaError(e.getMessage(), "iniciar_sesion.jsp", session, request, response);
            }
        }

		
		// Petición desde registro.jsp
		if (request.getParameter("registro") != null) {
            String username = request.getParameter("form_username");
            String password = request.getParameter("form_password");
			String password2 = request.getParameter("form_password2");
			String mail = request.getParameter("form_mail");
			String ok;
            try {
                if (username != "" && password != "" && password2 != "" && mail != "" && password.equals(password2)) {
                    ok = ControladorBD.insertarUsuario(username,password,mail);
                    if (ok!="ok"){
						mostrarPaginaError(ok,"/registro.jsp", session, request, response);
					}
					else gotoPage("/index.jsp", request, response);
                } else {
                    mostrarPaginaError("Algún dato es incorrecto o las contraseñas no coinciden", "/registro.jsp", session, request, response);
                }
            } catch (Exception e) {
                mostrarPaginaError(e.getMessage(), "registro.jsp", session, request, response);
            }
        }
        // Peticion desde el perfil.jsp
        if (request.getParameter("cerrar_sesion") != null) {
            try {
                if (session.getAttribute("user") != null) {
                    session.removeAttribute("user");
                    session.removeAttribute("carrito");
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
