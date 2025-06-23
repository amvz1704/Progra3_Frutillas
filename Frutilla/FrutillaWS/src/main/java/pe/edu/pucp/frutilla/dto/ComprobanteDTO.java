/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.dto;

import java.time.LocalDate;
import pe.edu.pucp.frutilla.models.venta.ComprobantePago;
import pe.edu.pucp.frutilla.models.venta.FormaDePago;

/**
 *
 * @author Desktop
 */
public class ComprobanteDTO {
    private int idComprobante;
    private int numeroArticulos;
    private double subtotal;
    private double montoIGV;
    private double total;
    private FormaDePago formaPago;
    private String fechaStr;

    public ComprobanteDTO() {
    }
    
    public ComprobanteDTO (ComprobantePago base){
        idComprobante=base.getIdComprobante();
        numeroArticulos=base.getNumeroArticulos();
        subtotal=base.getSubtotal();
        montoIGV=base.getMontoIGV();
        total=base.getTotal();
        formaPago=base.getFormaPago();
        fechaStr=base.getFecha().toString();
    }

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

    public FormaDePago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaDePago formaPago) {
        this.formaPago = formaPago;
    }

    public String getFechaStr() {
        return fechaStr;
    }

    public void setFechaStr(String fechaStr) {
        this.fechaStr = fechaStr;
    }
    
    public ComprobantePago convertirAComprobante(){
        ComprobantePago nuevo = new ComprobantePago();
        nuevo.setIdComprobante(idComprobante);
        nuevo.setFecha(LocalDate.parse(fechaStr));
        nuevo.setFormaPago(formaPago);
        nuevo.setMontoIGV(montoIGV);
        nuevo.setSubtotal(subtotal);
        nuevo.setTotal(total);
        nuevo.setNumeroArticulos(numeroArticulos);
        return nuevo;
    }
}
