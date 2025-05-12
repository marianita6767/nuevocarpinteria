package modelo;

public class Caja {
    private int id_codigo;
    private String fecha;
    private String descripcion;
    private double monto;
    private String categoria;

    public Caja(int id_codigo, String fecha, String descripcion, double monto, String categoria) {
        this.id_codigo = id_codigo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
        this.categoria = categoria;
    }

    public int getId_codigo() {
        return id_codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public String getCategoria() {
        return categoria;
    }
}