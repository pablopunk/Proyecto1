
package Controlador;

import java.sql.*;
import Modelo.*;
import java.util.*;

public class ControladorBD {

	public static Connection obtenerConexionBD() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Connection con = null;
        java.util.Properties conProps;

        conProps = new java.util.Properties();
        conProps.put("user", "dawa");
        conProps.put("password", "dawa");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Proyecto1", conProps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    // Devuelve TRUE si el usuario y su contraseña coinciden
    public static boolean validarUsuario(String username, String password) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Connection con = obtenerConexionBD();
        Statement statement = con.createStatement();
        String query = "SELECT password FROM usuarios WHERE username='" + username + "';";
        ResultSet resultado = null;
        String password_valida = "";
        try {
            resultado = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        while (resultado.next()) {
            password_valida = resultado.getString("password");
        }

        if (password.equals(password_valida)) {
        	return true;
        }

        return false;
    }
	
	// Devuelve TRUE si el usuario es admin
	public static boolean esAdmin(String username)throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Connection con = obtenerConexionBD();
        Statement statement = con.createStatement();
        String query = "SELECT admin FROM usuarios WHERE username='" + username + "';";
        ResultSet resultado = null;
        String es_admin = "";
        try {
            resultado = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        while (resultado.next()) {
            es_admin = resultado.getString("admin");
        }

        if (es_admin.equals("1")) {
        	return true;
        }

        return false;
    }

    // Devuelve TRUE si el usuario tiene mas de 100 compras
    public static boolean esVip(String username) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Connection con = obtenerConexionBD();
        Statement statement = con.createStatement();
        String query = "SELECT precio FROM comprar WHERE username='" + username + "';";
        ResultSet resultado = null;
        float euros=0;
        try {
            resultado = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        while (resultado.next()) {
         euros += resultado.getFloat(0);
     }

     if (euros > 99) {
        return true;
    }

    return false;
}

    // Devuelve el CD asociado a un producto de un pedido
public static CD obtenerProducto(String id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    Connection con = obtenerConexionBD();
    Statement statement = con.createStatement();
    String query = "SELECT * FROM productos WHERE id='" + id + "';";
    ResultSet resultado = null;
    CD cd = new CD();

    try {
        resultado = statement.executeQuery(query);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return null;
    }

    while (resultado.next()) {
        String descripcionCD = resultado.getString("descripcion");
        cd.setId(resultado.getString("id"));
        cd.setNombre(cortarNombre(descripcionCD));
        cd.setAutor(cortarAutor(descripcionCD));
        cd.setPrecio(cortarPrecio(descripcionCD));
        cd.setPais(cortarPais(descripcionCD));
    }

    return cd;
}

    // Devuelve los productos y la cantidad de una compra
public static ArrayList<ProductoCarrito> obtenerProductosCompra(String fecha, String username) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    Connection con = obtenerConexionBD();
    Statement statement = con.createStatement();
    String query = "SELECT * FROM productos_pedido WHERE fechaCompra='" + fecha + "' AND username='" + username + "';";
    ResultSet resultado = null;
    ArrayList<ProductoCarrito> productos = new ArrayList<>();
    try {
        resultado = statement.executeQuery(query);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return null;
    }

    while (resultado.next()) {
        ProductoCarrito producto = new ProductoCarrito();
        producto.setCd(obtenerProducto(resultado.getString("producto")));
        producto.setCantidad(resultado.getInt("cantidad"));
        productos.add(producto);
    }

    return productos;
}

    // Devuelve las compras de un usuario
public static ArrayList<Compra> obtenerHistorialCompras(String username) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    Connection con = obtenerConexionBD();
    Statement statement = con.createStatement();
    String query = "SELECT * FROM comprar WHERE username='" + username + "';";
    ResultSet resultado = null;
    ArrayList<Compra> compras = new ArrayList<>();
    try {
        resultado = statement.executeQuery(query);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return null;
    }

    while (resultado.next()) {
        Compra nuevaCompra = new Compra();
        nuevaCompra.setUsername(resultado.getString("username"));
        nuevaCompra.setFecha(resultado.getString("fecha"));
        nuevaCompra.setValoracion(resultado.getInt("valoracion"));
        nuevaCompra.setComentarios(resultado.getString("comentarios"));
        nuevaCompra.setPrecio(resultado.getFloat("precio"));
        nuevaCompra.setProductos(obtenerProductosCompra(resultado.getString("fecha"),resultado.getString("username")));
        compras.add(nuevaCompra);
    }

    return compras;
}

public static Usuario obtenerUsuario(String username) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    Connection con = obtenerConexionBD();
    Statement statement = con.createStatement();
    String query = "SELECT * FROM usuarios WHERE username='" + username + "';";
    ResultSet resultado = null;
    Usuario usuario = new Usuario();
    try {
        resultado = statement.executeQuery(query);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return null;
    }

    while (resultado.next()) {
        usuario.setUsername(resultado.getString("username"));
        usuario.setPassword(resultado.getString("password"));
        usuario.setMail(resultado.getString("mail"));
        usuario.setAdmin(resultado.getBoolean("admin")); 
        usuario.setHistorialCompras(obtenerHistorialCompras(resultado.getString("username"))); 
    }

    return usuario;
}

public static ArrayList<ProductoCarrito> obtenerProductosStock() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    Connection con = obtenerConexionBD();
    Statement statement = con.createStatement();
    String query = "SELECT * FROM productos_stock;";
    ResultSet resultado = null;
    ArrayList<ProductoCarrito> productos = new ArrayList<>();

    try {
        resultado = statement.executeQuery(query);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return null;
    }

    while (resultado.next()) {
        ProductoCarrito producto = new ProductoCarrito();
        producto.setCd(obtenerProducto(resultado.getString("producto")));
        producto.setStock(resultado.getInt("cantidad"));
        productos.add(producto);
    }

    return productos;
}

public static int obtenerStockProducto (String id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    Connection con = obtenerConexionBD();
    Statement statement = con.createStatement();
    String query = "SELECT cantidad FROM productos_stock WHERE producto='"+id+"';";
    ResultSet resultado = null;
    int stock = 0;
    try {
        resultado = statement.executeQuery(query);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return -1;
    }

    while (resultado.next()) {
        stock = resultado.getInt("cantidad");
    }

    return stock;
}

public static void actualizarStockProducto (String id, int cantidadComprada) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    Connection con = obtenerConexionBD();
    Statement statement = con.createStatement();

    int cantidadAntigua = obtenerStockProducto(id);

    String query = "UPDATE productos_stock SET cantidad="+(cantidadAntigua-cantidadComprada)+" WHERE producto='"+id+"';";

    try {
        statement.executeUpdate(query);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

public static String insertarProductoCompra (String id, int cantidad, float precio, String fecha, String username) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    Connection con = obtenerConexionBD();
    Statement statement = con.createStatement();

    String query = "INSERT INTO productos_pedido VALUES ('"+id+"','"+cantidad+"',"+precio+",'"+fecha+"','"+username+"');";

    try {
        statement.executeUpdate(query);
        actualizarStockProducto(id,cantidad);
    } catch (SQLException e) {
        return e.getMessage();
    }
    return "ok";
}

public static String insertarProducto (String id, String titulo, String artista, String pais, String precio, String stock) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
	Connection con = obtenerConexionBD();
    Statement statement = con.createStatement();
	Statement statement2 = con.createStatement();
	int stockActual = 0;
	
	int stockInt = Integer.parseInt(stock);
	String query = "INSERT INTO productos VALUES ('"+id+"','"+titulo+" | "+artista+" | "+pais+" | "+precio+"');";
	
	try{
		statement.executeUpdate(query);
		query = "INSERT INTO productos_stock VALUES ('"+id+"','"+stockInt+"');";
		statement2.executeUpdate(query);
	} catch (SQLException e){
		if (e.getMessage().contains("Duplicate")){
			stockActual = obtenerStockProducto(id);
			stockInt += stockActual;
			query = "UPDATE productos_stock SET cantidad="+stockInt+" WHERE producto='"+id+"';";
			statement2.executeUpdate(query);
			return "ok";
		}
	}
	return "ok";
}

public static ArrayList<ProductoCarrito> buscarProductos (String titulo, String artista, String pais, String precio) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{

  ArrayList<ProductoCarrito> productosBuscados = new ArrayList<ProductoCarrito>();
  float precioFloat;
  if (precio != "") precioFloat = Float.parseFloat(precio);
  else precioFloat = -1;
  
  ArrayList<ProductoCarrito> productosStock = obtenerProductosStock();
  for (ProductoCarrito producto : productosStock){
     CD cd = producto.getCd();
     if(titulo != ""){
        if(!titulo.equals(cd.getNombre())){
           continue;
       }
   }
   if(artista != ""){
    if(!artista.equals(cd.getAutor())){
       continue;
   }
}
if(pais != ""){
    if(!pais.equals(cd.getPais())){
       continue;
   }
}
if(precioFloat > 0){
    if(precioFloat < cd.getPrecio()){
       continue;
   }
}
productosBuscados.add(producto);
}

return productosBuscados;

}

public static String insertarUsuario (String username, String password, String mail) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    Connection con = obtenerConexionBD();
    Statement statement = con.createStatement();

    String query = "INSERT INTO usuarios VALUES ('"+username+"','"+password+"','"+mail+"','0');";

    try {
        statement.executeUpdate(query);
    } catch (SQLException e) {
        return e.getMessage();
    }
    return "ok";
}

public static String insertarCompra (String username, float precioTotal, String fecha) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    Connection con = obtenerConexionBD();
    Statement statement = con.createStatement();

    String query = "INSERT INTO comprar(fecha,precio,username) VALUES ('"+fecha+"',"+precioTotal+",'"+username+"'); ";

    try {
        statement.executeUpdate(query);
    } catch (SQLException e) {
        return e.getMessage();
    }
    return "ok";
}

private static float cortarPrecio (String descripcionCD) {
    StringTokenizer t = new StringTokenizer(descripcionCD,"|");
    t.nextToken();
    t.nextToken();
    t.nextToken();
    String precioString = t.nextToken();
    precioString = precioString.replace('$',' ').trim();
    return Float.parseFloat(precioString);
}

private static String cortarPais (String descripcionCD) {
    StringTokenizer t = new StringTokenizer(descripcionCD,"|");
    t.nextToken();
    t.nextToken();
    String paisString = t.nextToken();
    paisString = paisString.replace('$',' ').trim();
    return paisString;
}

private static String cortarAutor (String descripcionCD) {
    StringTokenizer t = new StringTokenizer(descripcionCD,"|");
    t.nextToken();
    String autorString = t.nextToken();
    autorString = autorString.replace('$',' ').trim();
    return autorString;
}

private static String cortarNombre (String descripcionCD) {
    StringTokenizer t = new StringTokenizer(descripcionCD,"|");
    String nombreString = t.nextToken();
    nombreString = nombreString.replace('$',' ').trim();
    return nombreString;
}

}