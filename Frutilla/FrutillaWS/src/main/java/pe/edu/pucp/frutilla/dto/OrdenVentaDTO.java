/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import pe.edu.pucp.frutilla.models.venta.EstadoVenta;
import pe.edu.pucp.frutilla.models.venta.OrdenVenta;


public class OrdenVentaDTO {
    private int idOrdenVenta;  
    private String fechaStr;
    private String horaStr;
    private String descripcion; 
    private double montoTotal; 
    private EstadoVenta estado; 
    private boolean entregado; 
    private int idEmpleado;
    private int idLocal;
    private int idComprobante;
    private int idCliente;

    public OrdenVentaDTO() {
        this.entregado = false;
        this.estado = EstadoVenta.PROCESO;
    }
    
    public OrdenVentaDTO(OrdenVenta base){
        idOrdenVenta=base.getIdOrdenVenta();
        idLocal=base.getIdLocal();
        idCliente=base.getIdCliente();
        idComprobante=base.getIdComprobante();
        idEmpleado=base.getIdEmpleado();
        fechaStr=base.getFecha().toString();
        horaStr=base.getHoraFinEntrega().toString();
        descripcion=base.getDescripcion();
        montoTotal=base.getMontoTotal();
        estado=base.getEstado();
        entregado=base.getEntregado();
    }

    public int getIdOrdenVenta() {
        return idOrdenVenta;
    }

    public void setIdOrdenVenta(int idOrdenVenta) {
        this.idOrdenVenta = idOrdenVenta;
    }

    public String getFechaStr() {
        return fechaStr;
    }

    public void setFechaStr(String fechaStr) {
        this.fechaStr = fechaStr;
    }

    public String getHoraStr() {
        return horaStr;
    }

    public void setHoraStr(String horaStr) {
        this.horaStr = horaStr;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public EstadoVenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoVenta estado) {
        this.estado = estado;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public OrdenVenta convertirAOrdenVenta(){
        OrdenVenta ordenFinal = new OrdenVenta();
        ordenFinal.setIdOrdenVenta(idOrdenVenta);
        ordenFinal.setIdLocal(idLocal);
        ordenFinal.setIdCliente(idCliente);
        ordenFinal.setIdComprobante(idComprobante);
        ordenFinal.setIdEmpleado(idEmpleado);
        ordenFinal.setDescripcion(descripcion);
        ordenFinal.setEntregado(entregado);
        ordenFinal.setEstado(estado);
        ordenFinal.setFecha(LocalDate.parse(fechaStr));
        ordenFinal.setHoraFinEntrega(LocalTime.parse(horaStr));
        ordenFinal.setMontoTotal(montoTotal);
        return ordenFinal;
    }
}
