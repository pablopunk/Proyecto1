
package Modelo;

public class CD {
    private String id;
    private String nombre;
    private String autor;
    private String pais;
    private float precio;
    
    public CD() {
        this.id = "";
    	this.nombre = "";
    	this.autor = "";
    	this.pais = "";
    	this.precio = 0.0f;
    }
    
    public CD (String id, String nombre, String autor, String pais, float precio) {
        this.id = id;
    	this.nombre = nombre;
    	this.autor = autor;
    	this.pais = pais;
    	this.precio = precio;
    }

    public String getId() {
        return this.id;
    }
    
    public String getNombre() {
    	return this.nombre;
    }
    
    public String getAutor() {
    	return this.autor;
    }
    
    public String getPais() {
    	return this.pais;
    }
    
    public float getPrecio() {
    	return this.precio;
    }
    
    public void setId (String id) {
        this.id = id;
    }

    public void setNombre (String nombre) {
    	this.nombre = nombre;
    }
    
    public void setAutor (String autor) {
    	this.autor = autor;
    }
    
    public void setPais (String pais) {
    	this.pais = pais;
    }
    
    public void setPrecio (float precio) {
    	if (precio > 0)
    		this.precio = precio;
    	else
    		this.precio = 0.0f;
    }
}
