package com.frutilla.models.Inventario;

import java.util.ArrayList; 
import java.time.*;

//se hizo publico y se agrego

public class Bebida extends Producto{
	private int idBebida; 
	private int tamanioOz; 
	private String tipo; 
	private String endulzante;
	private tipoLeche tieneLeche;
	private ArrayList<Fruta> frutasBebida;
	
	
	
	public Bebida(){
		super();
		frutasBebida = new ArrayList<Fruta>();
	}
	
	public Bebida(int idProducto, String nombre, String descripcion, String codigoProd,
		LocalDate fechaProduccion, LocalDate fechaVencimiento, 
		double precioUnitario,int stock, int stockMinimo,
		int idBebida, int tamanioOz, String tipo, 
		String endulzante, tipoLeche tieneLeche){
		
		super(idProducto, nombre, descripcion, codigoProd,
		fechaProduccion, fechaVencimiento, 
		precioUnitario, stock, stockMinimo);
		this.idBebida = idBebida; 
		this.tamanioOz = tamanioOz; 
		this.tipo = tipo; 
		this.endulzante = endulzante; 
		this.tieneLeche = tieneLeche; 
		frutasBebida = new ArrayList<Fruta>();
		
	}
	
	public Bebida(Bebida original){
		
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
	
	//como juntar fruta
	
	@Override
	public String toString(){
		
		//colocar un formato
		String cadena = super.toString() +", Tipo: " + tipo + ", Endulzante: " + endulzante;
		
		if(tieneLeche != null){
			cadena += ", Leche: " + tieneLeche;
		}
		cadena += "\nFrutas: ";
		for(Fruta fruta: frutasBebida){
			cadena += fruta.getNombre() + " ";
		}

		
		
		return cadena; 
	}
	
	public void agregarFruta(Fruta fruta){
		
		frutasBebida.add(fruta); 
	} 
	
	
}
