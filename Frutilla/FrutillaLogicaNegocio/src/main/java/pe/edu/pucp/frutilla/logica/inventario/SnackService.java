/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.inventario;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.frutilla.crud.mysql.inventario.ProductoMySQL;
import pe.edu.pucp.frutilla.models.inventario.Snack;
import pe.edu.pucp.frutilla.crud.mysql.inventario.SnackMySQL;


public class SnackService {
    private SnackMySQL snaSQL;
    
    public SnackService(){
        snaSQL=new SnackMySQL();
    }
    
    public void agregar(Snack sna)throws Exception{
        if(sna==null)
            throw new Exception("La bebida ha ingresado nulo");
        if(sna.getNombre()==null||sna.getNombre().trim().isEmpty())
            throw new Exception("El nombre no puede ser vacio");
        if(sna.getDescripcion()==null||sna.getDescripcion().trim().isEmpty())
            throw new Exception("La descripcion no puede ser vacia");
        if(sna.getCodigoProd()==null||sna.getCodigoProd().trim().isEmpty())
            throw new Exception("El codigo no puede ser vacio");
        if(sna.getPrecioUnitario()<=0)
            throw new Exception("El precio no puede ser menor a 0");
        if(sna.getStockMinimo()<=0)
            throw new Exception("El stock minimo no puede ser menor a 0");
        if(sna.isRequiereEnvase()==null)
            throw new Exception("El requiereEnvase no puede ser nulo");
        if(sna.getEnvase()==null||sna.getEnvase().trim().isEmpty())
            throw new Exception("El envase no puede ser vacio");
        if(sna.isEstaEnvasado()==null)
            throw new Exception("El estaEnvasado no puede ser nulo");
        snaSQL.agregar(sna);
    }
    
    public void actualizar(Snack sna)throws Exception{
        if(sna==null)
            throw new Exception("La bebida ha ingresado nulo");
        if(sna.getNombre()==null||sna.getNombre().trim().isEmpty())
            throw new Exception("El nombre no puede ser vacio");
        if(sna.getDescripcion()==null||sna.getDescripcion().trim().isEmpty())
            throw new Exception("La descripcion no puede ser vacia");
        if(sna.getCodigoProd()==null||sna.getCodigoProd().trim().isEmpty())
            throw new Exception("El codigo no puede ser vacio");
        if(sna.getPrecioUnitario()<=0)
            throw new Exception("El precio no puede ser menor a 0");
        if(sna.getStockMinimo()<=0)
            throw new Exception("El stock minimo no puede ser menor a 0");
         if(sna.isRequiereEnvase()==null)
            throw new Exception("El requiereEnvase no puede ser nulo");
        if(sna.getEnvase()==null||sna.getEnvase().trim().isEmpty())
            throw new Exception("El envase no puede ser vacio");
        if(sna.isEstaEnvasado()==null)
            throw new Exception("El estaEnvasado no puede ser nulo");
        snaSQL.actualizar(sna);
    }
    
    public void eliminar(int idSnack) throws Exception{
        if(idSnack<=0)
            throw new Exception("El id del snack no es válido");
        ProductoMySQL proSQL = new ProductoMySQL();
        snaSQL.eliminar(idSnack);
        proSQL.eliminar(idSnack);
    }
    
    public Snack obtenerPorId(int idSnack) throws Exception{
        if(idSnack<=0)
            throw new Exception("El id del snack no es válido");
        Snack sna = snaSQL.obtener(idSnack);
        return sna;
    }
    
    public List<Snack> listar(String nombre) throws Exception{
        return snaSQL.listarTodos();
    }
    
    public ArrayList<Snack> listarPorLocal(int idLocal) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id de local no puede ser menor a 0");
        return snaSQL.obtenerTodosPorLocal(idLocal);
    }
}
