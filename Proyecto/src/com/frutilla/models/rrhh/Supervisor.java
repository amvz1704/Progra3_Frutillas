package com.frutilla.models.rrhh;
import java.time.*;

import com.frutilla.crud.mysql.local.LocalMySQL;
import com.frutilla.models.local.Local;

public class Supervisor extends Empleado{

    public Supervisor(){
        super();
    }

    public Supervisor(String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String telefono, LocalDate fechaContrato, double salario, String usuarioSistema, String contraSistema){
        super(nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, fechaContrato, salario, usuarioSistema, contraSistema, 'S');
    }

    public void generarReporte(int idLocal, LocalDate fecha){
        LocalMySQL localMySQL = new LocalMySQL();
        Local local;
        try{
            local = localMySQL.obtenerLocalPorId(idLocal);
        }
        catch(Exception e){
            System.out.println("Error al obtener el local: " + e.getMessage());
            return;
        }
        local.generarReporteVentas(fecha);
    }

}
