/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.catalogoproducto;

/**
 *
 * @author buitr
 */
public class ctrl_productocatalogo {

    public static class productoConDetalles {

        private catalogoproducto producto;
        private int idproducto;
        private String nombre;
        private String categoria;

        public productoConDetalles(catalogoproducto producto, String nombre, String categoria) {
            this.producto = producto;
            this.nombre = nombre;
            this.categoria = categoria;
        }

        public catalogoproducto getProducto() {
            return producto;
        }

        public String getNombre() {
            return nombre;
        }

        public String getCategoria() {
            return categoria;
        }

        public int getIdproducto() {
            return idproducto;
        }

    }

public boolean insertarProducto(catalogoproducto producto) {
    // USAR LOS NOMBRES EXACTOS QUE MUESTRA 'SHOW CREATE TABLE'
    String sql = "INSERT INTO catalogo_producto(nombre, categoria_id, imagen) VALUES (?, ?, ?)";
    
    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        // 1. Nombre del producto
        stmt.setString(1, producto.getNombre());
        
        // 2. ID de categoría (convertir a entero)
        stmt.setInt(2, Integer.parseInt(producto.getCategoria()));
        
        // 3. Imagen (manejo de nulos)
        if (producto.getImagen() != null && producto.getImagen().length > 0) {
            stmt.setBytes(3, producto.getImagen());
        } else {
            stmt.setNull(3, java.sql.Types.BLOB);
        }

        // Ejecutar y mostrar resultado
        int result = stmt.executeUpdate();
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "✅ Producto guardado");
            return true;
        }
    } catch (SQLException e) {
        mostrarErrorDetallado(e);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, 
            "Formato de categoría inválido", "Error", JOptionPane.ERROR_MESSAGE);
    }
    return false;
}

private void mostrarErrorDetallado(SQLException e) {
    String mensaje = "🔴 Error SQL (" + e.getErrorCode() + "):\n";
    
    if (e.getMessage().contains("unknown column")) {
        mensaje += "COLUMNA INCORRECTA: Verifica los nombres de columnas:\n"
                 + "1. Ejecuta 'SHOW CREATE TABLE catalogo_producto'\n"
                 + "2. Ajusta la consulta SQL en insertarProducto()";
    } else {
        mensaje += e.getMessage();
    }
    
    JOptionPane.showMessageDialog(null, mensaje, "Error de BD", JOptionPane.ERROR_MESSAGE);
}
  

// Modifica el método obtenerproducto
public List<productoConDetalles> obtenerproducto() {
    List<productoConDetalles> lista = new ArrayList<>();
    String sql = "SELECT cp.idproducto, cp.nombre, cp.img, c.idCategoria, c.nombre AS nombre_categoria "
               + "FROM catalogo_producto cp "
               + "LEFT JOIN categoria c ON cp.Categoria_idCategoria = c.idCategoria";

    try (Connection con = Conexion.getConnection(); 
         PreparedStatement stmt = con.prepareStatement(sql); 
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            catalogoproducto producto = new catalogoproducto(
                rs.getInt("idproducto"),
                rs.getString("nombre"),
                rs.getString("nombre_categoria"), // Mostramos el nombre
                rs.getBytes("img")
            );

            lista.add(new productoConDetalles(
                producto,
                producto.getNombre(),
                rs.getString("nombre_categoria")
            ));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al obtener productos: " + e.getMessage());
        e.printStackTrace();
    }
    return lista;
}
    
    

  

    public boolean actualizar(catalogoproducto producto) {
        String sql = "UPDATE catalogo_producto SET nombre = ?, Categoria_idCategoria = ?, img = ? "
                + "WHERE idproducto = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCategoria()); // Asegúrate que coincida con el nombre de tu método getter
            stmt.setBytes(3, producto.getImagen());
            stmt.setInt(4, producto.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM catalogo_producto WHERE idproducto = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idProducto);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
