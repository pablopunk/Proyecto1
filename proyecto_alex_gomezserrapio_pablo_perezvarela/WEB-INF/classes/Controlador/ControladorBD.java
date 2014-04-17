
package Controlador;

import java.sql.*;
import Modelo.*;
import java.util.*;

public class ControladorBD {

	protected static Connection obtenerConexionBD() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

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
    protected static boolean validarUsuario(String username, String password) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
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

    // Devuelve el CD asociado a un producto de un pedido
    protected static CD obtenerProducto(int id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
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
            cd.setNombre(cortarNombre(descripcionCD));
            cd.setAutor(cortarAutor(descripcionCD));
            cd.setPrecio(cortarPrecio(descripcionCD));
            cd.setPais(cortarPais(descripcionCD));
        }

        return cd;
    }

    // Devuelve el producto y la cantidad de una compra concreta
    protected static ProductoCarrito obtenerProductoCompra(int id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Connection con = obtenerConexionBD();
        Statement statement = con.createStatement();
        String query = "SELECT * FROM productos_pedido WHERE compra='" + id + "';";
        ResultSet resultado = null;
        ProductoCarrito producto = new ProductoCarrito();
        try {
            resultado = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        while (resultado.next()) {
            producto.setCd(obtenerProducto(resultado.getInt("id")));
            producto.setCantidad(resultado.getInt("cantidad"));
        }

        return producto;
    }

    // Devuelve las compras de un usuario
    protected static ArrayList<Compra> obtenerHistorialCompras(String username) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Connection con = obtenerConexionBD();
        Statement statement = con.createStatement();
        String query = "SELECT * FROM compras WHERE username='" + username + "';";
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
            nuevaCompra.setId(resultado.getString("id"));
            nuevaCompra.setFecha(resultado.getDate("fecha"));
            nuevaCompra.setValoracion(resultado.getInt("valoracion"));
            nuevaCompra.setComentarios(resultado.getString("comentarios"));
            nuevaCompra.setPrecio(resultado.getFloat("precio"));
            nuevaCompra.setProducto(obtenerProductoCompra(resultado.getInt("id")));
            compras.add(nuevaCompra);
        }

        return compras;
    }

    protected static Usuario obtenerUsuario(String username) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
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
            usuario.setVip(resultado.getBoolean("vip"));
            usuario.setAdmin(resultado.getBoolean("admin")); 
            usuario.setHistorialCompras(obtenerHistorialCompras(resultado.getString("username"))); 
        }

        return usuario;
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