package com.frutilla.models.Venta;

import java.time.*;
import java.util.ArrayList;

public class ComprobantePago {
    
    // ATRIBUTOS
    private int idComprobante;
    private int numeroArticulos;
    private double subtotal;
    private double montoIGV;
    private double total;
    private LocalDate fecha;
    private FormaDePago formaPago;
    private static int correlativo = 1;
	private OrdenVenta orden;
    // CONSTRUCTORES
    
    public ComprobantePago(){
        this.fecha = LocalDate.now();
        this.idComprobante = 0;
        this.numeroArticulos = 0;
        this.subtotal = 0;
        this.montoIGV = 0;
        this.total  = 0;
    }
    public ComprobantePago(int idComprobante, int numeroArticulos, double subtotal, double montoIGV, double total, LocalDate fecha,
	OrdenVenta orden) {
        this.idComprobante = idComprobante;
        this.numeroArticulos = numeroArticulos;
        this.subtotal = subtotal;
        this.montoIGV = montoIGV;
        this.total = total;
        this.fecha = fecha;
		this.orden=new OrdenVenta(orden);
    }
    
    //GETTERS AND SETTERS
    
    public int getIdComprobante() {
        return idComprobante;
    }

    
    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    
    public int getNumeroArticulos() {
        return numeroArticulos;
    }

    
    public void setNumeroArticulos(int numeroArticulos) {
        this.numeroArticulos = numeroArticulos;
    }

    
    public double getSubtotal() {
        return subtotal;
    }

    
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    
    public double getMontoIGV() {
        return montoIGV;
    }

    
    public void setMontoIGV(double montoIGV) {
        this.montoIGV = montoIGV;
    }

    
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
   
    public LocalDate getFecha() {
		LocalDate copia = LocalDate.of(fecha.getYear(), fecha.getMonth(),
		fecha.getDayOfMonth());
        return copia;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    //METODOS
    
    public void calcularTotal(){
        //agregar recorrida y suma de array en subtotal
        montoIGV = subtotal*0.18;
        total = subtotal + montoIGV;
    }

    @Override
    public String toString() {
        return "Comprobante_Pago{" +
                "idComprobante=" + idComprobante +
                ", numeroArticulos=" + numeroArticulos +
                ", subtotal=" + subtotal +
                ", montoIGV=" + montoIGV +
                ", total=" + total +
                ", fecha=" + fecha +
                '}';
    }
    
}

