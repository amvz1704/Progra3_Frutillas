/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.inventario;

import java.util.ArrayList;
import pe.edu.pucp.frutilla.crud.mysql.inventario.InventarioMySQL;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.inventario.TipoEstado;

/**
 *
 * @author Desktop
 */
public class InventarioService {
    private InventarioMySQL invSQL;
    
    public InventarioService (){
        invSQL=new InventarioMySQL();
    }
    
    public void insertar(Producto prod,int idLocal) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id de local no puede ser negativo");
        if(prod.getIdProducto()<=0)
            throw new Exception("El id de producto no puede ser negativo");
        if(prod.getStock()<0)
            throw new Exception("El stock no puede ser negativo");
        if(prod.getTipoEstado().name().trim().isEmpty())
            throw new Exception("El estado no puede ser vacio");
        invSQL.insertarInventario(prod, idLocal);
    }
    public void actualizar(Producto prod,int idLocal) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id de local no puede ser negativo");
        if(prod.getIdProducto()<=0)
            throw new Exception("El id de producto no puede ser negativo");
        if(prod.getStock()<0)
            throw new Exception("El stock no puede ser negativo");
        if(prod.getTipoEstado().name().trim().isEmpty())
            throw new Exception("El estado no puede ser vacio");
        int stockActual = obtenerStockPorId(prod.getIdProducto(), idLocal);
        if(stockActual>prod.getStock())
            invSQL.actualizarStock(prod, idLocal);
        else if(stockActual==prod.getStock()){
            prod.setTipoEstado(TipoEstado.AGOTADO);
            invSQL.actualizarInventario(prod, idLocal);
        }
        else
            throw new Exception("No hay sufieciente stock");
    }
    public void eliminar(int idProducto,int idLocal) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id del loccal no puede ser negativo");
        if(idProducto<=0)
            throw new Exception("El id de producto no puede ser negativo");
        invSQL.eliminarInventario(idProducto, idLocal);
    }
    public int obtenerStockPorId(int idProducto,int idLocal) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id del loccal no puede ser negativo");
        if(idProducto<=0)
            throw new Exception("El id de producto no puede ser negativo");
        return invSQL.obtenerInventarioPorId(idProducto, idLocal);
    }
    public ArrayList<Producto> listar(int idLocal) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id del loccal no puede ser negativo");
        return invSQL.obtenerTodos(idLocal);
    }
}
