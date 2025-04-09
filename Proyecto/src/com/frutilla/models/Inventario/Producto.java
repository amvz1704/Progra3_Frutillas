package com.frutilla.models.Inventario;

//nueva edicion: agregue mostrar Producto 

import java.time.*; 

public class Producto{
	private int idProducto; 
	private String nombre; 
	private String descripcion;
	private String codigoProd; 
	private LocalDate fechaProduccion; 
	private LocalDate fechaVencimiento; 
	private double precioUnitario; 
	private int stock; 
	private int stockMinimo; 
	
	
	public Producto(){
		this.stock = 0; 
		this.stockMinimo = 0; 
		this.precioUnitario = 0; 
	}
	
	public Producto(int idProducto, String nombre, String descripcion, String codigoProd,
		LocalDate fechaProduccion, LocalDate fechaVencimiento, 
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
	public Producto(Producto original){
		
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
	
	public void revisarVencimiento(){
		System.out.println("Fecha vencimiento: " + fechaVencimiento);
	}
	
	//nuevo agregado
	@Override
	public String toString(){
		
		//colocar un formato
		return "Producto: " + nombre + ", Stock: "+ stock + ", Precio: s/" + precioUnitario + ", Descripcion: "+ descripcion;
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
		LocalDate copia = LocalDate.of(fechaProduccion.getYear(), 
		fechaProduccion.getMonth(), fechaProduccion.getDayOfMonth());
		return copia; 
	}
	
	public void setFechaVencimiento(LocalDate fechaVencimiento){
		this.fechaVencimiento = fechaVencimiento; 
	}
	
	public LocalDate getFechaVencimiento(){
		LocalDate copia = LocalDate.of(fechaVencimiento.getYear(), 
		fechaVencimiento.getMonth(), fechaVencimiento.getDayOfMonth());
		return copia;
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
