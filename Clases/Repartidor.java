package com.frutilla.models.Empleado;
import java.time.*;
import com.frutilla.models.Venta.*;

public class Repartidor extends Empleado{

    public Repartidor(String nombre, String apellidoPaterno, String apellidoMaterno,
    String correoElectronico, String telefono, LocalDate fechaContrato,
    double salario, String usuarioSistema, String contraSistema){
        super(nombre, apellidoPaterno, apellidoMaterno, correoElectronico,
        telefono, fechaContrato, salario, usuarioSistema, contraSistema);
    }
	
	public Repartidor(Repartidor rep){
		this.setNombre(rep.getNombre());
		this.setApellidoPaterno(rep.getApellidoPaterno());
		this.setApellidoMaterno(rep.getApellidoMaterno());
        this.setCorreoElectronico(rep.getCorreoElectronico());
        this.setTelefono(rep.getTelefono());
        this.setFechaContrato(rep.getFechaContrato());
        this.setSalario(rep.getSalario());
        this.setUsuarioSistema(rep.getUsuarioSistema());
        this.setContraSistema (rep.getContraSistema());
        this.setIdEmpleado(rep.getIdEmpleado());
		this.setTurnoTrabajo(rep.getTurnoTrabajo());
	}
    
    public void prepararPedido(OrdenVenta orden){
        //Settear Orden de venta como preparado
        orden.setEstado(estadoVenta.PROCESO);
    }

    public void confirmarEntregaCliente(OrdenVenta orden){
        orden.setEstado(estadoVenta.ENTREGADO);
        orden.setEntregado(true);
    }
}

