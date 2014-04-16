

package Modelo;

import java.util.ArrayList;

public class Carrito {
	private ArrayList<ProductoCarrito> productos;
	
	public Carrito() {
		this.productos = new ArrayList<ProductoCarrito>();
	}
	
	public void setProductos (ArrayList<ProductoCarrito> productos) {
		this.productos = productos;
	}
	
	public ArrayList<ProductoCarrito> getProductos() {
		return this.productos;
	}
	
	public void addProducto(ProductoCarrito producto) {
		if (this.productos == null)
			this.productos = new ArrayList<ProductoCarrito>();
			
		this.productos.add(producto);
	}
	
	public void removeProducto(int index) {
		if (index > -1) {
			this.productos.remove(index);
		}
	}
}