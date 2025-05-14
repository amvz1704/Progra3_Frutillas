package pe.edu.pucp.frutilla.logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.frutilla.logica.inventario.ProductoService;
import pe.edu.pucp.frutilla.logica.local.LocalService;
import pe.edu.pucp.frutilla.logica.rrhh.ClienteService;
import pe.edu.pucp.frutilla.logica.rrhh.EmpleadoService;
import pe.edu.pucp.frutilla.logica.venta.ComprobantePagoService;
import pe.edu.pucp.frutilla.logica.venta.LineaOrdenDeVentaService;
import pe.edu.pucp.frutilla.logica.venta.OrdenVentaService;
import pe.edu.pucp.frutilla.models.inventario.Bebida;
import pe.edu.pucp.frutilla.models.inventario.Fruta;
import pe.edu.pucp.frutilla.models.inventario.FrutasBebida;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.inventario.Snack;
import pe.edu.pucp.frutilla.models.inventario.TipoLeche;
import pe.edu.pucp.frutilla.models.local.Local;
import pe.edu.pucp.frutilla.models.rrhh.Cliente;
import pe.edu.pucp.frutilla.models.rrhh.Repartidor;
import pe.edu.pucp.frutilla.models.rrhh.Supervisor;
import pe.edu.pucp.frutilla.models.venta.LineaOrdenDeVenta;
import pe.edu.pucp.frutilla.models.venta.OrdenVenta;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class Principal2 {
     public static void main(String[] args) throws Exception {
        LocalService localS = new LocalService();
         
        EmpleadoService empleadoS = new EmpleadoService();
        
        ProductoService productoS = new ProductoService();
        
        ClienteService clienteS = new ClienteService();
        
        OrdenVentaService ordenS = new OrdenVentaService();
        
        LineaOrdenDeVentaService lineaS = new LineaOrdenDeVentaService();
         
        ComprobantePagoService comprobanteS = new ComprobantePagoService();
         
        Local local1 = new Local("Polideportivo", "Frutilla dentro de Cato", "Av. Universitaria", "xxx-xxx-xxx");
         
        localS.agregar(local1);
         
        Supervisor supervisor1 = new Supervisor("Luffy", "Monkey", "D.", "luffy@mail.com", "111",
            LocalDate.now(), 2003.45, "nuevo2", "mikuoishi", local1.getIdLocal());
         
        empleadoS.agregar(supervisor1);
         
        localS.asignarSupervisorALocal(local1.getIdLocal(), supervisor1.getIdEmpleado());
         
        Repartidor repartidor1 = new Repartidor("Juan","Pérez","Gómez","juan.perez@example.com","987654321",
                 LocalDate.now(),2500.00,"juanr","segura123",
            local1.getIdLocal());
         
        empleadoS.agregar(repartidor1);
        
        localS.asignarEmpleadoALocal(local1.getIdLocal(), repartidor1.getIdEmpleado());
         
        local1 = localS.obtenerPorId(local1.getIdLocal());
        System.out.println(local1.getIdSupervisor());
         //localS.actualizar(local1);
         
        Local local2 = new Local("Polideportivo 2", "Otro local más", "Av. Universitaria", "995777011");
        
        localS.agregar(local2);
        
        Supervisor supervisor2 = new Supervisor("Nayane", "Melendez", "Saire", "nayane@gmail.com",
                "999999999", LocalDate.now() , 2000, "capibara", "academica", local2.getIdLocal());
        
        empleadoS.agregar(supervisor2);
        
        localS.asignarSupervisorALocal(local2.getIdLocal(), supervisor2.getIdEmpleado());
         
        List<Local> segundo = localS.listarActivos();
        for(Local x: segundo){
            System.out.println(x.toString());
        }
        
        Producto producto1 = new Producto("Producto 1", "Descripcion del producto 1", "P01", 5.50, 150, 20);
        Snack snack1 = new Snack("Snack 1", "Descripcion del snack 1", "S01", 3.0, 10, 5, "Tipo 1", "Envase 1", true, true);
        Snack snack2 = new Snack("Snack 2", "Descripcion del snack 2", "S02", 1.5, 1, 2, "Tipo 2", "Envase 2", true, true);
        Fruta fruta1 = new Fruta("Fruta 1", "Descripcion de la fruta 1", "F01", 2.0, 25, 10, true, true, false, false, "Envase 1");
        Fruta fruta2 = new Fruta("Fruta 2", "Descripcion de la fruta 2", "F02", 4.0, 20, 15, true, true, true, true, "Envase 2");
        ArrayList<FrutasBebida> frutasbebida1 = new ArrayList<FrutasBebida>();//Arraylist con las frutas que contiene la bebida
            frutasbebida1.add(FrutasBebida.MANGO);
            frutasbebida1.add(FrutasBebida.PAPAYA);
        Bebida bebida1 = new Bebida("Bebida 1","Descripcion de la bebida 1", "B01", 7.0, 12, 5, 12, "Tipo 1", "Endulzante 1", TipoLeche.ENTERA, frutasbebida1);
        
        productoS.agregar(producto1);
        productoS.agregar(snack1);
        productoS.agregar(snack2);
        productoS.agregar(fruta1);
        productoS.agregar(fruta2);
        productoS.agregar(bebida1);
        
        localS.asignarProductoALocal(local1.getIdLocal(), producto1.getIdProducto());
        localS.asignarProductoALocal(local1.getIdLocal(), snack1.getIdProducto());
        localS.asignarProductoALocal(local1.getIdLocal(), snack2.getIdProducto());
        localS.asignarProductoALocal(local1.getIdLocal(), fruta1.getIdProducto());
        localS.asignarProductoALocal(local1.getIdLocal(), fruta2.getIdProducto());
        localS.asignarProductoALocal(local1.getIdLocal(), bebida1.getIdProducto());
        
        Cliente cliente1 = new Cliente("Junior", "Herrera", "Valverde", "99999999", "junior@gmail.com", "junior", "1234");
        
        clienteS.agregar(cliente1);
        
        OrdenVenta orden1 = new OrdenVenta("Compra de snacks");
        
        LineaOrdenDeVenta lin1 = new LineaOrdenDeVenta(1, snack1);
        LineaOrdenDeVenta lin2 = new LineaOrdenDeVenta(2, snack2);
        
        ArrayList<LineaOrdenDeVenta> lineas = new ArrayList<>();
        lineas.add(lin1);
        lineas.add(lin2);
        
        ordenS.registrarOrdenConLineas(orden1, lineas);
        //comprobanteSQL.insertarComprobante(orden.getComprobantePago());
        //ordenSQL.insertarOrdenVenta(orden, 1, 3, cliente.getIdCliente(), orden.getComprobantePago().getIdComprobante());
     }
    
}
