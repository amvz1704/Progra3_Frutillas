package com.frutilla.models.inventario;

import java.util.ArrayList; 

//se hizo publico y se agrego

public class Bebida extends Producto{
	private int tamanioOz; 
	private String tipo; 
	private String endulzante;
	private TipoLeche tieneLeche;
	private ArrayList<FrutasBebida> frutasBebida;
	
	
	
	public Bebida(){
		super();
		frutasBebida = new ArrayList<FrutasBebida>();
	}
	
	public Bebida(int idProducto, String nombre, String descripcion, String codigoProd, double precioUnitario,int stock, int stockMinimo, int tamanioOz, String tipo, String endulzante, TipoLeche tieneLeche,ArrayList<FrutasBebida> frutasBebidas){
		
		super(idProducto, nombre, descripcion, codigoProd, precioUnitario, stock, stockMinimo);
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
		this.tamanioOz = original.tamanioOz; 
		this.tipo = original.tipo; 
		this.endulzante = original.endulzante; 
		this.tieneLeche = original.tieneLeche; 
		this.frutasBebida = new ArrayList<FrutasBebida>();
		
		//copiar el arreglo!
		for(FrutasBebida f: original.frutasBebida){
			this.frutasBebida.add(f);
		}
	}
	
	public int getTamanioOz() {
        return tamanioOz;
    }

    public void setTamanioOz(int tamanioOz) {
        this.tamanioOz = tamanioOz;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEndulzante() {
        return endulzante;
    }

    public void setEndulzante(String endulzante) {
        this.endulzante = endulzante;
    }

    public TipoLeche getTieneLeche() {
        return tieneLeche;
    }

    public void setTieneLeche(TipoLeche tieneLeche) {
        this.tieneLeche = tieneLeche;
    }

    public ArrayList<FrutasBebida> getFrutasBebida() {
        return frutasBebida;
    }

    public void setFrutasBebida(ArrayList<FrutasBebida> frutasBebida) {
        this.frutasBebida = frutasBebida;
    }
	
	//como juntar fruta
	
	@Override
	public String toString(){
		
		//colocar un formato
		String cadena = super.toString() +", Tipo: " + tipo + ", Endulzante: " + endulzante;
		
		if(tieneLeche != null){
			cadena += ", Leche: " + tieneLeche;
		}
		cadena += ", Frutas: ";
		for(FrutasBebida fruta: frutasBebida){
			cadena += fruta + " ";
		}
		
		return cadena; 
	}
	
	
}
