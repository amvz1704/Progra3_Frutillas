import java.time.*;

class Snack extends Producto{
	private int idSnack;
	private String tipo; 
	private boolean requiereEnvase; 
	private boolean estaEnvasado; 
	private String envase;
	
	
	public Snack(){
		super();
		this.estaEnvasado = false; 
	}
	
	public Snack(int idProducto, String nombre, String descripcion, String codigoProd,
		LocalDate fechaProduccion, LocalDate fechaVencimiento, 
		double precioUnitario,int stock, int stockMinimo,
		int idSnack, String tipo,String envase, 
		boolean requiereEnvase, boolean estaEnvasado){
		
		super(idProducto, nombre, descripcion, codigoProd,
		fechaProduccion, fechaVencimiento, 
		precioUnitario, stock, stockMinimo); //arreglar
		this.idSnack = idSnack; 
		this.tipo = tipo; 
		this.envase = envase; 
		this.requiereEnvase = requiereEnvase; 
		this.estaEnvasado = estaEnvasado; 
	}
	
	public Snack(Snack original){
		super(original);
		this.idSnack = original.idSnack; 
		this.tipo = original.tipo; 
		this.requiereEnvase = original.requiereEnvase; 
		this.estaEnvasado = original.estaEnvasado; 
		this.envase = original.envase; 
	}
	
	//como se hace? XD
	public void confirmarEnvasado(){
		this.estaEnvasado = true; 
	}
	
}