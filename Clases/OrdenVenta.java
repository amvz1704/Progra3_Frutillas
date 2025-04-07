
import java.time.*; 
import java.util.ArrayList;
//solo usar set y gets con los realmente usados preguntarle a regina**

class OrdenVenta{
	
	private int idOrdenVenta; 
	private LocalDate fecha; 
	private LocalTime horaFinEntrega;
	private String descripcion; 
	private double montoTotal; 
	private estadoVenta estado; 
	private boolean entregado; 
	private static int correlativo = 1;
	private ArrayList <LineaOrdenDeVenta> lineasOrdenes;
	private ComprobantePago comprobante;
	//Agregar Repartidor

	public OrdenVenta(){
		this.estado = estadoVenta.PROCESO; 
		this.fecha = LocalDate.now(); //el dia pedido debe ser el de entrega
	}
	
	public OrdenVenta(LocalDate fecha, LocalTime horaFinEntrega,
	String descripcion, double montoTotal, estadoVenta estado, boolean entregado){
		this.idOrdenVenta = correlativo; 
		this.horaFinEntrega = horaFinEntrega;
		this.descripcion = descripcion; 
		this.montoTotal = montoTotal; 
		this.entregado = entregado; 
		this.estado = estado; 
		this.fecha = fecha; //el dia pedido debe ser el de entrega
		this.lineasOrdenes = new ArrayList<LineaOrdenDeVenta>();
		this.comprobante = new ComprobantePago();
		this.correlativo++;
	}
	
	//methods 
	public void calcularTotal(){
		
	}
	public void cambiarEstado(){
		
	}
	
	public void entregarPedido(){
		
	}
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