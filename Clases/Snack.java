

class Snack extends Producto{
	private int idSnack;
	private String tipo; 
	private boolean requiereEnvase; 
	private boolean estaEnvasado; 
	private String envase;
	
	
	Snack(){
		super.Producto();
		this.estaEnvasado = 0; 
	}
	
	Snack(int idProducto, String nombre, String descripcion, String codigoProd,
		LocalDate fechaProduccion, LocalTime fechaVencimiento, 
		double precioUnitario,int stock, int stockMinimo,
		int idSnack, String tipo,UTL_Empaquetado envase, 
		boolean requiereEnvase, boolean estaEnvasado){
		
		super.Producto(int idProducto, String nombre, String descripcion, String codigoProd,
		LocalDate fechaProduccion, LocalTime fechaVencimiento, 
		double precioUnitario,int stock, int stockMinimo); //arreglar
		this.idSnack = idSnack; 
		this.tipo = tipo; 
		this.envase = envase; 
		this.requiereEnvase = requiereEnvase; 
		this. estaEnvasado = estaEnvasado; 
	}
	
	Snack(Snack original){
		super(original);
		this.idSnack = original.idSnack; 
		this.tipo = original.tipo; 
		this.requiereEnvase = original.requiereEnvase; 
		this.estaEnvasado = original.estaEnvasado; 
		this.envase = original.envase; 
	}
	
	//como se hace? XD
	void confirmarEnvasado(){
		this.estaEnvasado = 1; 
	}
	
}