/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

import pe.edu.pucp.frutilla.crud.dao.rrhh.EmpleadoDAO; //¿se puede agregar?
import pe.edu.pucp.frutilla.crud.mysql.rrhh.EmpleadoMySQL;

import pe.edu.pucp.frutilla.crud.dao.inventario.ProductoDAO; //¿se puede agregar?
import pe.edu.pucp.frutilla.crud.mysql.inventario.ProductoMySQL;
import pe.edu.pucp.frutilla.logica.inventario.InventarioService;

import pe.edu.pucp.frutilla.models.rrhh.Empleado;
import pe.edu.pucp.frutilla.models.inventario.Producto; 
import pe.edu.pucp.frutilla.models.venta.OrdenVenta; 

public class LocalService {
    private final LocalMySQL localDAO;
    
    public LocalService(){
        this.localDAO = new LocalMySQL(); 
    }
    
    
    public void agregar(Local loc)throws Exception {
        if(loc==null)
            throw new Exception("El local a ingresado nulo");
        if(loc.getNombre()==null||loc.getNombre().trim().isEmpty())
            throw new Exception("El nombre del local no puede ser vacio");
        //crear otras 
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
        
        EmpleadoDAO sup= new EmpleadoMySQL();
        if (sup.obtener( loc.getIdSupervisor()) == null)
           throw new Exception("No se ha registrado AUN un supervisor para el local de id " + loc.getIdLocal());
        
        
        localDAO.actualizar(loc);
    }
    
    public void eliminar(int idLocal) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id del Local no es válido");
        if (localDAO.obtener(idLocal) == null)
            throw new Exception("El id del local no se encuentra en la base de datos");
        
        localDAO.eliminar(idLocal);
    }
    
    public Local obtenerPorId(int idLocal) throws Exception{
        if(idLocal<=0)
            throw new Exception("El id del Local no es válido");
        Local loc = localDAO.obtener(idLocal);
        return loc;
    }
    
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
    
    
    
    //metodos extra de asignacion 
    public void asignarEmpleadoALocal(int idLocal, int idEmpleado) throws Exception{
        Local local = localDAO.obtener(idLocal);
        
        if(local == null)
            throw new Exception("El id del Local no es válido");
        
        EmpleadoDAO empleadoDAO = new EmpleadoMySQL();
        
        System.out.println("ID recibido en asignarEmpleadoALocal: " + idEmpleado);
        Empleado emp = empleadoDAO.obtener(idEmpleado);
        System.out.println("Empleado encontrado: " + (emp != null));
        

        
        if(emp == null)
            throw new Exception("El id del Empleado no es válido");
        
        local.agregarEmpleado(emp);       // lógica de dominio
        
        //localDAO.actualizar(local);        // llamada a persistencia para actualizar el local 
      }
    
    public void asignarSupervisorALocal(int idLocal, int idEmpleado) throws Exception{
        Local local = localDAO.obtener(idLocal);
        
        if(local == null)
            throw new Exception("El id del Local no es válido");
        
        EmpleadoDAO empleadoDAO = new EmpleadoMySQL();
        
        System.out.println("ID recibido en asignarSupervisorALocal: " + idEmpleado);
        Empleado emp = empleadoDAO.obtener(idEmpleado);
        System.out.println("Empleado encontrado: " + (emp != null));
        
        
        if(emp == null)
            throw new Exception("El id del Empleado no es válido, falta en la base de Datos");
        
        local.agregarEmpleado(emp);       // lógica de dominio
        local.setIdSupervisor(idEmpleado);
        System.out.println(local.getIdSupervisor());
        
        //localDAO.actualizar(local);        // llamada a persistencia para actulizar  
      }
    
    public void asignarProductoALocal(int idLocal, Producto producto) throws Exception{
        //Local local = localDAO.obtener(idLocal);
        InventarioService invS = new InventarioService();
        
        //obtener sus datos de producto tambien 
        //localDAO.ObtenerProductosPorLocal(idLocal, local); 
        
        if(idLocal <= 0)
            throw new Exception("El id del Local no es válido");
        
        //ProductoDAO productoDAO = new ProductoMySQL();
        
        invS.insertar(producto, idLocal);
        
//        Producto emp  = productoDAO.obtener(idProducto);
//        
//        if(emp == null)
//            throw new Exception("El id del Producto no es válido");
        
        
        //local.agregarProducto(producto);       // lógica de dominio
        
        //localDAO.actualizar(local);        // llamada a persistencia para actualizar el empleado 
      }
    
    
    
    
    //hacemos los metodos extra de Local
    
    public ArrayList<Empleado> encontrarEmpleados(int idLocal)throws Exception{
       
       //Buscar que el local exista en la base de datos
      Local loc = localDAO.obtener(idLocal);
      
      if(loc == null)
          throw new Exception("El id del Local no se encuentra en la base de datos");
       
      List<Empleado> empleados = localDAO.encontrarEmpleados(idLocal);
      
      if (empleados.isEmpty()) {
        // en este contexto significa que no tiene aun empleados
        throw new Exception("El Local no tiene empleados");
      }
      
       return localDAO.encontrarEmpleados(idLocal);
   }
    
     public ArrayList<Producto> encontrarProductos(int idLocal)throws Exception{
       
       //Buscar que el local exista en la base de datos
      Local loc = localDAO.obtener(idLocal);
      
      if(loc == null)
          throw new Exception("El id del Local no se encuentra en la base de datos");
      
      List<Producto> productos = localDAO.encontrarProductos(idLocal);
      
      if (productos.isEmpty()) {
        // en este contexto significa que no tiene aun empleados
        throw new Exception("El Local no tiene productos");
      }
       
       return localDAO.encontrarProductos(idLocal);
   }
     
      public ArrayList<OrdenVenta> encontrarOrdenVenta(int idLocal)throws Exception{
       
       //Buscar que el local exista en la base de datos
      Local loc = localDAO.obtener(idLocal);
      
      if(loc == null)
          throw new Exception("El id del Local no se encuentra en la base de datos");
      
      List<OrdenVenta> ventas = localDAO.encontrarVentas(idLocal);
      
      if (ventas.isEmpty()) {
        // en este contexto significa que no tiene aun empleados
        throw new Exception("El Local no tiene ventas pipipi");
      }
      
       return localDAO.encontrarVentas(idLocal);
   }
}
