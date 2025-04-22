package com.frutilla.models.rrhh;
import java.time.*;

public class Supervisor extends Empleado{

    public Supervisor(){
        super();
    }

    public Supervisor(String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String telefono, LocalDate fechaContrato, double salario, String usuarioSistema, String contraSistema){
        super(nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, fechaContrato, salario, usuarioSistema, contraSistema, 'S');
    }


}
