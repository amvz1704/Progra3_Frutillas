

import java.time.LocalDate;
import java.time.LocalTime;

class Producto{
	private int idProducto; 
	private String nombre; 
	private String descripcion;
	private String codigoProd; 
	private LocalDate fechaProduccion; 
	private LocalTime fechaVencimiento; 
	private double precioUnitario; 
	private int stock; 
	private int stockMinimo; 
	
	
	Producto(){
		this.stock = 0; 
		this.stockMinimo = 0; 
		this.precioUnitario = 0; 
	}
	
	Producto(int idProducto, String nombre, String descripcion, String codigoProd,
		LocalDate fechaProduccion, LocalTime fechaVencimiento, 
		double precioUnitario,int stock, int stockMinimo){
			
		this.idProducto = idProducto;
		this.nombre = nombre; 
		this.descripcion = descripcion; 
		this.codigoProd = codigoProd; 
		this.fechaProduccion = fechaProduccion; 
		this.fechaVencimiento = fechaVencimiento;
		this.precioUnitario = precioUnitario; 
		this.stock = stock; 
		this.stockMinimo = stockMinimo; 
		
	}
	
	//constructor copia
	Producto(Producto original){
		
		this.idProducto = original.idProducto;
		this.nombre = original.nombre; 
		this.descripcion = original.descripcion; 
		this.codigoProd = original.codigoProd; 
		this.fechaProduccion = original.fechaProduccion;  //inmutables?
		this.fechaVencimiento = original.fechaVencimiento;
		this.precioUnitario = original.precioUnitario; 
		this.stock = original.stock; 
		this.stockMinimo = original.stockMinimo; 
	}
	
	//metodos
	
	void revisarVencimiento(){
		//NO SE preguntar XD
	}
	
	//conjuntoSettersyGetters
	public void setNombre(String nombre){
		this.nombre = nombre; 
	}
	
	public String getNombre(){
		return this.nombre; 
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
	
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setCodigoProd(String codigoProd){
		this.codigoProd = codigoProd;
	}
	
	public String getCodigoProd(){
		return this.codigoProd;
	}
	
	public void setFechaProduccion(LocalDate fechaProduccion){
		this.fechaProduccion = fechaProduccion; 
	}
	
	public LocalDate getFechaProduccion(){
		return this.fechaProduccion; 
	}
	
	public void setFechaVencimiento(LocalDate fechaVencimiento){
		this.fechaVencimiento = fechaVencimiento; 
	}
	
	public LocalDate getFechaVencimiento(){
		return this.fechaVencimiento; 
	}
	
	public void setPrecioUnitario(double precioUnitario){
		this.precioUnitario = precioUnitario; 
	}
	
	public double getPrecioUnitario(){
		return this.precioUnitario; 
	}
	
	public void setStock(int stock){
		this.stock = stock; 
	}
	
	public int getStock(){
		return this.stock; 
	}
	
	public void setStockMinimo(int StockMinimo){
		this.stock = stock; 
	}
	
	public int getStockMinimo(){
		return this.stock; 
	}
	
	
}
