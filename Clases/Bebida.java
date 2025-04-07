

import java.util.ArrayList; 
import java.time.*;

class Bebida extends Producto{ 
	private int tamanioOz; 
	private String tipo; 
	private String endulzante;
	private tipoLeche tieneLeche;
	private ArrayList<Fruta> frutasBebida;
	
	//como juntar fruta
	
	Bebida(){
		super();
		frutasBebida = new ArrayList<Fruta>();
	}
	
	Bebida(String nombre, String descripcion, String codigoProd,
		LocalDate fechaProduccion, LocalDate fechaVencimiento, 
		double precioUnitario,int stock, int stockMinimo,
		int idBebida, int tamanioOz, String tipo, 
		String endulzante, tipoLeche tieneLeche){
		
		super(nombre, descripcion, codigoProd,
		fechaProduccion, fechaVencimiento, 
		precioUnitario, stock, stockMinimo); 
		this.tamanioOz = tamanioOz; 
		this.tipo = tipo; 
		this.endulzante = endulzante; 
		this.tieneLeche = tieneLeche; 
		frutasBebida = new ArrayList<Fruta>();
		
	}
	
	Bebida(Bebida original){
		
		super(original);
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
	
	public void agregarFruta(Fruta fruta){
		this.frutasBebida.add(fruta);
	}
}