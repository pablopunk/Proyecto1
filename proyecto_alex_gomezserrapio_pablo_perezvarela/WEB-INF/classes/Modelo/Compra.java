

package Modelo;

import java.util.ArrayList;
import java.sql.*;

public class Compra {
	private String username;
	private String fecha;
	private int valoracion;
	private String comentarios;
	private float precio;
	private ProductoCarrito producto;

	public Compra() {
	}

	// Setters
	public void setUsername (String username) {
		this.username = username;
	}
	public void setFecha (String fecha) {
		this.fecha = fecha;
	}
	public void setValoracion (int valoracion) {
		this.valoracion = valoracion;
	}
	public void setComentarios (String comentarios) {
		this.comentarios = comentarios;
	}
	public void setPrecio (float precio) {
		this.precio = precio;
	}
	public void setProducto (ProductoCarrito producto) {
		this.producto = producto;
	}
	// Getters
	public String getUsername () {
		return this.username;
	}
	public String getFecha () {
		return this.fecha;
	}
	public int getValoracion () {
		return this.valoracion;
	}
	public String getComentarios () {
		return this.comentarios;
	}
	public float getPrecio () {
		return this.precio;
	}
	public ProductoCarrito getProducto () {
		return this.producto;
	}
}