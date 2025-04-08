package com.frutilla.models.Empleado;
import java.time.*;
import com.frutilla.models.Venta.*;

public class Empleado{
    private int idEmpleado;
    private static int correlativo = 1;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoElectronico;
    private String telefono;
    private LocalDate fechaContrato;
    private double salario; 
    private String usuarioSistema;
    private String contraSistema;
    // Este podriamos considerarlo un enum (manana o tarde) --> o solo un bool false si es manan o true si es tarde - gandy recomendacion 
    private boolean turnoTrabajo;

    public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno,
    String correoElectronico, String telefono, LocalDate fechaContrato,
    double salario, String usuarioSistema, String contraSistema){
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.fechaContrato = fechaContrato;
        this.salario = salario;
        this.usuarioSistema = usuarioSistema;
        this.contraSistema = contraSistema;
        this.idEmpleado = correlativo;
	this.turnoTrabajo = false;
        correlativo++;
    }
	
	public Empleado(){
	}

	//para asignar turno  -- nuevo agregado
	public void asignarNoche(){
		this.turnoTrabajo = true; 
	} 
	
	public void asignarManana(){
		this.turnoTrabajo = false; 
	}

	
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public static int getCorrelativo() {
        return correlativo;
    }

    public static void setCorrelativo(int aCorrelativo) {
        correlativo = aCorrelativo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaContrato() {
		LocalDate copia = LocalDate.of(fechaContrato.getYear(), 
		fechaContrato.getMonth(), fechaContrato.getDayOfMonth());
        return copia;
    }

    public void setFechaContrato(LocalDate fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(String usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public String getContraSistema() {
        return contraSistema;
    }

    public void setContraSistema(String contraSistema) {
        this.contraSistema = contraSistema;
    }

    public boolean getTurnoTrabajo() {
        return turnoTrabajo;
    }

    public void setTurnoTrabajo(boolean turnoTrabajo) {
        this.turnoTrabajo = turnoTrabajo;
    }
}
