package modelo;

import java.awt.Image;

public class Catalogocategoria {
    private int idCategoria;
    private String nombre;
    private Image imagen;
private int id;
    public Catalogocategoria(String nombre, Image imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    // Getters y Setters
    
    public int getId() {
    return id;
}
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
}