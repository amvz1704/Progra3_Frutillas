public abstract class Utileria {
	private static int correlativo=1; 
	private int idUtelieria;
	private String nombre;
	private String descripcion;
	private int stock; 
	private double costoUnitario;
	private char activo; //activo: A, inactivo: I (en particular importante para el caso de las piezas)
	
	//se va a tomar en cuenta que toda creación de objeto tiene las caracteristicas de nombre,
	//descripcion y costo unitario;
	public Utileria (String nombre,String descripcion,double costoUnitario){
		this.idUtelieria=correlativo;
		this.stock=0;
		this.activo='A';
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.costoUnitario=costoUnitario;
		correlativo++;
	}
	public int getId(){
		return idUtelieria;
	}
	public int getStock(){
		return stock;
	}
	public void setNombre (String nombre){
		this.nombre=nombre;
	}
	public String getNombre (){
		return nombre;
	}
	public void setDescripcion(String descripcion){
		this.descripcion=descripcion;
	}
	public String getDescripcion (){
		return descripcion;
	}
	public void setCosto(double costoUnitario){
		this.costoUnitario=costoUnitario;
	}
	public double getCosto(){
		return costoUnitario;
	}
	public void desactivarUtileria(){
		activo='I';
	}
	public char estaActivo(){
		return activo;
	}
	public String imprimirDatosPedido(){
		String cadena="Id: "+getId()+" "+getNombre()+" S/. "+getCosto;
	}
	public abstract String imprimirDatosReporte();
}