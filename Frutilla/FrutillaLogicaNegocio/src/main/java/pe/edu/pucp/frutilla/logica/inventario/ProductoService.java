/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.inventario;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.crud.mysql.inventario.ProductoMySQL;

public class ProductoService {
    private ProductoMySQL proSQL;
    
    public ProductoService (){
        proSQL=new ProductoMySQL();
    }
    
    public void agregar(Producto prod) throws Exception{
        if(prod==null)
            throw new Exception("EL producto ha ingresado nulo");
        if(prod.getNombre()==null||prod.getNombre().trim().isEmpty())
            throw new Exception("El nombre del producto no puede ser vacio");
        if(prod.getDescripcion()==null||prod.getDescripcion().trim().isEmpty())
            throw new Exception("La descripcion del producto no puede ser vacio");
        if(prod.getCodigoProd()==null||prod.getCodigoProd().trim().isEmpty())
            throw new Exception("El codigo del producto no puede ser vacio");
        if(prod.getPrecioUnitario()<=0)
            throw new Exception("El precio del producto no puede menor a 0");
        if(prod.getStockMinimo()<=0)
            throw new Exception("El stock minimo del producto no puede menor a 0");
        proSQL.agregar(prod);
    }
    
    public void actualizar(Producto prod) throws Exception{
        if(prod==null)
            throw new Exception("EL producto ha ingresado nulo");
        if(prod.getNombre()==null||prod.getNombre().trim().isEmpty())
            throw new Exception("El nombre del producto no puede ser vacio");
        if(prod.getDescripcion()==null||prod.getDescripcion().trim().isEmpty())
            throw new Exception("La descripcion del producto no puede ser vacio");
        if(prod.getCodigoProd()==null||prod.getCodigoProd().trim().isEmpty())
            throw new Exception("El codigo del producto no puede ser vacio");
        if(prod.getPrecioUnitario()<=0)
            throw new Exception("El precio del producto no puede menor a 0");
        if(prod.getStockMinimo()<=0)
            throw new Exception("El stock minimo del producto no puede menor a 0");
        proSQL.actualizar(prod);
    }
    
    public void eliminar(int idProducto) throws Exception{
        if(idProducto<=0)
            throw new Exception("El id de producto no es válido");
        proSQL.eliminar(idProducto);
    }
    
    public Producto obtenerPorId(int idProducto) throws Exception{
        if(idProducto<=0)
            throw new Exception("El id de producto no es válido");
        Producto prod=proSQL.obtener(idProducto);
        return prod;
    }
    
    public List<Producto> listar(String nombre) throws Exception{
        if(nombre==null||nombre.trim().isEmpty()){
            List<Producto> prods = proSQL.listarTodos();
            return prods;
        }
        else{
            ArrayList<Producto> prods = proSQL.obtenerPorNombre(nombre);
            return prods;
        } 
    }
    
    public ArrayList<Producto> listarPorLocal(int idLocal) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id de local no puede ser menor a 0");
        return proSQL.obtenerTodosPorLocal(idLocal);
    }
}
