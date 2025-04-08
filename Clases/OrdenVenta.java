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
	private Repartidor repartidor;
	//Agregar Repartidor

	public OrdenVenta(){
		this.idOrdenVenta = correlativo; 
		this.estado = estadoVenta.PROCESO; 
		this.fecha = LocalDate.now(); //el dia pedido debe ser el de entrega
	}
	
	public OrdenVenta(LocalDate fecha, LocalTime horaFinEntrega,
	String descripcion, double montoTotal, estadoVenta estado, boolean entregado,Repartidor repartidor){
		this.idOrdenVenta = correlativo; 
		this.horaFinEntrega = horaFinEntrega;
		this.descripcion = descripcion; 
		this.montoTotal = montoTotal; 
		this.entregado = entregado; 
		this.estado = estado; 
		this.fecha = fecha; //el dia pedido debe ser el de entrega
		this.lineasOrdenes = new ArrayList<LineaOrdenDeVenta>();
		this.repartidor=new Repartidor (repartidor);
		this.correlativo++;
	}
	
	public OrdenVenta(OrdenVenta ord){
		this.setIdOrdenVenta(ord.getIdOrdenVenta()); 
		this.setHoraFinEntrega (ord.getHoraFinEntrega());
		this.setDescripcion (ord.getDescripcion()); 
		this.setMontoTotal (ord.getMontoTotal()); 
		this.setEntregado (ord.isEntregado()); 
		this.setEstado(ord.getEstado()); 
		this.setFecha(ord.getFecha()); //el dia pedido debe ser el de entrega
		this.setLineasOrdenes(ord.getLineasOrdenes());
		this.setRepartidor(ord.getRepartidor());
	}
	
	//setters y getters
	public void setIdOrdenVenta (int idOrdenVenta){
		this.idOrdenVenta=idOrdenVenta;
	}
	public int getIdOrdenVenta (){
		return idOrdenVenta;
	}
	public void setFecha(LocalDate fecha){
		this.fecha = fecha; 
	} 
	public LocalDate getFecha() {
		LocalDate copia = LocalDate.of(fecha.getYear(), fecha.getMonth(), 
		fecha.getDayOfMonth());
		return copia;
	}
	public void setHoraFinEntrega(LocalTime horaFinEntrega) {
		this.horaFinEntrega = horaFinEntrega;
	}
	public LocalTime getHoraFinEntrega() {
		LocalTime copia = LocalTime.of(horaFinEntrega.getHour(),
		horaFinEntrega.getMinute(),horaFinEntrega.getSecond(),
		horaFinEntrega.getNano());
		return copia;
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
	public void setLineasOrdenes(ArrayList<LineaOrdenDeVenta> detalle){
		this.lineasOrdenes=new ArrayList<LineaOrdenDeVenta> (detalle);
	}
	public ArrayList<LineaOrdenDeVenta> getLineasOrdenes(){
		return new ArrayList<LineaOrdenDeVenta>(lineasOrdenes);
	}
	public void setRepartidor(Repartidor rep){
		this.repartidor=new Repartidor(rep);
	}
	public Repartidor getRepartidor(){
		return new Repartidor(repartidor);
	}
	//methods 
	public void agregarLineaOrden(LineaOrdenDeVenta linea){
		lineasOrdenes.add(linea);
	}
	public void calcularTotal(){
		
	}
	public void cambiarEstado(){
		
	}
	public void entregarPedido(){
		
	}
	public void escogerProducto(){
		
	}
	
}
