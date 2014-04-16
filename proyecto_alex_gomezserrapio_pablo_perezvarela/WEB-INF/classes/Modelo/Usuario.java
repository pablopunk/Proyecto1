

package Modelo;

import java.util.ArrayList;

public class Usuario {
	private String username;
	private String password;
	private String mail;
	private boolean vip;
	private boolean admin;
	private Carrito carrito;
	private ArrayList<Compra> historialCompras;
	
	public Usuario() {
		this.carrito = new Carrito();
		this.historialCompras = new ArrayList<>();
	}

	// Setters
	private void setUsername(String username) {
		this.username = username;
	}
	private void setPassword(String password) {
		this.password = password;
	}
	private void setMail(String mail) {
		this.mail = mail;
	}
	private void setVip(boolean vip) {
		this.vip = vip;
	}
	private void setAdmin(boolean admin) {
		this.admin = admin;
	}
	private void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	private void setHistorialCompras(ArrayList<Compra> historialCompras) {
		this.historialCompras = historialCompras;
	}
	// Getters
	private String getUsername() {
		return this.username;
	}
	private String getPassword() {
		return this.password;
	}
	private String getMail() {
		return this.mail;
	}
	private boolean getVip() {
		return this.vip;
	}
	private boolean getAdmin() {
		return this.admin;
	}
	private Carrito getCarrito() {
		return this.carrito;
	}
	private ArrayList<Compra> getHistorialCompras() {
		return this.historialCompras;
	}
}
