

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

	public int contiene (ProductoCarrito producto) {
		if (this.productos == null) {
			return -1;
		}

		ProductoCarrito iterator = null;
		for ( int i = 0; i < this.productos.size(); i++) {
			iterator = this.productos.get(i);
			if (iterator.getCd().getNombre().equals(producto.getCd().getNombre())) {
				return i;
			}
		}

		return -1;
	}

	public void aumentarCantidadProducto (int index, int suma) {
		ProductoCarrito producto = this.productos.get(index);
		producto.setCantidad(producto.getCantidad()+suma);
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