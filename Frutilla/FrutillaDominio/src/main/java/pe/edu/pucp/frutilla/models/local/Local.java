package pe.edu.pucp.frutilla.models.local;

import pe.edu.pucp.frutilla.models.venta.EstadoVenta;
import pe.edu.pucp.frutilla.models.venta.OrdenVenta;
import pe.edu.pucp.frutilla.models.inventario.Producto;
//import java.time.*;
import java.util.ArrayList;

//import com.frutilla.crud.mysql.local.LocalMySQL;
//import com.frutilla.crud.mysql.venta.OrdenVentaMySQL;
import pe.edu.pucp.frutilla.models.rrhh.Empleado;
import pe.edu.pucp.frutilla.models.rrhh.Repartidor;
import pe.edu.pucp.frutilla.models.rrhh.Supervisor;


public class Local {

    private int idLocal;
    private String nombre;
    private String descripcion;
    private String direccion;
    private boolean activo;
    private String telefono;
    private int idSupervisor;
    private ArrayList<OrdenVenta> ordenesVentas; // Lista de ordenes de venta
    private ArrayList<Empleado> empleados; // Lista de empleados
    private ArrayList<Producto> productos; // Lista de todos los productos

	//Constructor Local
    public Local(String nombre, String descripcion, String direccion, String telefono){
        this.idLocal = -1; //sera asignado por la BD 
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activo = true; // Por defecto, el local está activo al crearse
        this.ordenesVentas = new ArrayList<OrdenVenta>();
        this.empleados = new ArrayList<Empleado>();
        this.productos = new ArrayList<Producto>();
    }

	//Constructor copia Local
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
        this.idSupervisor = local.getIdSupervisor();
    }
	
    
    @Override
    public String toString() {
        return String.format(
          "Local{id=%d, nombre='%s', descripcion='%s', direccion='%s', telefono='%s', activo=%s}",
          idLocal, nombre, descripcion, direccion, telefono, activo
        );
    }
	
	//Regresa la primer orden que esta por ser entregada 
    public OrdenVenta obtenerOrden(){
        for(OrdenVenta orden : ordenesVentas) {
            if(orden.getEstado() == EstadoVenta.POR_ENTREGAR) {
                return orden; // Devuelve la primera orden en estado POR_ENTREGAR
            }
        }
        return null; // No hay ordenes en estado POR_ENTREGAR
    }
	
	//muestra en la pantalla la lista de productos dentro de un local
//    public void generarReporteProductos(){
//        LocalMySQL localMySQL = new LocalMySQL();
//        try{
//            this.productos = localMySQL.encontrarProductos(idLocal);
//        }
//        catch(Exception e){
//            System.out.println("Error al obtener los productos: " + e.getMessage());
//            return;
//        }
//        String reporte = "Reporte de productos del local " + "\n";
//        reporte += "Productos: \n";
//        for (Producto producto : productos) {
//            reporte += producto.toString() + "\n";
//        }
//        System.out.println(reporte);
//    }
	
	//dado una lista de productos y una lista de cantidades actualiza el stock 
    public void actualizarStock(ArrayList<Producto> listaProductos, ArrayList<Integer> listaCantidad) {
    for (int i = 0; i < listaProductos.size(); i++) {
        Producto pro = listaProductos.get(i);
        int cantidad = listaCantidad.get(i);
        // Verifica si el producto existe en la lista de productos
        for (Producto p : productos) {
            if (p.getCodigoProd().equals(pro.getCodigoProd())) {
                p.actualizarStock(-cantidad);
                System.out.println("Se actualizó el stock del producto: " + p.getNombre());
                break;
            }
        }
    }
}
	
	//verifica si existe el stock necesario para un producto 
    public boolean verificarStock(Producto producto, int cantidad) {
        // Verifica si el producto existe en la lista de productos
        for (Producto p : productos) {
            if (p.getCodigoProd().equals(producto.getCodigoProd())) {
                if (p.getStock() >= cantidad){
                    System.out.println("Hay suficiente stock del producto: " + p.getNombre());
                    return true;
                }
                System.out.println("No hay suficiente stock del producto: " + p.getNombre());
                return false;
            }
        }
    
        System.out.println("El producto(" + producto.getNombre() + ") no existe en el local.");
        return false;
    }
    
	//agrega el producto o el stock de producto
    public void agregarProducto(Producto producto){
        for(Producto p : productos) {
            if (p.getCodigoProd().equals(producto.getCodigoProd())) {
                System.out.println("El producto(" + producto.getNombre() + ") ya existe en el local.");
                p.actualizarStock(producto.getStock()); // Actualiza el stock del producto existente
                return;
            }
        }
        // Si no existe se agrega el nuevo producto
        productos.add(producto);
        System.out.println("Se agrego el producto(" + producto.getNombre() + ") al local.");
    }
	
	
	//Agregando ordenes de venta y empleados 
	
    public void agregarOrdenVenta(OrdenVenta orden){
        ordenesVentas.add(orden);
    }

    public void agregarEmpleado(Empleado empleado){
        if(empleado instanceof Supervisor){
            setIdSupervisor(empleado.getIdEmpleado());
            System.out.println("Se agrego el supervisor al local.");
        }
        empleados.add(empleado);
    }

//    public void generarReporteEmpleados(){
//        LocalMySQL localMySQL = new LocalMySQL();
//        try{
//            this.empleados = localMySQL.encontrarEmpleados(idLocal);
//        }
//        catch(Exception e){
//            System.out.println("Error al obtener los empleados: " + e.getMessage());
//            return;
//        }
//        String reporte = "Reporte de empleados del local: " + "\n";
//        for (Empleado empleado : empleados) {
//            if (empleado instanceof Supervisor) {
//                reporte += "Supervisor: ";
//            } else if (empleado instanceof Repartidor) {
//                reporte += "Repartidor: ";
//            } else {
//                reporte += "Empleado: ";
//            }
//            reporte += empleado.toString();
//        }
//        System.out.println(reporte);
//    }
//
//    public void generarReporteVentas(LocalDate fecha){
//        OrdenVentaMySQL ordenesVentaMySQL = new OrdenVentaMySQL();
//        try{
//            this.ordenesVentas = ordenesVentaMySQL.obtenerTodos(idLocal);
//        }
//        catch(Exception e){
//            System.out.println("Error al obtener las ordenes de venta: " + e.getMessage());
//            return;
//        }
//        String reporte = "Reporte de ventas del " + fecha.toString() + ":" + "\n";
//        for (OrdenVenta orden : ordenesVentas) {
//            if (orden.getFecha().equals(fecha)) {
//                reporte +=orden.toString();
//            } else {
//                System.out.println("No hay ventas para la fecha especificada.");
//            }
//        }
//        System.out.println(reporte);
//    }
	
	//cambio nuevo agregar el setIdLocal -- 20/04 19:00
	public void setIdLocal(int idLocal){
        this.idLocal = idLocal;
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

}


