package com.frutilla.models.Cliente;

import java.util.ArrayList;
import com.frutilla.models.Venta.*;
import com.frutilla.models.Inventario.*;

public class Cliente {
    private static int correlativo = 1; // Correlativo para asignar ID a los clientes
    private int idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correoElectronico;
    private boolean activo; // true: activo, false: inactivo
    // falta inicializar
    private ArrayList <OrdenVenta> ordenesVentas;

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correoElectronico) {
        this.idCliente = correlativo;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.activo = true; // Por defecto, el cliente es activo al crearse
        correlativo++;
        this.ordenesVentas = new ArrayList<OrdenVenta>();
    }
    
    public boolean solicitarCompra(ArrayList<Producto> listaProductos, ArrayList<Integer> listaCantidad){
        // Crear una nueva orden de venta
        OrdenVenta orden = new OrdenVenta("Primera Compra");
        for(int i = 0; i < listaProductos.size(); i++){
            Producto producto = listaProductos.get(i);
            int cantidad = listaCantidad.get(i);
            // Crear una nueva línea de orden de venta
            LineaOrdenDeVenta lineaOrden = new LineaOrdenDeVenta(i + 1, cantidad, producto);
            System.out.println("Solicitando compra de " + cantidad + " unidades de " + producto.getNombre());
            // Agregar la línea de orden a la orden de venta
            orden.agregarLineaOrden(lineaOrden);
        }

        //Realiza el pago y se crea el comprobante de pago
        orden.crearComprobantePago();
        // Agregar la orden de venta a la lista de órdenes del cliente
        agregarOrdenVenta(orden);
        //Se desea obtener el comprobante de pago
        ComprobantePago comprobante = orden.getComprobantePago();
        //Imprimir el comprobante de pago
        System.out.println("Comprobante de pago: " + comprobante.toString());
        return true;
    }

    public void desactivarCliente(){
        setActivo(false);
    }

    public void agregarOrdenVenta(OrdenVenta orden){
        this.ordenesVentas.add(orden);
    }

    public void realizarCompra() {
        //Agregar logica de compra
        System.out.println("Realizando compra...");
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellidoPaterno(){
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno){
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno(){
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno){
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public String getCorreoElectronico(){
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico){
        this.correoElectronico = correoElectronico;
    }

    public boolean getActivo(){
        return activo;
    }

    public void setActivo(boolean activo){
        this.activo = activo;
    }
	
	public ArrayList <OrdenVenta> getOrdenesVentas(){
		return new ArrayList<OrdenVenta> (ordenesVentas);
	}
}
