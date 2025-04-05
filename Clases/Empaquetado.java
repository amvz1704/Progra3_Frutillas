public class Empaquetado extends Utileria {
	private char tipo;//B: para bebidas,F: para fruta,N: no determinado
	private float capacidad; //para el caso los empaquetados de bebidas (vasos)
	private String unidades; //la unidad de medida de la capacidad (onzas)
	private String material; 
	
	public Empaquetado (String nombre,String descripcion,char tipo){
		super(nombre,descripcion);
		this.capacidad=-1;
		this.tipo=tipo;
	}
	public void setCapacidad (float capacidad){
		this.capacidad=capacidad;
	}
	public float getCapacidad (){
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
	public llenarDatosAdicionales(float capacidad,String unidades,String material){
		setCapacidad(capacidad);
		setMaterial(material);
		setUnidades(unidades);
	}
	@Override
	public String imprimirDatosReporte(){
		String cadena="Id: "+getId()+" Nombre: "+getNombre()+" Descripcion: "+getDescripcion()+" Stock: "+
		getStock()+" Costo: "+getCosto()+" Tipo: ";
		if (tipo==B){
			cadena+=" Para bebidas Capacidad: "+getCapacidad()+" "+getUnidades()+"\n";
		}
		else{
			cadena+=" Para frutas \n";
		}
	}

	
}