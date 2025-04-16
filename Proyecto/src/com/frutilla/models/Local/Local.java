package com.frutilla.models.Local;

import java.time.*;
import java.util.ArrayList;
import com.frutilla.models.Empleado.*;
import com.frutilla.models.Inventario.*;
import com.frutilla.models.Venta.*;

public class Local {
    private static int correlativo = 1; // Correlativo para asignar ID a los locales
    private int idLocal;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String telefono;
    private boolean activo;
    private int idSupervisor;
    private ArrayList<OrdenVenta> ordenesVentas; // Lista de ordenes de venta
    private ArrayList<Empleado> empleados; // Lista de empleados
    private ArrayList<Producto> productos; // Lista de productos
    private ArrayList<Bebida> bebidas; // Lista de bebidas
    private ArrayList<Fruta> frutas; // Lista de frutas
    private ArrayList<Snack> snacks; // Lista de snacks

    public Local(String nombre, String descripcion, String direccion, String telefono){
        this.idLocal = correlativo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activo = true; // Por defecto, el local está activo al crearse
        this.ordenesVentas = new ArrayList<OrdenVenta>();
        this.empleados = new ArrayList<Empleado>();
        this.productos = new ArrayList<Producto>();
        this.bebidas = new ArrayList<Bebida>();
        this.frutas = new ArrayList<Fruta>();
        this.snacks = new ArrayList<Snack>();
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
        this.productos = new ArrayList<Producto>(local.getProductos());
        this.bebidas = new ArrayList<Bebida>(local.getBebidas());
        this.frutas = new ArrayList<Fruta>(local.getFrutas());
        this.snacks = new ArrayList<Snack>(local.getSnacks());
        this.idSupervisor = local.getIdSupervisor();
    }

    public boolean verificarStock(Producto producto, int cantidad){
        if(producto instanceof Producto){
            for(Producto p : productos) {
                if (p.getCodigoProd() == producto.getCodigoProd()) {
                    if (p.getStock() >= cantidad) {
                        return true; // Stock suficiente
                    } else {
                        System.out.println("No hay suficiente stock del producto: " + p.getNombre());
                        return false; // Stock insuficiente
                    }
                }
            }
        } else if(producto instanceof Bebida){
            for(Bebida b : bebidas) {
                if (b.getCodigoProd() == producto.getCodigoProd()) {
                    if (b.getStock() >= cantidad) {
                        return true; // Stock suficiente
                    } else {
                        System.out.println("No hay suficiente stock de la bebida: "+ b.getNombre());
                        return false; // Stock insuficiente
                    }
                }
            }
        } else if(producto instanceof Fruta){
            for(Fruta f : frutas) {
                if (f.getCodigoProd() == producto.getCodigoProd()) {
                    if (f.getStock() >= cantidad) {
                        return true; // Stock suficiente
                    } else {
                        System.out.println("No hay suficiente stock de la fruta: "+ f.getNombre());
                        return false; // Stock insuficiente
                    }
                }
            }
        } else if(producto instanceof Snack){
            for(Snack s : snacks) {
                if (s.getCodigoProd() == producto.getCodigoProd()) {
                    if (s.getStock() >= cantidad) {
                        return true; // Stock suficiente
                    } else {
                        System.out.println("No hay suficiente stock del snack: " + s.getNombre());
                        return false; // Stock insuficiente
                    }
                }
            }
        }
        System.out.println("El producto no existe en el local.");
        return false; // Producto no encontrado
    }

    public void agregarProducto(Producto producto){
        for(Producto p : productos) {
            if (p.getCodigoProd() == producto.getCodigoProd()) {
                System.out.println("El producto ya existe en el local.");
                p.setStock(producto.getStock() + p.getStock()); // Actualiza el stock del producto existente
                return;
            }
        }
        // Si no existe se agrega el nuevo producto
        productos.add(producto);
    }

    public void agregarBebida(Bebida bebida){
        for(Bebida b : bebidas) {
            if (b.getCodigoProd() == bebida.getCodigoProd()) {
                System.out.println("La bebida ya existe en el local.");
                b.setStock(bebida.getStock() + b.getStock()); // Actualiza el stock de la bebida existente
                return;
            }
        }
        // Si no existe se agrega la nueva bebida
        bebidas.add(bebida);
    }

    public void agregarFruta(Fruta fruta){
        for(Fruta f : frutas) {
            if (f.getCodigoProd() == fruta.getCodigoProd()) {
                System.out.println("La fruta ya existe en el local.");
                f.setStock(fruta.getStock() + f.getStock()); // Actualiza el stock de la fruta existente
                return;
            }
        }
        // Si no existe se agrega la nueva fruta
        frutas.add(fruta);
    }

    public void agregarSnack(Snack snack){
        for(Snack s : snacks) {
            if (s.getCodigoProd() == snack.getCodigoProd()) {
                System.out.println("El snack ya existe en el local.");
                s.setStock(snack.getStock() + s.getStock()); // Actualiza el stock del snack existente
                return;
            }
        }
        // Si no existe se agrega el nuevo snack
        snacks.add(snack);
    }

    public void agregarOrdenVenta(OrdenVenta orden){
        ordenesVentas.add(orden);
    }

    public void agregarEmpleado(Empleado empleado){
        if(empleado instanceof Supervisor){
            setIdSupervisor(empleado.getIdEmpleado());
            System.out.println("El empleado es un supervisor, se le asigna el ID del local como ID de supervisor.");
        }
        empleados.add(empleado);
    }

    public void generarReporteEmpleados(){
        String reporte = "Reporte de empleados del local: " + "\n";
        for (Empleado empleado : empleados) {
            if (empleado instanceof Supervisor) {
                reporte += "Supervisor: ";
            } else if (empleado instanceof Repartidor) {
                reporte += "Repartidor: ";
            } else {
                reporte += "Empleado: ";
            }
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

    public int getStockProductos(){
        return stockProductos;
    }

    public void setStockProductos(int stockProductos){
        this.stockProductos = stockProductos;
    }

    public int getStockBebidas(){
        return stockBebidas;
    }

    public void setStockBebidas(int stockBebidas){
        this.stockBebidas = stockBebidas;
    }

    public int getStockFrutas(){
        return stockFrutas;
    }

    public void setStockFrutas(int stockFrutas){
        this.stockFrutas = stockFrutas;
    }

    public int getStockSnacks(){
        return stockSnacks;
    }

    public void setStockSnacks(int stockSnacks){
        this.stockSnacks = stockSnacks;
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

    public int getIdSupervisor(){
        return idSupervisor;
    }

    public void setIdSupervisor(int idSupervisor){
        this.idSupervisor = idSupervisor;
    }

    public ArrayList<OrdenVenta> getOrdenesVentas() {
        return new ArrayList<OrdenVenta>(ordenesVentas);
    }

    public ArrayList<Empleado> getEmpleados() {
        return new ArrayList<Empleado>(empleados);
    }
    public ArrayList<Producto> getProductos() {
        return new ArrayList<Producto>(productos);
    }
    public ArrayList<Bebida> getBebidas() {
        return new ArrayList<Bebida>(bebidas);
    }
    public ArrayList<Fruta> getFrutas() {
        return new ArrayList<Fruta>(frutas);
    }
    public ArrayList<Snack> getSnacks() {
        return new ArrayList<Snack>(snacks);
    }
}


