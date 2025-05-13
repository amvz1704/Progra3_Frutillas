package pe.edu.pucp.frutilla.logica;
/**
 *
 * @author NAMS_
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.frutilla.logica.local.LocalService; 
import pe.edu.pucp.frutilla.logica.rrhh.EmpleadoService; 
import pe.edu.pucp.frutilla.logica.inventario.ProductoService;


import pe.edu.pucp.frutilla.models.local.Local;

import pe.edu.pucp.frutilla.models.rrhh.Empleado;
import pe.edu.pucp.frutilla.models.rrhh.Supervisor;


import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.inventario.Fruta;
import pe.edu.pucp.frutilla.models.inventario.Snack;
import pe.edu.pucp.frutilla.models.inventario.Bebida;
import pe.edu.pucp.frutilla.models.inventario.FrutasBebida;
import pe.edu.pucp.frutilla.models.inventario.TipoLeche;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Principal {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) throws Exception {
        
            // TODO code application logic here
            LocalService probar = new LocalService();
            
            //Ingreso de datos
//            
//            //ingreso de un dato correcto primero Local, luego supervisor, luego Local con el supervisor (pienso que podria automatizarse)
//            
//            Local ingresar = new Local("Polideportivo", "Frutilla dentro de Cato", "Av. Universitaria", "xxx-xxx-xxx");
//            try {
//                probar.agregar(ingresar);
//            } catch (Exception ex) {
//                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            //Actualizar datos
//            EmpleadoService probarEmpleado = new EmpleadoService(); 
//            
//            Supervisor supFrutilla = new Supervisor("Luffy", "Monkey", "D.", "luffy@mail.com", "111",
//            LocalDate.now(), 2003.45, "nuevo2", "mikuoishi", ingresar.getIdLocal());
//            probarEmpleado.agregar(supFrutilla);
//            
//            //con un local de id Existente actualizamos el local para asignarlo el Supervsor
//            probar.asignarEmpleadoALocal(ingresar.getIdLocal(), supFrutilla.getIdEmpleado());
//            probar.asignarSupervisorALocal(ingresar.getIdLocal(), supFrutilla.getIdEmpleado());
//            
//            
//            //Ingreso de datos null
//            ingresar = null;
//            try {
//                probar.agregar(ingresar);
//            } catch (Exception ex) {
//                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            //Ingreso de datos de nombre no dado
//            ingresar = new Local(" ", "aa", "Av. Universitaria", "995777011");
//            try {
//                probar.agregar(ingresar);
//            } catch (Exception ex) {
//                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            
//            
//            //con un local no registrado en la base de datos
//            Local noingresa = new Local("Polideportivo", "aa", "Av. Universitaria", "995777011");
//            noingresa.setIdLocal(143); //seria bueno tener uno por nombre
//            
//            probar.actualizar(ingresar);
//            
//            
//            //con un local registrado pero que no tiene un supervisor con el id asignado 
//            Local otro = new Local("Polideportivo 2", "Otro local más", "Av. Universitaria", "995777011");
//            otro.setIdLocal(1);
//            
//            probar.actualizar(otro);
//            
//            //listar todos los activos 
//            List<Local> imprimir = probar.listarActivos();
//            for(Local x: imprimir){
//                System.out.println(x);
//            }
            
            //Ahora eliminar algunos locales que en realidad es hacerlo inactivos 
//            probar.eliminar(11);
//            probar.eliminar(1);
//            probar.eliminar(8);
//            probar.eliminar(9);
            
            //listar todos los activos 
            List<Local> segundo = probar.listarActivos();
            for(Local x: segundo){
                System.out.println(x.toString());
            }
            
//            //Creamos un supervisor
//		Supervisor sup = new Supervisor("Nayane", "Melendez", "Saire", "nayane@gmail.com", "999999999", LocalDate.now() , 2000, "capibara", "academica", 8);
//		System.out.println("Se creo un supervisor");
//                
//            //lo subimos a las base de datos
//            EmpleadoService probarEmpleadoIngreso = new EmpleadoService(); 
//            probarEmpleadoIngreso.agregar(sup);
//                
//            //lo asignamos al local que existe
//            probar.asignarSupervisorALocal(sup.getIdLocal(), sup.getIdEmpleado());
            
            
            //crear un producto (hacer tests)
            Producto producto1 = new Producto("Producto 1", "Descripcion del producto 1", "P01", 5.50, 150, 20);
            Snack a = new Snack("Snack 1", "Descripcion del snack 1", "S01", 3.0, 10, 5, "Tipo 1", "Envase 1", true, true);
            Snack snack2 = new Snack("Snack 2", "Descripcion del snack 2", "S02", 1.5, 1, 2, "Tipo 2", "Envase 2", true, true);
            Fruta fruta1 = new Fruta("Fruta 1", "Descripcion de la fruta 1", "F01", 2.0, 25, 10, true, true, false, false, "Envase 1");
            Fruta fruta2 = new Fruta("Fruta 2", "Descripcion de la fruta 2", "F02", 4.0, 20, 15, true, true, true, true, "Envase 2");
            ArrayList<FrutasBebida> frutasbebida1 = new ArrayList<FrutasBebida>();//Arraylist con las frutas que contiene la bebida
		frutasbebida1.add(FrutasBebida.MANGO);
		frutasbebida1.add(FrutasBebida.PAPAYA);
            Bebida bebida1 = new Bebida("Bebida 1","Descripcion de la bebida 1", "B01", 7.0, 12, 5, 12, "Tipo 1", "Endulzante 1", TipoLeche.ENTERA, frutasbebida1);
		
            
            //primero agregarlo a la base de datos 
           ProductoService probarProductosIngreso = new ProductoService(); 
            probarProductosIngreso.agregar(producto1);
            probarProductosIngreso.agregar(a);
            probarProductosIngreso.agregar(snack2);
            probarProductosIngreso.agregar(fruta1);
            probarProductosIngreso.agregar(fruta2);
            probarProductosIngreso.agregar(bebida1);
            
            
            //agregar un producto a un local 
            probar.asignarProductoALocal(1, producto1.getIdProducto());
            probar.asignarProductoALocal(1, a.getIdProducto());
            probar.asignarProductoALocal(1, snack2.getIdProducto());
            probar.asignarProductoALocal(1, fruta1.getIdProducto());
            probar.asignarProductoALocal(1, fruta2.getIdProducto());
            probar.asignarProductoALocal(1, bebida1.getIdProducto());
            
            //crear una orden de venta (hacer tests)
            
            
            //agregar una producto a un local 
            
         
      
        
        
    }
    
    
}
