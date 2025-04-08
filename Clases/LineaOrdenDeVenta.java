package com.frutilla.models.Venta;

import com.frutilla.models.Inventario.*;

public class LineaOrdenDeVenta {
    
    //ATRIBUTOS
    private int idLineaVenta;
    private int cantidad;
    private double subtotal;
    private Producto producto;

    // CONSTRUCTORES
    
    public LineaOrdenDeVenta(int idLineaVenta, int cantidad, double subtotal, Producto producto) {
        this.idLineaVenta = idLineaVenta;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = new Producto(producto);
    } 
    
    public LineaOrdenDeVenta(){
        this.idLineaVenta = 0;
        this.cantidad = 0;
        this.subtotal = 0;
        this.producto = new Producto();
    }
    
    //GETTERS AND SETTERS
    
    public int getIdLineaVenta() {
        return idLineaVenta;
    }    
    public void setIdLineaVenta(int idLineaVenta) {
        this.idLineaVenta = idLineaVenta;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    // METODOS
    
    public double calcularSubtotal(double precioUnitario) {
        this.subtotal = this.cantidad * precioUnitario;
        return this.subtotal;
    }

    @Override
    public String toString() {
        return "LineaDeOrdenVenta{" +
                "idLineaVenta=" + idLineaVenta +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }
}

