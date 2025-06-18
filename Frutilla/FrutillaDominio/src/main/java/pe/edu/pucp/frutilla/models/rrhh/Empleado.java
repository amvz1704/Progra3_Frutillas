package pe.edu.pucp.frutilla.models.rrhh;
//import java.time.*;
import java.util.Date; 
import java.time.LocalDate; 
import java.time.ZoneId;

public class Empleado extends Persona{
    
    private LocalDate fechaContrato;
    private String fechatContratoSTRING; 
    private double salario;
    // Este podriamos considerarlo un enum (manana o tarde) --> o solo un bool false si es manan o true si es tarde - gandy recomendacion 
    private boolean turnoTrabajo;
    private char tipo;
    private int idLocal;
	

    public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno, 
    String correoElectronico, String telefono, LocalDate fechaContrato,
    double salario, String usuarioSistema, String contraSistema, char tipo, int idLocal, int idUsuario ){
        super(nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, usuarioSistema, contraSistema, idUsuario, "E");
        this.fechaContrato = fechaContrato;
        this.salario = salario;
        this.turnoTrabajo = false;
        this.tipo = tipo;
        this.idLocal = idLocal;
    }
    
    
    public Empleado(Empleado empleado){
        super(empleado);
        this.fechaContrato = empleado.fechaContrato;
        this.salario = empleado.salario;
        this.turnoTrabajo = empleado.turnoTrabajo;
        this.tipo = empleado.tipo;
        this.idLocal = empleado.idLocal;
    }

    public Empleado(){ 
        this.setTipoUsuario("E");
    }
	
    @Override
    public String toString(){
        return super.toString() + ", Salario: " + String.valueOf(salario) + "\n";
    }

    //para asignar turno  -- nuevo agregado
    public void asignarNoche(){
            this.turnoTrabajo = true; 
    } 
	
    public void asignarManana(){
	this.turnoTrabajo = false; 
    }

    public LocalDate getFechaContrato() {
        return fechaContrato;
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

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    /*
     * @return the fechatContratoSTRING
     */
    public String getFechatContratoSTRING() {
        return fechatContratoSTRING;
    }

    /**
     * @param fechatContratoSTRING the fechatContratoSTRING to set
     */
    public void setFechatContratoSTRING(String fechatContratoSTRING) {
        this.fechatContratoSTRING = fechatContratoSTRING;
    }
	
}
