public class ArtEmpaquetado extends Utileria {
	private char tipo;//B: para bebidas,F: para fruta,N: no determinado
	private double capacidad; 
	private String unidades; 
	private String material; 
	
	public ArtEmpaquetado (String nombre,String descripcion,
	double costoUnitario,char tipo){
		super(nombre,descripcion,costoUnitario);
		this.capacidad=-1;
		this.tipo=tipo;
	}
	public void setCapacidad (double capacidad){
		this.capacidad=capacidad;
	}
	public double getCapacidad (){
		return capacidad;
	}
	public void setUnidades(String unidades){
		this.unidades=unidades;
	}
	public String getUnidades (){
		return unidades;
	}
	public void setMaterial(String material){
		this.material=material;
	}
	public String getMaterial (){
		return material;
	}
	public void llenarDatosAdicionales(double capacidad,String unidades,String material){
		setCapacidad(capacidad);
		setMaterial(material);
		setUnidades(unidades);
	}
	@Override
	public String imprimirDatosReporte(){
		String cadena="Id: "+getId()+" Nombre: "+getNombre()+" Descripcion: "+getDescripcion()+" Stock: "+
		getStock()+" Costo: "+getCosto()+" Tipo: ";
		if (tipo=='B'){
			cadena+=" Para bebidas Capacidad: "+getCapacidad()+" "+getUnidades()+"\n";
		}
		else{
			cadena+=" Para frutas Capacidad: "+getCapacidad()+" "+getUnidades()+"\n";;
		}
		return cadena;
	}
}
