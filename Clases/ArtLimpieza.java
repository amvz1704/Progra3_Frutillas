public class ArtLimpieza extends Utileria{
	private char tipo; //G: para usos generales del local,A: especializado para limpieza de alimentos
	
	public ArtLimpieza (String nombre,String descripcion,double costoUnitario,char tipo){
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
		if (tipo==G){
			cadena+=" Uso general del local \n";
		}
		else{
			cadena+=" Uso para limpieza de alimentos \n";
		}
	}
}