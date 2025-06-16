package pe.edu.pucp.frutilla.logica.local;

/**
 *
 * @author NAMS_
 */

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.frutilla.models.local.Local; 
import pe.edu.pucp.frutilla.crud.dao.local.LocalDAO;
import pe.edu.pucp.frutilla.crud.mysql.local.LocalMySQL; 



public class LocalService {
    private final LocalDAO localDAO;
    
    public LocalService(){
        this.localDAO = new LocalMySQL(); //creamos una instancia de localDAO
    }
    
    public void agregar(Local loc)throws Exception {
        if(loc==null)
            throw new Exception("El local a ingresado nulo");
        if(loc.getNombre()==null||loc.getNombre().trim().isEmpty())
            throw new Exception("El nombre del local no puede ser vacio");
        localDAO.agregar(loc);
        
    }
    
     public void actualizar(Local loc)throws Exception{
        if(loc==null)
            throw new Exception("El local a ingresado nulo");
        if (loc.getIdLocal() <= 0)
            throw new Exception("El id del local no puede ser menor o igual a 0");
        if (localDAO.obtener(loc.getIdLocal()) == null)
            throw new Exception("El id del local no se encuentra en la base de datos");
        if(loc.getNombre()==null||loc.getNombre().trim().isEmpty())
            throw new Exception("El nombre del local no puede ser vacio");
        if(loc.getDescripcion()==null||loc.getDescripcion().trim().isEmpty())
            throw new Exception("La descripcion del local no puede ser vacia");
        if(loc.getDireccion()==null||loc.getDireccion().trim().isEmpty())
            throw new Exception("La direccion del local no puede ser vacia");
        if(loc.getTelefono()==null||loc.getTelefono().trim().isEmpty())
            throw new Exception("El telefono del local no puede ser vacia");
        if(loc.getIdSupervisor()<=0)
            throw new Exception("El id del supervisor no es valido");

        
        localDAO.actualizar(loc);
    }
    
     
    //deshanilitar el local
    public void eliminar(int idLocal) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id del Local no es válido");
        if (localDAO.obtener(idLocal) == null) 
            throw new Exception("El id del local no se encuentra en la base de datos"); //tambien podría ya estar activo
        
        localDAO.eliminar(idLocal);
    }
    
    public Local obtenerPorId(int idLocal) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id del Local no es válido");
        Local loc = localDAO.obtener(idLocal);
        if(loc == null){
            System.out.println("Fallo aqui en Service");
        }
        return loc;
    }
    
    //Editar para ver los activos
    public List<Local> listarActivos() {
        return localDAO.listarTodos();
    }

    //listar local no tiene service ? listar los activos
    
//    public List<Local> listarActivos() {
//        return localDAO.listarTodos().stream()
//                  .filter(l -> l.getActivo() == true)
//                  .collect(Collectors.toList());
//    }
//    
//    public List<Local> listarInactivos() {
//        return localDAO.listarTodos().stream()
//                  .filter(l -> l.getActivo() == false)
//                  .collect(Collectors.toList());
//    }
//   
    //Asigancion en masa de productos/locales (futuro) 

    
    
    
}
