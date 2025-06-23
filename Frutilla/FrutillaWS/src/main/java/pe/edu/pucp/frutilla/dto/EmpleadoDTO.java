/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.dto;

import java.time.LocalDate;
import pe.edu.pucp.frutilla.models.rrhh.Empleado;
import pe.edu.pucp.frutilla.models.rrhh.Persona;

/**
 *
 * @author Desktop
 */
public class EmpleadoDTO extends Persona{
    private String fechatContratoSTRING; 
    private double salario;
    private boolean turnoTrabajo;
    private char tipo;
    private int idLocal;
    
    public EmpleadoDTO(){
        
    }
    public EmpleadoDTO(Empleado base){
        this.setNombre(base.getNombre());
        this.setApellidoPaterno(base.getApellidoPaterno());
        this.setApellidoMaterno(base.getApellidoMaterno());
        this.setCorreoElectronico(base.getCorreoElectronico());
        this.setTelefono(base.getTelefono());
        this.setUsuarioSistema(base.getUsuarioSistema());
        this.setContraSistema(base.getContraSistema());
        this.setActivo(true);
        this.setIdUsuario(base.getIdUsuario());
        this.setTipoUsuario(base.getTipoUsuario());
        this.fechatContratoSTRING = base.getFechaContrato().toString();
        this.salario = base.getSalario();
        this.turnoTrabajo = base.getActivo();
        this.tipo = base.getTipo();
        this.idLocal = base.getIdLocal();
    }

    public String getFechatContratoSTRING() {
        return fechatContratoSTRING;
    }

    public void setFechatContratoSTRING(String fechatContratoSTRING) {
        this.fechatContratoSTRING = fechatContratoSTRING;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isTurnoTrabajo() {
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
    
    public Empleado convertirAEmpleado(){
        Empleado nuevo = new Empleado();
        nuevo.setNombre(this.getNombre());
        nuevo.setApellidoPaterno(this.getApellidoPaterno());
        nuevo.setApellidoMaterno(this.getApellidoMaterno());
        nuevo.setCorreoElectronico(this.getCorreoElectronico());
        nuevo.setTelefono(this.getTelefono());
        nuevo.setUsuarioSistema(this.getUsuarioSistema());
        nuevo.setContraSistema(this.getContraSistema());
        nuevo.setActivo(this.getActivo());
        nuevo.setIdUsuario(this.getIdUsuario());
        nuevo.setTipoUsuario(this.getTipoUsuario());
        nuevo.setFechaContrato(LocalDate.parse(fechatContratoSTRING));
        nuevo.setSalario(salario);
        nuevo.setTurnoTrabajo(turnoTrabajo);
        nuevo.setTipo(tipo);
        nuevo.setIdLocal(idLocal);
        return nuevo;
    }
}
