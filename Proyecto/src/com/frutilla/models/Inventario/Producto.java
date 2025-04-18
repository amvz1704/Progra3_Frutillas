package com.frutilla.models.Inventario;

//nueva edicion: agregue mostrar Producto 

public class Producto{
	private int idProducto; 
	private String nombre; 
	private String descripcion;
	private String codigoProd; 
	private double precioUnitario; 
	private int stock; 
	private int stockMinimo; 
	private TipoEstado tipoEstado;
	
	
	public Producto(){
		this.stock = 0; 
		this.stockMinimo = 0; 
		this.precioUnitario = 0; 
	}
	
	public Producto(int idProducto, String nombre, String descripcion, String codigoProd,
		double precioUnitario,int stock, int stockMinimo){
			
		this.idProducto = idProducto;
		this.nombre = nombre; 
		this.descripcion = descripcion; 
		this.codigoProd = codigoProd; 
		this.precioUnitario = precioUnitario; 
		this.stock = stock; 
		this.stockMinimo = stockMinimo; 
		this.tipoEstado = tipoEstado;
		
	}
	
	//constructor copia
	public Producto(Producto original){
		
		this.idProducto = original.idProducto;
		this.nombre = original.nombre; 
		this.descripcion = original.descripcion; 
		this.codigoProd = original.codigoProd; 
		this.precioUnitario = original.precioUnitario; 
		this.stock = original.stock; 
		this.stockMinimo = original.stockMinimo; 
		this.tipoEstado = tipoEstado;
	}
	
	//metodos
	
	public void actualizarStock(int cantidad){// si cantidad es positivo aumenta el stock, si es negativo lo reduce
		this.stock += cantidad;
	}

	//nuevo agregado
	@Override
	public String toString(){
		
		//colocar un formato
		return nombre + ", Codigo: " + codigoProd + ", Stock: "+ stock 
		+ ", Precio: s/" + precioUnitario + ", Descripcion: "+ descripcion
		+ "Estado: "+ tipoEstado;
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
	
	public void setStockMinimo(int stockMinimo){
		this.stockMinimo = stockMinimo; 
	}
	
	public int getStockMinimo(){
		return this.stockMinimo; 
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdProducto() {
		return idProducto;
	}
	
	public TipoEstado getTipoEstado(){
		return tipoEstado;
	}
	public void setTipoEstado(TipoEstado tipoEstado){
		this.tipoEstado = tipoEstado;
	}
}

