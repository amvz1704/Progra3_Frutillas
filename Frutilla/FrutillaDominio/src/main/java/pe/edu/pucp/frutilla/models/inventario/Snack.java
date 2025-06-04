package pe.edu.pucp.frutilla.models.inventario;

//faltaba que sea public xd
public class Snack extends Producto{
	private String tipo; 
	private Boolean requiereEnvase; 
	private Boolean estaEnvasado; 
	private String envase;
	
	
	public Snack(){
		super();
		this.estaEnvasado = false;
                this.requiereEnvase = false;
	}
	
	public Snack(String nombre, String descripcion, String codigoProd, double precioUnitario,int stock, int stockMinimo, String tipo,String envase, boolean requiereEnvase, boolean estaEnvasado){
		super(nombre, descripcion, codigoProd, precioUnitario, stock, stockMinimo); 
		
		this.tipo = tipo; 
		this.envase = envase; 
		this.requiereEnvase = requiereEnvase; 
		this.estaEnvasado = estaEnvasado; 
	}
	public Snack(Producto producto) {
		super(producto);  // Llama al constructor copia de Producto
		this.estaEnvasado = false;
	}
	
	public Snack(Snack original){
		super(original);
		this.tipo = original.tipo; 
		this.requiereEnvase = original.requiereEnvase; 
		this.estaEnvasado = original.estaEnvasado; 
		this.envase = original.envase; 
	}

	public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public Boolean isRequiereEnvase() {
            return requiereEnvase;
        }

        public void setRequiereEnvase(Boolean requiereEnvase) {
            this.requiereEnvase = requiereEnvase;
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
	
	@Override
	public String toString(){
		
		//colocar un formato
		String cadena =super.toString(); 
		if(requiereEnvase) cadena += ", Envase: " + envase; 
		return cadena; 
	}
	
	public void confirmarEnvasado(){
		this.estaEnvasado = true; 
	}
	
}
