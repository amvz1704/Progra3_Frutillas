import java.time.LocalTime;
import java.time.LocalDate;

//solo usar set y gets con los realmente usados preguntarle a regina**

class Orden_Venta{
	
	private int idOrdenVenta; 
	private LocalDate fecha; 
	private LocalTime horaFinEntrega;
	private String descripcion; 
	private double montoTotal; 
	private estadoVenta estado; 
	private boolean entregado; 
	
	Orden_Venta(){
		this.estado = estadoVenta.PROCESO; 
		this.fecha = LocalDate.now(); //el dia pedido debe ser el de entrega
	}
	
	Orden_Venta(int idOrdenVenta, LocalDate fecha, LocalTime horaFinEntrega,
	String descripcion, double montoTotal, estadoVenta estado, boolean entregado){
		this.idOrdenVenta = idOrdenVenta; 
		this.horaFinEntrega = horaFinEntrega;
		this.descripcion = descripcion; 
		this.montoTotal = montoTotal; 
		this.entregado = entregado; 
		this.estado = estado; 
		this.fecha = fecha; //el dia pedido debe ser el de entrega
	}
	
	//methods 
	void calcularTotal(){
		
	}
	void cambiarEstado(){
		
	}
	
	void entregarPedido(){
		
	}
	void escogerProducto(){
		
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
