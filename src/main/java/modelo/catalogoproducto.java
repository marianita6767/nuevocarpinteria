/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author buitr
 */
public class catalogoproducto {
    private int id;
    private String nombre;
    private String categoria; // Ahora puede ser ID o nombre según el contexto
    private byte[] imagen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public catalogoproducto(int id, String nombre, String categoria, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.imagen = imagen;
    }
    
  
   public int getIdCategoriaInt() {
    try {
        return Integer.parseInt(this.categoria); // Convierte el String a int
    } catch (NumberFormatException e) {
        return 0; // Valor por defecto o maneja el error como necesites
    }
}
}
