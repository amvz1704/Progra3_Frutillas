package pe.edu.pucp.frutilla.models.inventario;

//se hizo publico 
public class Fruta extends Producto{
	private Boolean requiereLimpieza; 
	private Boolean requiereEnvase;
	private Boolean estaLimpio;
	private Boolean estaEnvasado; 
	private String envase;
	
	public Fruta(){
		super();
		this.estaEnvasado = false; 
		this.estaLimpio = false;
	}
	
	public Fruta(String nombre, String descripcion, String codigoProd,double precioUnitario,int stock, int stockMinimo, boolean requiereLimpieza, boolean requiereEnvase, boolean estaLimpio, boolean estaEnvasado, String envase){
		
		super(nombre, descripcion, codigoProd,
		precioUnitario, stock, stockMinimo);
		this.requiereEnvase = requiereEnvase; 
		this.requiereLimpieza = requiereLimpieza; 
		this.estaEnvasado = estaEnvasado; 
		this.estaLimpio = estaLimpio; 
		this.envase = envase; 
	}
	
	//copia
	public Fruta(Fruta original){
		super(original);
		this.requiereLimpieza = original.requiereLimpieza; 
		this.requiereEnvase = original.requiereEnvase;
		this.estaEnvasado = original.estaEnvasado; 
		this.estaLimpio = original.estaLimpio; 
		this.envase = original.envase; 
	}

	public Fruta(Producto producto) {
		super(producto);  // Llama al constructor copia de Producto
		this.estaLimpio=false;
		this.estaEnvasado=false; 
	}

	public Boolean isRequiereLimpieza() {
        return requiereLimpieza;
    	}

	public void setRequiereLimpieza(Boolean requiereLimpieza) {
	this.requiereLimpieza = requiereLimpieza;
	}
	
	public Boolean isRequiereEnvase() {
	return requiereEnvase;
	}
	
	public void setRequiereEnvase(Boolean requiereEnvase) {
	this.requiereEnvase = requiereEnvase;
	}
	
	public Boolean isEstaLimpio() {
	return estaLimpio;
	}
	
	public void setEstaLimpio(Boolean estaLimpio) {
	this.estaLimpio = estaLimpio;
	}
	
	public Boolean isEstaEnvasado() {
	return estaEnvasado;
	}
	
	public void setEstaEnvasado(Boolean estaEnvasado) {
	this.estaEnvasado = estaEnvasado;
	}
	
	public String getEnvase() {
	return envase;
	}
	
	public void setEnvase(String envase) {
	this.envase = envase;
	}
	
	
	//metodos
	@Override
	public String toString(){
		String cadena = super.toString();
		cadena += ", Limpio : "+ estaLimpio + ", Envasado: " + estaEnvasado; 
		if(estaEnvasado) cadena += ", Envase: " + envase; 
		
		return cadena; 
			
	}
	
	void confirmarLimpieza(){
		this.estaLimpio = true;
	}
	
	void confirmarEnvasado(){
		this.estaEnvasado = true; 
	}
	
	
	
}
