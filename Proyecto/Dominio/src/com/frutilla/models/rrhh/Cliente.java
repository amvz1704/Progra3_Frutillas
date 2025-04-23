package com.frutilla.models.rrhh;

import java.util.ArrayList;

import com.frutilla.models.inventario.*;
import com.frutilla.models.venta.*;

public class Cliente extends Persona{
    private int idCliente;
    // falta inicializar
    private ArrayList <OrdenVenta> ordenesVentas;

    public Cliente(){
        this.ordenesVentas = new ArrayList<OrdenVenta>();
    }

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correoElectronico, String usuarioSistema, String contraSistema){
        super(nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, usuarioSistema, contraSistema);
        this.idCliente = -1;
        this.ordenesVentas = new ArrayList<OrdenVenta>();
    }

    public OrdenVenta obtenerUltimaOrden(){
        if(ordenesVentas.size() > 0){
            return ordenesVentas.get(ordenesVentas.size() - 1);
        }else{
            return null; // No hay ordenes de venta
        }
    }
    
    public boolean solicitarCompra(ArrayList<Producto> listaProductos, ArrayList<Integer> listaCantidad, FormaDePago formaPago) {
        // Crear una nueva orden de venta
        OrdenVenta orden = new OrdenVenta("Orden de venta Nro" + String.valueOf(ordenesVentas.size() + 1));
        for(int i = 0; i < listaProductos.size(); i++){
            Producto producto = listaProductos.get(i);
            int cantidad = listaCantidad.get(i);
            // Crear una nueva línea de orden de venta
            // Para despues, revertir el constructor de LineaOrden que reciba el i+1 como id
            LineaOrdenDeVenta lineaOrden = new LineaOrdenDeVenta( cantidad, producto);
            System.out.println("Solicitando compra de " + cantidad + " unidades de " + producto.getNombre());
            // Agregar la línea de orden a la orden de venta
            orden.agregarLineaOrden(lineaOrden);
        }
        //Realiza el pago y se crea el comprobante de pago
        orden.crearComprobantePago(formaPago);
        // Agregar la orden de venta a la lista de órdenes del cliente
        agregarOrdenVenta(orden);
        //Se desea obtener el comprobante de pago
        ComprobantePago comprobante = orden.getComprobantePago();
        //Imprimir el comprobante de pago
        System.out.println("Comprobante de pago: " + comprobante.toString() + "\n");
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

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
	
	public ArrayList <OrdenVenta> getOrdenesVentas(){
		return new ArrayList<OrdenVenta> (ordenesVentas); 
	}
}
