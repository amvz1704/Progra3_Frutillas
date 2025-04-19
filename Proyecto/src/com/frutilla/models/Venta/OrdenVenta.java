package com.frutilla.models.venta;

import java.time.*; 
import java.util.ArrayList;
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
	private ComprobantePago comprobantePago; //se crea al momento de que se realice el pago
	//Agregar Empleado
	private int idEmpleado;

	@Override
	public String toString(){
		return "Id: " + String.valueOf(idOrdenVenta) + ", Estado: " + estado + ", Descripcion: " + descripcion + ", Monto: s/" + String.valueOf(montoTotal) + "\n";
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
		this.idEmpleado = -1;
		correlativo++;
	}
	
	//constructor forma 1 le pasas la lista de las lineas
	public OrdenVenta(String descripcion, ArrayList <LineaOrdenDeVenta> lista){
		this.idOrdenVenta = correlativo; 
		this.descripcion = descripcion; 
		
		this.fecha = LocalDate.now();//el dia pedido debe ser el de entrega
		this.idEmpleado = -1;
		//una orden de venta crea un comprobante de pago
		this.lineasOrdenes = new ArrayList<LineaOrdenDeVenta>();
		
		//copia de forma profunda las lineas de orden 
		for(LineaOrdenDeVenta linea: lista){
			lineasOrdenes.add(linea); 
		}
		this.estado = estadoVenta.FALTA_PAGO; 
		
		this.entregado = false; //se actualizara despues
		correlativo++;
	}
	
	//forma 2 & forma 1--> methods reemplace crear total por crear un comprobante de pago para una orden de venta!
	public void crearComprobantePago(FormaDePago formaPago){
		
		//no se puede crear comprobante si no hay ninguna linea :)
		if(lineasOrdenes.size() == 0){
			System.out.println("Aun no se agregaron lineas de venta para crear un comprobante"); 
			this.comprobantePago = new ComprobantePago();
		}
		
		int numArticulos = 0; 
		for(LineaOrdenDeVenta linea: lineasOrdenes){
			montoTotal += linea.getSubtotal(); 
			numArticulos += linea.getCantidad(); 
		}
		
		//se asigna por entregar al comprobante de pago
		this.estado = estadoVenta.POR_ENTREGAR; 
		//se crea el comprobante de pago
		this.comprobantePago = new ComprobantePago(numArticulos, montoTotal, 0.18, fecha, idOrdenVenta, formaPago);
	}
	
	//tipo 2--> agregar linea de ordenVenta otro metodo de llenar un orden de venta
	public void agregarLineaOrden(LineaOrdenDeVenta linea){
		lineasOrdenes.add(new LineaOrdenDeVenta(linea));
	}
	
	//en vez de entregar pedido lo cambie a entrega exitosa que sera editado por un Repartidor
	public void entregaExitosa(boolean cambio){
		if(cambio) this.estado = estadoVenta.CAMBIO;
		else this.estado = estadoVenta.ENTREGADO;
	}
	
	//setters y getters

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getIdEmpleado() {
		return this.idEmpleado;
	}
	
	public void setComprobantePago(ComprobantePago comprobantePago) {
		this.comprobantePago = comprobantePago;
	}

	public ComprobantePago getComprobantePago() {
		return new ComprobantePago(this.comprobantePago);
	}

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
