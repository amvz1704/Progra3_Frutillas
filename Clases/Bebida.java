

import java.util.ArrayList; 

class Bebida extends Producto{
	private int idBebida; 
	private int tamanioOz; 
	private String tipo; 
	private String endulzante;
	private tipoLeche tieneLeche;
	private ArrayList<Fruta> frutasBebida;
	
	//como juntar fruta
	
	Bebida(){
		super.Producto();
		frutasBebida = new ArrayList<Fruta>();
	}
	
	Bebida(int idProducto, String nombre, String descripcion, String codigoProd,
		LocalDate fechaProduccion, LocalTime fechaVencimiento, 
		double precioUnitario,int stock, int stockMinimo,
		int idBebida, int tamanioOz, String tipo, 
		String endulzante, tipoLeche tieneLeche){
		
		super.Producto(int idProducto, String nombre, String descripcion, String codigoProd,
		LocalDate fechaProduccion, LocalTime fechaVencimiento, 
		double precioUnitario,int stock, int stockMinimo);
		this.idBebida = idBebida; 
		this.tamanioOz = tamanioOz; 
		this.tipo = tipo; 
		this.endulzante = endulzante; 
		this.tieneLeche = tieneLeche; 
		frutasBebida = new ArrayList<Fruta>();
		
	}
	
	Bebida(Bebida original){
		
		super(original);
		this.idBebida = original.idBebida; 
		this.tamanioOz = original.tamanioOz; 
		this.tipo = original.tipo; 
		this.endulzante = original.endulzante; 
		this.tieneLeche = original.tieneLeche; 
		this.frutasBebida = new ArrayList<Fruta>();
		
		//copiar el arreglo!
		for(Fruta i: original.frutasBebida){
			this.frutasBebida.add(new Fruta(i));
		}
	}
	
	
}