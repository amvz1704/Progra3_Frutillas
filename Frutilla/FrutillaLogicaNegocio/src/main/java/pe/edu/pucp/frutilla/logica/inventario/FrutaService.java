/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.inventario;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.frutilla.crud.mysql.inventario.ProductoMySQL;
import pe.edu.pucp.frutilla.models.inventario.Fruta;
import pe.edu.pucp.frutilla.crud.mysql.inventario.FrutaMySQL;

public class FrutaService {
    private FrutaMySQL fruSQL;
    
    public FrutaService(){
        fruSQL=new FrutaMySQL();
    }
    
    public void agregar(Fruta fru)throws Exception{
        if(fru==null)
            throw new Exception("La bebida ha ingresado nulo");
        if(fru.getNombre()==null||fru.getNombre().trim().isEmpty())
            throw new Exception("El nombre no puede ser vacio");
        if(fru.getDescripcion()==null||fru.getDescripcion().trim().isEmpty())
            throw new Exception("La descripcion no puede ser vacia");
        if(fru.getCodigoProd()==null||fru.getCodigoProd().trim().isEmpty())
            throw new Exception("El codigo no puede ser vacio");
        if(fru.getPrecioUnitario()<=0)
            throw new Exception("El precio no puede ser menor a 0");
        if(fru.getStockMinimo()<=0)
            throw new Exception("El stock minimo no puede ser menor a 0");
        if(fru.isRequiereEnvase()==null)
            throw new Exception("El requiereEnvase no puede ser nulo");
        if(fru.getEnvase()==null||fru.getEnvase().trim().isEmpty())
            throw new Exception("El envase no puede ser vacio");
        if(fru.isEstaEnvasado()==null)
            throw new Exception("El estaEnvasado no puede ser nulo");
        if(fru.isRequiereLimpieza()==null){
            throw new Exception("El requiereLimpieza no puede ser nulo");
        }
        fruSQL.agregar(fru);
    }
    
    public void actualizar(Fruta fru)throws Exception{
        if(fru==null)
            throw new Exception("La bebida ha ingresado nulo");
        if(fru.getNombre()==null||fru.getNombre().trim().isEmpty())
            throw new Exception("El nombre no puede ser vacio");
        if(fru.getDescripcion()==null||fru.getDescripcion().trim().isEmpty())
            throw new Exception("La descripcion no puede ser vacia");
        if(fru.getCodigoProd()==null||fru.getCodigoProd().trim().isEmpty())
            throw new Exception("El codigo no puede ser vacio");
        if(fru.getPrecioUnitario()<=0)
            throw new Exception("El precio no puede ser menor a 0");
        if(fru.getStockMinimo()<=0)
            throw new Exception("El stock minimo no puede ser menor a 0");
        if(fru.isRequiereEnvase()==null)
            throw new Exception("El requiereEnvase no puede ser nulo");
        if(fru.getEnvase()==null||fru.getEnvase().trim().isEmpty())
            throw new Exception("El envase no puede ser vacio");
        if(fru.isEstaEnvasado()==null)
            throw new Exception("El estaEnvasado no puede ser nulo");
        if(fru.isRequiereLimpieza()==null){
            throw new Exception("El requiereLimpieza no puede ser nulo");
        }
        fruSQL.actualizar(fru);
    }
    
    public void eliminar(int idFruta) throws Exception{
        if(idFruta<=0)
            throw new Exception("El id de la fruta no es válido");
        ProductoMySQL proSQL = new ProductoMySQL();
        fruSQL.eliminar(idFruta);
        proSQL.eliminar(idFruta);
    }
    
    public Fruta obtenerPorId(int idFruta) throws Exception{
        if(idFruta<=0)
            throw new Exception("El id de la fruta no es válido");
        Fruta fru = fruSQL.obtener(idFruta);
        return fru;
    }
    
    public List<Fruta> listar() throws Exception{
        return fruSQL.listarTodos();
    }
    
    public ArrayList<Fruta> listarPorLocal(int idLocal) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id de local no puede ser menor a 0");
        return fruSQL.obtenerTodosPorLocal(idLocal);
    }
}
