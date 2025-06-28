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
import pe.edu.pucp.frutilla.logica.inventario.InventarioService;

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
import java.util.HashSet;
import java.util.List;
import pe.edu.pucp.frutilla.logica.rrhh.UsuarioService;
import pe.edu.pucp.frutilla.models.rrhh.Cliente;
import pe.edu.pucp.frutilla.models.rrhh.Persona;

public class Principal {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) throws Exception {
        
//            // TODO code application logic here Probar insertar 3 locales con 3 supervisores 

//insertar de forma masiva productos 
//asegurar el funcionamiento de comprobante
            LocalService localS = new LocalService();
             
//            //Ingreso de datos
//            Local ingresar = new Local("E1", "Descripcipn", "Av. Universitaria", "xxx-xxx-xxx");
//            
//            
//            localS.agregar(ingresar);
//            localS.agregar(ingresar);
//            localS.agregar(ingresar);
////            
//            Local obtener = localS.obtenerPorId(2); //obtener todos los locales
//            System.out.println(obtener); 
            
            //Ingresar 3 empleados 
            
            //Ingresar clientes
            
            //Actualizar datos
//            EmpleadoService probarEmpleado = new EmpleadoService(); 
//            
//                    
//            Empleado supFrutilla = probarEmpleado.obtener(2);
//            
//            //agregar supervisor a cada local
//            
//            supFrutilla.setTipo('S');
//            supFrutilla.setIdUsuario(2);
//            
//            //agregar supervisor a cada local
//            probarEmpleado.actualizar(supFrutilla);
//            
//            supFrutilla = probarEmpleado.obtener(3);
//            
//            supFrutilla.setTipo('S');
//            supFrutilla.setIdUsuario(3);
//            
//            //agregar supervisor a cada local
//            probarEmpleado.actualizar(supFrutilla);
//            
//            probarEmpleado.agregar(supFrutilla);
//            
//            supFrutilla.setIdLocal(2);
//            supFrutilla.setUsuarioSistema("gato");
//            supFrutilla.setContraSistema("academico");
//            
//            probarEmpleado.agregar(supFrutilla);
//            
//            supFrutilla.setIdLocal(3);
//            supFrutilla.setUsuarioSistema("suricata");
//            supFrutilla.setContraSistema("academica");
//            
//            probarEmpleado.agregar(supFrutilla);
            
            
            //El problema es el local
            
        
            
            
            //Desde el front hacer pedidos --> revisar los requisitos 
            
            
            //ingreso de un dato correcto primero Local, luego supervisor, luego Local con el supervisor (pienso que podria automatizarse)
////            
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
//            LocalDate.now(), 2003.45, "nuevo1", "mikuoishi", ingresar.getIdLocal());
//            probarEmpleado.agregar(supFrutilla);
//            
//            //con un local de id Existente actualizamos el local para asignarlo el Supervsor
//            
//            probar.asignarSupervisorALocal(ingresar.getIdLocal(), supFrutilla.getIdEmpleado());
//            
////            
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

        
        
    }
    
    
}
