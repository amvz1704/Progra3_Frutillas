/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.dto;

import java.time.LocalDate;
import pe.edu.pucp.frutilla.models.local.Notificacion;

public class NotificacionDTO {
    private int idNotificacion;
    private String fechaStr; //usado para poder pasar la info a front
    private String titulo;
    private String descripcion;
    private char tipoReceptor; // 'C' para Cliente, 'S' para Supervisor
    private int idCliente;
    private int idSupervisor;

    public NotificacionDTO() {
    }
    
    public NotificacionDTO(Notificacion temp){
        idNotificacion=temp.getIdNotificacion();
        idCliente=temp.getIdCliente();
        idSupervisor=temp.getIdSupervisor();
        titulo=temp.getTitulo();
        descripcion=temp.getDescripcion();
        tipoReceptor=temp.getTipoReceptor();
        fechaStr=temp.getFecha().toString();
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getFechaStr() {
        return fechaStr;
    }

    public void setFechaStr(String fechaStr) {
        this.fechaStr = fechaStr;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public char getTipoReceptor() {
        return tipoReceptor;
    }

    public void setTipoReceptor(char tipoReceptor) {
        this.tipoReceptor = tipoReceptor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(int idSupervisor) {
        this.idSupervisor = idSupervisor;
    }
    
    public Notificacion convertirANotificacion(NotificacionDTO base){
        Notificacion notificacion = new Notificacion();
        notificacion.setIdNotificacion(base.idNotificacion);
        notificacion.setIdCliente(base.idCliente);
        notificacion.setIdSupervisor(base.idSupervisor);
        notificacion.setDescripcion(base.descripcion);
        notificacion.setFecha(LocalDate.parse(base.fechaStr));
        notificacion.setTipoReceptor(base.tipoReceptor);
        notificacion.setTitulo(base.titulo);
        return notificacion;
    }
}
