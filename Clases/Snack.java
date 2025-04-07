import java.time.*;

public class Snack extends Producto{
	private String tipo; 
	private boolean requiereEnvase; 
	private boolean estaEnvasado; 
	private String envase;
	
	
	public Snack(){
		super();
		this.estaEnvasado = false; 
	}
	
	public Snack(String nombre, String descripcion, String codigoProd,
		LocalDate fechaProduccion, LocalDate fechaVencimiento, 
		double precioUnitario,int stock, int stockMinimo,
		int idSnack, String tipo,String envase, 
		boolean requiereEnvase, boolean estaEnvasado){
		
		super(nombre, descripcion, codigoProd,
		fechaProduccion, fechaVencimiento, 
		precioUnitario, stock, stockMinimo); //arreglar
		this.tipo = tipo; 
		this.envase = envase; 
		this.requiereEnvase = requiereEnvase; 
		this.estaEnvasado = estaEnvasado; 
	}
	
	public Snack(Snack original){
		super(original);
		this.tipo = original.tipo; 
		this.requiereEnvase = original.requiereEnvase; 
		this.estaEnvasado = original.estaEnvasado; 
		this.envase = original.envase; 
	}
	
	public void confirmarEnvasado(){
		//cambia estado
		this.estaEnvasado = true; 
	}

	@Override
	public String toString(){
		//colocar un mejor formato
		String cadena =super.toString() +" Es un snack. Envase: " + requiereEnvase; 
		if(requiereEnvase) cadena = cadena + " tipo: "+ envase; 
		return cadena; 
	}
	
}
