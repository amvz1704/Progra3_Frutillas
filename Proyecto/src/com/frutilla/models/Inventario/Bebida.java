package com.frutilla.models.Inventario;

import java.util.ArrayList; 

//se hizo publico y se agrego

public class Bebida extends Producto{
	private int idBebida; 
	private int tamanioOz; 
	private String tipo; 
	private String endulzante;
	private tipoLeche tieneLeche;
	private ArrayList<FrutasBebida> frutasBebida;
	
	
	
	public Bebida(){
		super();
		frutasBebida = new ArrayList<FrutasBebida>();
	}
	
	public Bebida(int idProducto, String nombre, String descripcion, String codigoProd, double precioUnitario,int stock, int stockMinimo, int idBebida, int tamanioOz, String tipo, String endulzante, tipoLeche tieneLeche,ArrayList<FrutasBebida> frutasBebidas){
		
		super(idProducto, nombre, descripcion, codigoProd, precioUnitario, stock, stockMinimo);
		this.idBebida = idBebida; 
		this.tamanioOz = tamanioOz; 
		this.tipo = tipo; 
		this.endulzante = endulzante; 
		this.tieneLeche = tieneLeche; 
		frutasBebida = new ArrayList<FrutasBebida>();
		for(FrutasBebida f: frutasBebidas){
			this.frutasBebida.add(f);
		}
	}
	
	public Bebida(Bebida original){
		
		super(original);
		this.idBebida = original.idBebida; 
		this.tamanioOz = original.tamanioOz; 
		this.tipo = original.tipo; 
		this.endulzante = original.endulzante; 
		this.tieneLeche = original.tieneLeche; 
		this.frutasBebida = new ArrayList<FrutasBebida>();
		
		//copiar el arreglo!
		for(FrutasBebida i: original.frutasBebida){
			this.frutasBebida.add(i);
		}
	}
	
	//como juntar fruta
	
	@Override
	public String toString(){
		
		//colocar un formato
		String cadena = super.toString() +", Tipo: " + tipo + ", Endulzante: " + endulzante;
		
		if(tieneLeche != null){
			cadena += ", Leche: " + tieneLeche;
		}
		cadena += "\nFrutas: ";
		for(FrutasBebida fruta: frutasBebida){
			cadena += fruta + " ";
		}
		
		return cadena; 
	}
	
	
}
