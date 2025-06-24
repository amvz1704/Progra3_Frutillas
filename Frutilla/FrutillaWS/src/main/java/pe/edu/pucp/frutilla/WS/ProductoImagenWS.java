/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import java.util.List;
import pe.edu.pucp.frutilla.logica.inventario.ProductoImagenService;
import pe.edu.pucp.frutilla.models.inventario.ProductoImagen;

@WebService(serviceName = "ProductoImagenWS")
public class ProductoImagenWS {
    private ProductoImagenService productoImagenService;
    
    public ProductoImagenWS(){
        productoImagenService=new ProductoImagenService();
    }
    
    @WebMethod(operationName = "agregarProductoImagen")
    public void agregarProductoImagen(ProductoImagen productoImagen){
        try{
            productoImagenService.agregar(productoImagen);
        } catch(Exception ex){
            System.out.println(ex.getMessage()); 
        }
    }
    
    @WebMethod(operationName = "actualizarProductoImagen")
    public void actualizarProductoImagen(ProductoImagen productoImagen){
        try{
            productoImagenService.actualizar(productoImagen);
        } catch(Exception ex){
            System.out.println(ex.getMessage()); 
        }
    }
    
    @WebMethod(operationName = "eliminarProductoImagen")
    public void eliminarProductoImagen(int idProductoImagen){
        try{
            productoImagenService.eliminar(idProductoImagen);
        } catch(Exception ex){
            System.out.println(ex.getMessage()); 
        }
    }
    
    @WebMethod(operationName = "obtenerProductoImagen")
    public String obtenerProductoImagen(int idProductoImagen){
        try{
            ProductoImagen temp = productoImagenService.obtener(idProductoImagen);
            if (temp.getUrlImagen()==null||temp.getUrlImagen().trim().isEmpty()){
                return "~/imagenes/placeholder.jpg";
            }
            return temp.getUrlImagen();
        } catch(Exception ex){
            System.out.println(ex.getMessage()); 
        }
        return "~/imagenes/placeholder.jpg";
    }
    
    @WebMethod(operationName = "listarProductoImagen")
    public List<ProductoImagen> listarProductoImagen(){
        try{
            return productoImagenService.listar();
        } catch(Exception ex){
            System.out.println(ex.getMessage()); 
        }
        return null;
    }
}
