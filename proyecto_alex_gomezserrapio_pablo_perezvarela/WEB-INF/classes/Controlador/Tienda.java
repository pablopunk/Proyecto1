
package Controlador;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import Modelo.*;

public class Tienda extends HttpServlet {
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

        // Petición de la tienda
		if (request.getParameter("mostrar_tienda") != null) {

			try {
				// Mostrar catalogo tienda
				ArrayList<ProductoCarrito> productos = ControladorBD.obtenerProductosStock();
				session.setAttribute("productos", productos);
				gotoPage("/tienda.jsp", request, response);
			} catch (Exception e) {
				mostrarPaginaError(e.getMessage(), "iniciar_sesion.jsp", session, request, response);
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