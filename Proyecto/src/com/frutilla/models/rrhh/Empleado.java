package com.frutilla.models.rrhh;
import java.time.*;

public class Empleado extends Persona{
    private int idEmpleado;
    private static int correlativo = 1;
    private LocalDate fechaContrato;
    private double salario;
    // Este podriamos considerarlo un enum (manana o tarde) --> o solo un bool false si es manan o true si es tarde - gandy recomendacion 
    private boolean turnoTrabajo;
	private boolean estado;
	

    public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno,
    String correoElectronico, String telefono, LocalDate fechaContrato,
    double salario, String usuarioSistema, String contraSistema){
        super(nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, usuarioSistema, contraSistema);
        this.fechaContrato = fechaContrato;
        this.salario = salario;
        this.idEmpleado = correlativo;
        this.turnoTrabajo = false;
		this.estado = false;
        correlativo++;
    }
	
	public Empleado(){
	}
	
	@Override
	public String toString(){
		return "Id: " + String.valueOf(idEmpleado) + ", " + super.toString() + ", Salario: " + String.valueOf(salario) + "Estado: " + estado+ "\n";
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

    public boolean getTurnoTrabajo() {
        return turnoTrabajo;
    }

    public void setTurnoTrabajo(boolean turnoTrabajo) {
        this.turnoTrabajo = turnoTrabajo;
    }
	
	public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
	
}
