package com.frutilla.models.local;

import java.time.LocalDate;
import java.time.LocalTime;

public class Notificacion {

    private int idNotificacion;
    private LocalDate fecha;
    private LocalTime hora;
    private String titulo;
    private String descripcion;
    private char tipoReceptor; // 'C' para Cliente, 'S' para Supervisor
    private int idCliente;
    private int idSupervisor;

    // Constructor vacío
    public Notificacion() {
    }

    // Constructor completo
    public Notificacion(int idNotificacion, LocalDate fecha, LocalTime hora, String titulo, String descripcion, char tipoReceptor, int idCliente, int idSupervisor) {
        this.idNotificacion = idNotificacion;
        this.fecha = fecha;
        this.hora = hora;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipoReceptor = tipoReceptor;
        this.idCliente = idCliente;
        this.idSupervisor = idSupervisor;
    }

    // Constructor parcial sin ID general
    public Notificacion(LocalDate fecha, LocalTime hora, String titulo, String descripcion, char tipoReceptor, int idCliente, int idSupervisor) {
        this.fecha = fecha;
        this.hora = hora;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipoReceptor = tipoReceptor;
        this.idCliente = idCliente;
        this.idSupervisor = idSupervisor;
    }

    // Getters y setters
    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
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

    public void enviarNotificacion() {
        System.out.println("Enviando notificación:");
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
        System.out.println("Título: " + titulo);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Tipo de Receptor: " + tipoReceptor);
        if (tipoReceptor == 'C') {
            System.out.println("ID Cliente: " + idCliente);
        } else if (tipoReceptor == 'S') {
            System.out.println("ID Supervisor: " + idSupervisor);
        } else {
            System.out.println("Tipo de receptor no reconocido.");
        }
        System.out.println("Notificación enviada correctamente.");
    }
}