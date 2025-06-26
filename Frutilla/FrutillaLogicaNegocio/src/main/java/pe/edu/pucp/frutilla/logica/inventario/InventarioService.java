/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.inventario;

import java.sql.SQLException;
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
    
     public void actualizarStock(Producto producto, int idLocal) throws Exception {
        if (idLocal <= 0)
            throw new Exception("El id del local no puede ser negativo");
        if (producto.getIdProducto() <= 0)
            throw new Exception("El id de producto no puede ser negativo");
        if (producto.getStock() < 0)
            throw new Exception("El stock no puede ser negativo");

        try {
            invSQL.actualizarStock(producto, idLocal);
        } catch (SQLException e) {
            throw new Exception("Error al actualizar el stock en la base de datos: " + e.getMessage(), e);
        }
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
        if(prod.getStock()>stockActual){
            prod.setTipoEstado(TipoEstado.DISPONIBLE);
            invSQL.actualizarStock(prod, idLocal);
        }
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
    
    public ArrayList<Producto> listarPorTipo(int idLocal, char tipo) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id del loccal no puede ser negativo");
        if(!(tipo == 'F' || tipo == 'S' || tipo == 'B' || tipo == 'P' || tipo == 'T'))
            throw new Exception("El tipo de producto no es valido");
        return invSQL.listarPorTipo(idLocal, tipo);
    }
    
    public char obtenerTipoProducto(int idProducto, int idLocal) throws Exception {
        if(idLocal<=0)
            throw new Exception("El id del loccal no puede ser negativo");
        if(idProducto<=0)
            throw new Exception("El id de producto no puede ser negativo");
        return invSQL.obtenerTipoProducto(idProducto, idLocal);
    }
}
