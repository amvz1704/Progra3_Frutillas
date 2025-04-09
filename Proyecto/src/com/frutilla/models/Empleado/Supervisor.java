package com.frutilla.models.Empleado;
import java.time.*;

class Supervisor extends Empleado{

    public Supervisor(String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String telefono, LocalDate fechaContrato, double salario, String usuarioSistema, String contraSistema){
        super(nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, fechaContrato, salario, usuarioSistema, contraSistema);
    }

    public void generarReporteVentas(){
		
    }
    
    public void eliminarEmpleado(Empleado empleado){

    }

    public void agregarEmpleado(Empleado empleado){

    }

}