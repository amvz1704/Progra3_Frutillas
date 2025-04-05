public class ArtMantenimiento extends Utileria{
	private char tipo;//H: herramienta,P: pieza de emergencia
	
	public ArtMantenimiento (String nombre,String descripcion,double costoUnitario,char tipo){
		super(nombre,descripcion,costoUnitario);
		this.tipo=tipo;
	}
	public void setTipo(char tipo){
		this.tipo=tipo;
	}
	public char getTipo(){
		return tipo;
	}
	@Override
	public String imprimirDatosReporte(){
		String cadena="Id: "+getId()+" Nombre: "+getNombre()+" Descripcion: "+getDescripcion()+" Stock: "+
		getStock()+" Costo: "+getCosto()+" Tipo: ";
		if (tipo==H){
			cadena+=" Herramienta \n";
		}
		else{
			cadena+=" Pieza de repuesto \n";
		}
	}
}