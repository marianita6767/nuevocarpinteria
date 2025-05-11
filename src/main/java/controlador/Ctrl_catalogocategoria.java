package controlador;

import java.awt.Graphics2D;
import modelo.Catalogocategoria;
import modelo.Conexion;
import java.sql.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Ctrl_catalogocategoria {
    private Connection con = null;

    public Ctrl_catalogocategoria() {
        con = Conexion.getConnection();
    }

    // Método para insertar una nueva categoría en la base de datos
    public boolean insertarCategoria(Catalogocategoria categoria) {
        String sql = "INSERT INTO categoriacatalogo(nombre, imagen) VALUES(?, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Convertir la imagen a bytes para almacenarla en la base de datos
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage bufferedImage = new BufferedImage(
                categoria.getImagen().getWidth(null), 
                categoria.getImagen().getHeight(null), 
                BufferedImage.TYPE_INT_RGB
            );
            
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(categoria.getImagen(), 0, 0, null);
            g2d.dispose();
            
            ImageIO.write(bufferedImage, "jpg", baos);
            byte[] imagenBytes = baos.toByteArray();
            
            ps.setString(1, categoria.getNombre());
            ps.setBytes(2, imagenBytes);
            
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerado = rs.getInt(1);
                        // Puedes usar el ID generado si lo necesitas
                    }
                }
                return true;
            }
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar categoría: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Método para actualizar una categoría existente
    public boolean actualizarCategoria(Catalogocategoria categoria) {
        String sql = "UPDATE categoriacatalogo SET nombre = ?, imagen = ? WHERE idCategoria = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            // Convertir la imagen a bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage bufferedImage = new BufferedImage(
                categoria.getImagen().getWidth(null), 
                categoria.getImagen().getHeight(null), 
                BufferedImage.TYPE_INT_RGB
            );
            
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(categoria.getImagen(), 0, 0, null);
            g2d.dispose();
            
            ImageIO.write(bufferedImage, "jpg", baos);
            byte[] imagenBytes = baos.toByteArray();
            
            ps.setString(1, categoria.getNombre());
            ps.setBytes(2, imagenBytes);
            ps.setInt(3, categoria.getIdCategoria());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar categoría: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Método para eliminar una categoría
    public boolean eliminarCategoria(int idCategoria) {
        String sql = "DELETE FROM categoriacatalogo WHERE idCategoria = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar categoría: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Método para obtener todas las categorías
    public List<Catalogocategoria> obtenerTodasCategorias() {
        List<Catalogocategoria> categorias = new ArrayList<>();
        String sql = "SELECT idCategoria, nombre, imagen FROM categoriacatalogo";
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                int id = rs.getInt("idCategoria");
                String nombre = rs.getString("nombre");
                
                // Convertir bytes de la imagen a Image
                byte[] imagenBytes = rs.getBytes("imagen");
                Image imagen = null;
                
                if (imagenBytes != null) {
                    ByteArrayInputStream bais = new ByteArrayInputStream(imagenBytes);
                    imagen = ImageIO.read(bais);
                }
                
                Catalogocategoria categoria = new Catalogocategoria(nombre, imagen);
                categoria.setIdCategoria(id);
                categorias.add(categoria);
            }
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener categorías: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        return categorias;
    }

    // Método para obtener una categoría por ID
    public Catalogocategoria obtenerCategoriaPorId(int idCategoria) {
        String sql = "SELECT nombre, imagen FROM categoriacatalogo WHERE idCategoria = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    byte[] imagenBytes = rs.getBytes("imagen");
                    Image imagen = null;
                    
                    if (imagenBytes != null) {
                        ByteArrayInputStream bais = new ByteArrayInputStream(imagenBytes);
                        imagen = ImageIO.read(bais);
                    }
                    
                    Catalogocategoria categoria = new Catalogocategoria(nombre, imagen);
                    categoria.setIdCategoria(idCategoria);
                    return categoria;
                }
            }
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener categoría: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}