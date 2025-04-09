package com.frutilla.models.Local;

import java.time.*;
import java.util.ArrayList;
import com.frutilla.models.Empleado.*;
import com.frutilla.models.Venta.*;

public class Local {
    private static int correlativo = 1; // Correlativo para asignar ID a los locales
    private int idLocal;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String telefono;
    private boolean activo;
    private String idSupervisor;
    private ArrayList<OrdenVenta> ordenesVentas; // Lista de ordenes de venta
    private ArrayList<Empleado> empleados; // Lista de empleados

    public Local(String nombre, String descripcion, String direccion, String telefono){
        this.idLocal = correlativo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activo = true; // Por defecto, el local está activo al crearse
        this.ordenesVentas = new ArrayList<OrdenVenta>();
        this.empleados = new ArrayList<Empleado>();
        correlativo++;
    }

    public Local(Local local){
        this.idLocal = local.getIdLocal();
        this.nombre = local.getNombre();
        this.descripcion = local.getDescripcion();
        this.direccion = local.getDireccion();
        this.telefono = local.getTelefono();
        this.activo = local.getActivo();
        this.ordenesVentas = new ArrayList<OrdenVenta>(local.getOrdenesVentas());
        this.empleados = new ArrayList<Empleado>(local.getEmpleados()); 
    }

    public void agregarOrdenVenta(OrdenVenta orden){
        ordenesVentas.add(orden);
    }

    public void agregarEmpleado(Empleado empleado){
        empleados.add(empleado);
    }

    public void generarReporteEmpleados(){
        String reporte = "Reporte de empleados del local: " + "\n";
        for (Empleado empleado : empleados) {
            reporte += empleado.toString();
        }
        System.out.println(reporte);
    }

    public void generarReporteVentas(LocalDate fecha){
        String reporte = "Reporte de ventas del " + fecha.toString() + ":" + "\n";
        for (OrdenVenta orden : ordenesVentas) {
            if (orden.getFecha().equals(fecha)) {
                reporte +=orden.toString();
            } else {
                System.out.println("No hay ventas para la fecha especificada.");
            }
        }
        System.out.println(reporte);
    }

    public int getIdLocal() {
        return idLocal;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public boolean getActivo(){
        return activo;
    }

    public void setActivo(boolean activo){
        this.activo = activo;
    }

    public String getIdSupervisor(){
        return idSupervisor;
    }

    public void setIdSupervisor(String idSupervisor){
        this.idSupervisor = idSupervisor;
    }

    public ArrayList<OrdenVenta> getOrdenesVentas() {
        return new ArrayList<OrdenVenta>(ordenesVentas);
    }

    public ArrayList<Empleado> getEmpleados() {
        return new ArrayList<Empleado>(empleados);
    }
}


