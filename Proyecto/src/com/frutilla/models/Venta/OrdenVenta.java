package com.frutilla.models.Venta;

import java.time.*; 
import java.util.ArrayList;
import com.frutilla.models.Empleado.*;
//solo usar set y gets con los realmente usados preguntarle a regina**

public class OrdenVenta{
	
	private int idOrdenVenta; 
	private LocalDate fecha; 
	private LocalTime horaFinEntrega;
	private String descripcion; 
	private double montoTotal; 
	private estadoVenta estado; 
	private boolean entregado; 
	private static int correlativo = 1;
	private ArrayList <LineaOrdenDeVenta> lineasOrdenes;
	//Agregar Empleado
	private Empleado empleado;

	@Override
	public String toString(){
		return "Id: " + String.valueOf(idOrdenVenta) + ", Estado: " + estado + ", Descripcion: " + descripcion + ", Monto: " + String.valueOf(montoTotal) + "\n";
	}
	
	//Agregar Repartidor --> no es necesario pues puede ser cualquier repartidor una vez de hace la entrega
	
	//constructor forma 2 agregas linea de venta uno por uno a travez de agregarLinea()
	public OrdenVenta(String descripcion){
		this.descripcion = descripcion; 
		this.estado = estadoVenta.FALTA_PAGO; 
		this.fecha = LocalDate.now(); //el dia pedido debe ser el de entrega
		this.lineasOrdenes = new ArrayList<LineaOrdenDeVenta>();
		this.entregado = false; 
		this.montoTotal = 0; 
		this.idOrdenVenta = correlativo;
		this.empleado = new Empleado();
		this.correlativo++;
	}
	
	//constructor forma 1 le pasas la lista de las lineas
	public OrdenVenta(String descripcion, ArrayList <LineaOrdenDeVenta> lista){
		this.idOrdenVenta = correlativo; 
		this.descripcion = descripcion; 
		
		this.fecha = LocalDate.now();//el dia pedido debe ser el de entrega
		this.empleado = new Empleado();
		//una orden de venta crea un comprobante de pago
		this.lineasOrdenes = new ArrayList<LineaOrdenDeVenta>();
		
		//copia de forma profunda las lineas de orden 
		for(LineaOrdenDeVenta linea: lista){
			lineasOrdenes.add(linea); 
		}
		this.estado = estado.FALTA_PAGO; 
		
		this.entregado = false; //se actualizara despues
		this.correlativo++;
	}
	
	//forma 2 & forma 1--> methods reemplace crear total por crear un comprobante de pago para una orden de venta!
	public ComprobantePago crearComprobantePago(){
		
		//no se puede crear comprobante si no hay ninguna linea :)
		if(lineasOrdenes.size() == 0){
			System.out.println("Aun no se agregaron lineas de venta para crear un comprobante"); 
			return new ComprobantePago();
		}
		
		int numArticulos = 0; 
		for(LineaOrdenDeVenta linea: lineasOrdenes){
			montoTotal += linea.getSubtotal(); 
			numArticulos += linea.getCantidad(); 
		}
		
		//se asigna por entregar al comprobante de pago
		this.estado = estado.POR_ENTREGAR; 
		
		return new ComprobantePago(numArticulos, montoTotal, 0.18, fecha, idOrdenVenta);
		
	}
	
	//tipo 2--> agregar linea de ordenVenta otro metodo de llenar un orden de venta
	public void agregarLineaOrden(LineaOrdenDeVenta linea){
		lineasOrdenes.add(new LineaOrdenDeVenta(linea));
	}
	
	//en vez de entregar pedido lo cambie a entrega exitosa que sera editado por un Repartidor
	public void entregaExitosa(boolean cambio){
		if(cambio) this.estado = estado.CAMBIO;
		else this.estado = estado.ENTREGADO;
	}
	
	
	
	//por implementar
	public void escogerProducto(){
		
	}
	
	//setters y getters
	
	public void setFecha(LocalDate fecha){
		this.fecha = fecha; 
	} 
	
	public LocalDate getFecha() {
		return this.fecha;
	}

	public void setHoraFinEntrega(LocalTime horaFinEntrega) {
		this.horaFinEntrega = horaFinEntrega;
	}

	public LocalTime getHoraFinEntrega() {
		return this.horaFinEntrega;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public double getMontoTotal() {
		return this.montoTotal;
	}

	public void setEstado(estadoVenta estado) {
		this.estado = estado;
	}

	public estadoVenta getEstado() {
		return this.estado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

	public boolean isEntregado() {
		return this.entregado;
	} 
	
}
