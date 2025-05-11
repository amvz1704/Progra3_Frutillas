package pe.edu.pucp.frutilla.models.rrhh;
import java.time.*;

public class Empleado extends Persona{
    private int idEmpleado;
    private LocalDate fechaContrato;
    private double salario;
    // Este podriamos considerarlo un enum (manana o tarde) --> o solo un bool false si es manan o true si es tarde - gandy recomendacion 
    private boolean turnoTrabajo;
    private char tipo;
	

    public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno,
    String correoElectronico, String telefono, LocalDate fechaContrato,
    double salario, String usuarioSistema, String contraSistema, char tipo){
        super(nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, usuarioSistema, contraSistema);
        this.fechaContrato = fechaContrato;
        this.salario = salario;
        this.idEmpleado = -1;
        this.turnoTrabajo = false;
        this.tipo = tipo;
    }
	
	public Empleado(){ 
	}
	
	@Override
	public String toString(){
		return "Id: " + String.valueOf(idEmpleado) + ", " + super.toString() + ", Salario: " + String.valueOf(salario) + "\n";
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

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
	
}
