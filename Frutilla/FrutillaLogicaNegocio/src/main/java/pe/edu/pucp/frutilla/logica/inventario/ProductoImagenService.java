/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.inventario;

import java.util.List;
import pe.edu.pucp.frutilla.crud.mysql.inventario.ProductoImagenMySQL;
import pe.edu.pucp.frutilla.models.inventario.ProductoImagen;

/**
 *
 * @author Desktop
 */
public class ProductoImagenService {
    private ProductoImagenMySQL productoImagenMySQL;
    
   public ProductoImagenService(){
       productoImagenMySQL=new ProductoImagenMySQL();
   }
   
   public void agregar(ProductoImagen prodImagen) throws Exception{
       if(prodImagen.getIdProductoImagen()<=0)
           throw new Exception("el id no puede ser menor o igual a 0");
       if(prodImagen.getUtlImagen()==null||prodImagen.getUtlImagen().trim().isEmpty())
           throw new Exception("la utl no puede ser vacia");
      productoImagenMySQL.agregar(prodImagen);           
   }
   public void actualizar(ProductoImagen prodImagen) throws Exception{
       if(prodImagen.getIdProductoImagen()<=0)
           throw new Exception("el id no puede ser menor o igual a 0");
       if(prodImagen.getUtlImagen()==null||prodImagen.getUtlImagen().trim().isEmpty())
           throw new Exception("la utl no puede ser vacia");
       productoImagenMySQL.actualizar(prodImagen);
   }
   public void eliminar(int idProductoImagen) throws Exception{
       if(idProductoImagen<=0)
           throw new Exception("el id no puede ser menor o igual a 0");
       productoImagenMySQL.eliminar(idProductoImagen);
   }
   public ProductoImagen obtener(int idProductoImagen) throws Exception{
       if(idProductoImagen<=0)
           throw new Exception("el id no puede ser menor o igual a 0");
       return productoImagenMySQL.obtener(idProductoImagen);
   }
   public String obtenerUrlPorTipo(char tipoProducto){
       return productoImagenMySQL.obtenerImagenPorTipo(tipoProducto);
   }
   public List<ProductoImagen> listar() throws Exception{
       return productoImagenMySQL.listarTodos();
   }
}
