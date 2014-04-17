

package Modelo;

import java.util.ArrayList;
import java.sql.Date;

public class Compra {
	private String id;
	private Date fecha;
	private int valoracion;
	private String comentarios;
	private float precio;
	private ProductoCarrito producto;

	public Compra() {
	}

	// Setters
	public void setId (String id) {
		this.id = id;
	}
	public void setFecha (Date fecha) {
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
	public String getId () {
		return this.id;
	}
	public Date getFecha () {
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