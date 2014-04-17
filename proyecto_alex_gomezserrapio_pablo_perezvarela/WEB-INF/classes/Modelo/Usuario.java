

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
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	public void setHistorialCompras(ArrayList<Compra> historialCompras) {
		this.historialCompras = historialCompras;
	}
	// Getters
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public String getMail() {
		return this.mail;
	}
	public boolean getVip() {
		return this.vip;
	}
	public boolean getAdmin() {
		return this.admin;
	}
	public Carrito getCarrito() {
		return this.carrito;
	}
	public ArrayList<Compra> getHistorialCompras() {
		return this.historialCompras;
	}
}
