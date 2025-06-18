/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.models.inventario;

public class ProductoImagen {
    private int idProductoImagen;
    private char tipoProducto;
    private String utlImagen;

    public ProductoImagen() {
        tipoProducto='P';
    }
    
    public char getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(char tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getUtlImagen() {
        return utlImagen;
    }

    public void setUtlImagen(String utlImagen) {
        this.utlImagen = utlImagen;
    }

    public int getIdProductoImagen() {
        return idProductoImagen;
    }

    public void setIdProductoImagen(int idProductoImagen) {
        this.idProductoImagen = idProductoImagen;
    }
}
