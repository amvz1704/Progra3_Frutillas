

class Fruta extends Producto{
	private int idFruta; 
	private boolean requiereLimpieza; 
	private boolean requiereEnvase;
	private boolean estaLimpio;
	private boolean estaEnvasado; 
	private String envase;
	
	Fruta(){
		super.Producto();
		this.estaEnvasado = 0; 
		this.estaLimpio = 0;
	}
	
	Fruta(int idProducto, String nombre, String descripcion, String codigoProd,
		LocalDate fechaProduccion, LocalTime fechaVencimiento, 
		double precioUnitario,int stock, int stockMinimo,
		int idFruta, boolean requiereLimpieza, boolean requiereEnvase,
		boolean estaLimpio, boolean estaEnvasado, UTL_Empaquetado envase){
		
		super.Producto(int idProducto, String nombre, String descripcion, String codigoProd,
		LocalDate fechaProduccion, LocalTime fechaVencimiento, 
		double precioUnitario,int stock, int stockMinimo);
		
		this.idFruta = idFruta; 
		this.requiereEnvase = requiereEnvase; 
		this.requiereLimpieza = requiereLimpieza; 
		this.estaEnvasado = estaEnvasado; 
		this.estaLimpio = estaLimpio; 
		this.envase = envase; 
	}
	
	//copia
	Fruta(Fruta original){
		super(original);
		this.idFruta = original.idFruta; 
		this.requiereLimpieza = original.requiereLimpieza; 
		this.requiereEnvase = original.requiereEnvase;
		this.estaEnvasado = original.estaEnvasado; 
		this.estaLimpio = original.estaLimpio; 
		this.envase = original.envase; 
	}
	
	
	//metodos
	void confirmarLimpieza(){
		this.estaLimpio = 1;
	}
	
	void confirmarEnvasado(){
		this.estaEnvasado = 1; 
	}
	
	
	
}