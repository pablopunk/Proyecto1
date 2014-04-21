
package Modelo;

public class ProductoCarrito {
    private CD cd;
    private int cantidad;
    private int stock;
    
    public ProductoCarrito() {
    	this.cd = new CD();
    	this.cantidad = 0;
    }
    
    public ProductoCarrito(CD cd, int cantidad) {
    	this.cd = cd;
    	this.cantidad = cantidad;
    }
    
    public void setCd (CD cd) {
    	this.cd = cd;
    }
    
    public void setCantidad (int cantidad) {
    	this.cantidad = cantidad;
    }

    public void setStock (int stock) {
        this.stock = stock;
    }
    
    public CD getCd() {
		return this.cd;
    }
    
    public int getCantidad() {
    	return this.cantidad;
    }

    public int getStock() {
        return this.stock;
    }
    
    public float getPrecioTotal() {
    	float precio = (this.cantidad * this.cd.getPrecio());
    	
    	return precio;
    }
    
}