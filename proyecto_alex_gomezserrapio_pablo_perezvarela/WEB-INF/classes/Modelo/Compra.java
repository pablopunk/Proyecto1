

package Modelo;

import java.util.ArrayList;
import java.sql.*;

public class Compra {
	private String username;
	private String fecha;
	private float precio;
	private ArrayList<ProductoCarrito> productos;

	public Compra() {
	}

	// Setters
	public void setUsername (String username) {
		this.username = username;
	}
	public void setFecha (String fecha) {
		this.fecha = fecha;
	}
	public void setPrecio (float precio) {
		this.precio = precio;
	}
	public void setProductos (ArrayList<ProductoCarrito> productos) {
		this.productos = productos;
	}
	// Getters
	public String getUsername () {
		return this.username;
	}
	public String getFecha() {
		return this.fecha;
	}
	public float getPrecio () {
		return this.precio;
	}
	public ArrayList<ProductoCarrito> getProductos () {
		return this.productos;
	}
}