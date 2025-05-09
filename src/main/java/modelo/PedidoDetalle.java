/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ZenBook
 */
public class PedidoDetalle {
    private int idDetallePedido;
    private String descripcion;
    private int cantidad; 
    private String medida;
    private double precioUnitario;
    private double subtotal;
    private double total;
    private int pedidoIdPedido; 

// Constructor vacío
    public PedidoDetalle() {
    }

    public PedidoDetalle(int idDetallePedido, String descripcion, int cantidad, String medida, double precioUnitario, double subtotal, double total, int pedidoIdPedido) {
        this.idDetallePedido = idDetallePedido;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.medida = medida;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.total = total;
        this.pedidoIdPedido = pedidoIdPedido;
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getPedidoIdPedido() {
        return pedidoIdPedido;
    }

    public void setPedidoIdPedido(int pedidoIdPedido) {
        this.pedidoIdPedido = pedidoIdPedido;
    }
    
    
}

