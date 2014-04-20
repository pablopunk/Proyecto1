
package Controlador;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import javax.mail.*;
import javax.mail.internet.*;

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

		// Petición desde la tienda
		if (request.getParameter("comprar") != null) {
			@SuppressWarnings("unchecked")
			ArrayList<ProductoCarrito> productos = (ArrayList<ProductoCarrito>) session.getAttribute("productos");
			Carrito carrito = new Carrito();

			if (session.getAttribute("carrito") != null) {
				carrito = (Carrito) session.getAttribute("carrito");
			}
			
			try {
				// Comprobar usuario
				if (session.getAttribute("user") == null) {
					mostrarPaginaError("Debes hacer login antes de comprar en la tienda", "index.jsp", session, request, response);
					return;
				}
				// Comprobar cantidades correctas
				for (ProductoCarrito iterator : productos) {
					try {
						int c = Integer.parseInt(request.getParameter("cantidad_"+iterator.getCd().getId()));
						if (c > iterator.getStock()) {
							mostrarPaginaError("Solo hay "+iterator.getStock()+" unidades del CD "+iterator.getCd().getNombre()+" y has introducido "+c+". Por favor, ingresa una cantidad correcta.", "index.jsp", session, request, response);
							return;
						} else if (c < 0) {
							mostrarPaginaError("Has introducido cantidades negativas.", "index.jsp", session, request, response);
							return;
						}
						if (c > 0) {
							int index = carrito.contiene(iterator);
							if (index != -1) {
								carrito.aumentarCantidadProducto(index, c);
							} else {
								iterator.setCantidad(c);
								carrito.addProducto(iterator);
							}
						}
					} catch (Exception e) {
						mostrarPaginaError("Las cantidades introducidas no son correctas.", "index.jsp", session, request, response);
					}
				}
				session.setAttribute("carrito", carrito);
				gotoPage("/carrito.jsp", request, response);
			} catch (Exception e) {
				mostrarPaginaError(e.getMessage(), "index.jsp", session, request, response);
				return;
			}
		}

		// Peticion desde el carrito
		if (request.getParameter("finalizar_compra") != null) {
			try {
				@SuppressWarnings("unchecked")
				Carrito carrito = (Carrito) session.getAttribute("carrito");
				Usuario usuario = (Usuario) session.getAttribute("user");
				registrarCompra(usuario, carrito.getProductos(), session, request, response);
			} catch (Exception e) {
				mostrarPaginaError(e.getMessage(), "index.jsp", session, request, response);
				return;
			}
		}
	}

	private void registrarCompra (Usuario usuario, ArrayList<ProductoCarrito> productos, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
		// Fecha actual
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dateNow = formatter.format(currentDate.getTime());
		float precioTotal = 0;
		String ok;
		String username = usuario.getUsername();

		try {
			for (ProductoCarrito iterator : productos) {
				precioTotal += (iterator.getCd().getPrecio()*iterator.getCantidad());

				ok = ControladorBD.insertarProductoCompra(iterator.getCd().getId(),iterator.getCantidad(),iterator.getCd().getPrecio(),dateNow,username);
				if (!ok.equals("ok")) {
					mostrarPaginaError(ok, "index.jsp", session, request, response);
					return;
				}
			}

			// Ahora inserto la compra
			ok = ControladorBD.insertarCompra(username, precioTotal, dateNow);
			if (!ok.equals("ok")) {
				mostrarPaginaError(ok, "index.jsp", session, request, response);
				return;
			}

			// Y envio el correo
			enviarCorreoConfirmacion (usuario, precioTotal, session, request, response);
			gotoPage("/finalizar_compra.jsp", request, response);

		} catch (Exception e) {
			mostrarPaginaError(e.getMessage(), "index.jsp", session, request, response);
			return;
		}
	}

	private void enviarCorreoConfirmacion (Usuario usuario, float precioTotal, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException, NoSuchProviderException, MessagingException, AddressException {
		Properties mailProps = new Properties();
		mailProps.put("mail.smtp.from", "confirmacioncompra@gmail.com");
		mailProps.put("mail.smtp.host", "smtp.gmail.com");
		mailProps.put("mail.smtp.port", 465);
		mailProps.put("mail.smtp.auth", true);
		mailProps.put("mail.smtp.socketFactory.port", 465);
		mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		mailProps.put("mail.smtp.socketFactory.fallback", "false");
		mailProps.put("mail.smtp.starttls.enable", "true");

		Session mailSession = Session.getDefaultInstance(mailProps, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("confirmacioncompra@gmail.com", "thisismypassword");
			}

		});

		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress("confirmacioncompra@gmail.com"));
		String[] emails = { usuario.getMail() };
		InternetAddress dests[] = new InternetAddress[emails.length];
		for (int i = 0; i < emails.length; i++) {
			dests[i] = new InternetAddress(emails[i].trim().toLowerCase());
		}
		message.setRecipients(Message.RecipientType.TO, dests);
		message.setSubject("Musica para DAA - Confirmacion compra", "UTF-8");
		Multipart mp = new MimeMultipart();
		MimeBodyPart mbp = new MimeBodyPart();
		mbp.setContent("<h3>Hola "+usuario.getUsername()+"! Te enviamos este correo para confirmarte que acabas de realizar una compra por valor de $"+precioTotal+" en 'Musica para DAA'. Puedes consultar los detalles del pedido en tu perfil > Mostrar historial de compras. MUCHAS GRACIAS!</h3>", "text/html;charset=utf-8");
		mp.addBodyPart(mbp);
		message.setContent(mp);
		message.setSentDate(new java.util.Date());

		try {
			Transport.send(message);
		} catch (Exception e) {
			mostrarPaginaError(e.getMessage(), "index.jsp", session, request, response);
			return;
		}
	}

	private void mostrarPaginaError (String error, String paginaVolver, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.setAttribute("error", error);
		session.setAttribute("volver", paginaVolver);
        gotoPage("/error.jsp", request, response); // Imprimo el error en una pagina
        return;
    }

    private void gotoPage (String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
    	dispatcher.forward(request, response);
    	return;
    }
}