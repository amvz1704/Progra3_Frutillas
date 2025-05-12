/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.inventario;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.frutilla.crud.mysql.inventario.BebidaMySQL;
import pe.edu.pucp.frutilla.crud.mysql.inventario.ProductoMySQL;
import pe.edu.pucp.frutilla.models.inventario.Bebida;

/**
 *
 * @author Desktop
 */
public class BebidaService {
    private BebidaMySQL bebSQL;
    
    public BebidaService(){
        bebSQL=new BebidaMySQL();
    }
    
    public void agregar(Bebida beb)throws Exception{
        if(beb==null)
            throw new Exception("La bebida ha ingresado nulo");
        if(beb.getNombre()==null||beb.getNombre().trim().isEmpty())
            throw new Exception("El nombre no puede ser vacio");
        if(beb.getDescripcion()==null||beb.getDescripcion().trim().isEmpty())
            throw new Exception("La descripcion no puede ser vacia");
        if(beb.getCodigoProd()==null||beb.getCodigoProd().trim().isEmpty())
            throw new Exception("El codigo no puede ser vacio");
        if(beb.getPrecioUnitario()<=0)
            throw new Exception("El precio no puede ser menor a 0");
        if(beb.getStockMinimo()<=0)
            throw new Exception("El stock minimo no puede ser menor a 0");
        if(beb.getTamanioOz()<=0)
            throw new Exception("El tamaño en onzas no puede ser menor a 0");
        if(beb.getTipo()==null||beb.getTipo().trim().isEmpty())
            throw new Exception("El tipo no puede estar vacio");
        if(beb.getEndulzante()==null||beb.getEndulzante().trim().isEmpty())
            throw new Exception("El endulzante no puede ser vacio");
        if(beb.getTieneLeche().name()==null
                ||beb.getTieneLeche().name().trim().isEmpty())
            throw new Exception("El tipo de Leche no puede ser vacio");
        bebSQL.agregar(beb);
    }
    
    public void actualizar(Bebida beb)throws Exception{
        if(beb==null)
            throw new Exception("La bebida ha ingresado nulo");
        if(beb.getNombre()==null||beb.getNombre().trim().isEmpty())
            throw new Exception("El nombre no puede ser vacio");
        if(beb.getDescripcion()==null||beb.getDescripcion().trim().isEmpty())
            throw new Exception("La descripcion no puede ser vacia");
        if(beb.getCodigoProd()==null||beb.getCodigoProd().trim().isEmpty())
            throw new Exception("El codigo no puede ser vacio");
        if(beb.getPrecioUnitario()<=0)
            throw new Exception("El precio no puede ser menor a 0");
        if(beb.getStockMinimo()<=0)
            throw new Exception("El stock minimo no puede ser menor a 0");
        if(beb.getTamanioOz()<=0)
            throw new Exception("El tamaño en onzas no puede ser menor a 0");
        if(beb.getTipo()==null||beb.getTipo().trim().isEmpty())
            throw new Exception("El tipo no puede estar vacio");
        if(beb.getEndulzante()==null||beb.getEndulzante().trim().isEmpty())
            throw new Exception("El endulzante no puede ser vacio");
        if(beb.getTieneLeche().name()==null
                ||beb.getTieneLeche().name().trim().isEmpty())
            throw new Exception("El tipo de Leche no puede ser vacio");
        bebSQL.actualizar(beb);
    }
    
    public void eliminar(int idBebida) throws Exception{
        if(idBebida<=0)
            throw new Exception("El id de la bebida no es válido");
        ProductoMySQL proSQL = new ProductoMySQL();
        bebSQL.eliminar(idBebida);
        proSQL.eliminar(idBebida);
    }
    
    public Bebida obtenerPorId(int idBebida) throws Exception{
        if(idBebida<=0)
            throw new Exception("El id de la bebida no es válido");
        Bebida beb = bebSQL.obtener(idBebida);
        return beb;
    }
    
    public List<Bebida> listar(String nombre) throws Exception{
        return bebSQL.listarTodos();
    }
    
    public ArrayList<Bebida> listarPorLocal(int idLocal) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id de local no puede ser menor a 0");
        return bebSQL.obtenerTodosPorLocal(idLocal);
    }
}
