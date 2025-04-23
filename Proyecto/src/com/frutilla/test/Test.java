package com.frutilla.test; 

import com.frutilla.config.DBManager; //incluimos el DBManager que maneja todos nuestros CRUDS
import com.frutilla.models.rrhh.Cliente;
//importando todo lo referente a RRHH
import com.frutilla.models.rrhh.Empleado; 
import com.frutilla.models.rrhh.Supervisor;
import com.frutilla.models.venta.FormaDePago;
import com.frutilla.models.venta.LineaOrdenDeVenta;
import com.frutilla.models.venta.OrdenVenta;
import com.frutilla.models.rrhh.Repartidor; 
	//agregamos el dao y el mysql para RRHH
	import com.frutilla.crud.dao.rrhh.*;
import com.frutilla.crud.dao.venta.LineaOrdenVentaDAO;
import com.frutilla.crud.dao.venta.OrdenVentaDAO;
import com.frutilla.crud.mysql.rrhh.*;
import com.frutilla.crud.mysql.venta.ComprobantePagoMySQL;
import com.frutilla.crud.mysql.venta.LineaOrdenDeVentaMySQL;
import com.frutilla.crud.mysql.venta.OrdenVentaMySQL;
//importando todo lo referente a local 
import com.frutilla.models.local.Local; 
	//agregamos el dao y el mysql para local
	import com.frutilla.crud.dao.local.*;
	import com.frutilla.crud.mysql.local.*;

//importando todo lo referente a inventario 
import com.frutilla.models.inventario.*; 

	//agregamos el dao y el mysql para inventario
	import com.frutilla.crud.dao.inventario.*;
	import com.frutilla.crud.mysql.inventario.*;


//import de librerias comunes
import java.sql.Connection;
import java.util.ArrayList; 
import java.time.*;
import java.sql.SQLException; 

public class Test{
    public static void main(String[] args){
        try{
            ClienteDAO cliSQL = new ClienteMySQL();
            LocalDAO segundoLocal = new LocalMySQL();
            SnackDAO snackSQL = new SnackMySQL();
            Local localA = new Local("NuevoNuevo","CIA 9no piso", "CENTRO DE INNOVACION PUCP, Av. Universitaria 1801, San Miguel 15088", "995-111-011");
			Cliente cliente = new Cliente("AAAA", "BBBBB", "CCCC", "0000000", "A@adsadsad", "asdapsd", "paosdoasd");
			segundoLocal.insertarLocal(localA);
            cliSQL.insertarCliente(cliente);
            EmpleadoDAO primerEmpleado = new EmpleadoMySQL(); 
			
			//tenemos que crear un supervisor
			Supervisor supervisor1 = new Supervisor("Naruto", "Uzumaki", "Namikaze", "hokageDeLaHoja@gmail.com", "999999993", LocalDate.now() , 2000, "6toHokage", "sasukei<3u"); 
			Supervisor sup = new Supervisor("Nayane", "Melendez", "Saire", "nayane@gmail.com", "999999999", LocalDate.now() , 2000, "nayane", "1234");
			supervisor1.setActivo(true); 
			sup.setActivo(true); 
			
			//Creamos un repartidor
			Repartidor repartidor1 = new Repartidor("Itachi", "Uchiha", "Mamani", "uchihaKiller@gmail.com", "999999991", LocalDate.now(), 1066, "itachi", "contra1234");
			Repartidor repartidor2 = new Repartidor("Enzo", "Avila", "Mamani", "enzo@gmail.com", "999999999", LocalDate.now(), 1000, "enzo", "1234");
			
			repartidor1.setActivo(true); 
			repartidor2.setActivo(true); 
			
			//Como dijo junior el idLocal lo asignamos al nuevo local creado owo  --Side note: Supongo que dependiendo del local en el que se trabaje esto automaticamente hace el get del local
			 
			
			primerEmpleado.insertarEmpleado(repartidor1, 1);
			primerEmpleado.insertarEmpleado(sup, 1); //esto es nuevo estamos agregando un supervisor al local de id 1 owo
			primerEmpleado.insertarEmpleado(supervisor1, 1); 
			primerEmpleado.insertarEmpleado(repartidor2, 1);
            /*
            Cliente cliente = new Cliente("Pepito", "Sanchez", "Sanchez", "99999999", "a@gmail.com", "hacker", "antihacker");
            cliSQL.insertarCliente(cliente);
            */
            OrdenVentaMySQL ordenSQL = new OrdenVentaMySQL();
            ComprobantePagoMySQL comprobanteSQL = new ComprobantePagoMySQL();
            Snack snack1 = new Snack("Galleta", "De chocolate", "GAL", 3.4, 10, 0, "COMESTIBLES", "EMBOLSADO", false, true);
            Snack snack2 = new Snack("Batido", "De fresa", "BEB", 10.3, 10, 0, "NO SE", "AZUCAR", false, false);
            snackSQL.insertarSnack(snack1);
            snackSQL.insertarSnack(snack2);
            LineaOrdenDeVenta lin1 = new LineaOrdenDeVenta(1, snack1);
            LineaOrdenDeVenta lin2 = new LineaOrdenDeVenta(2, snack2);
            OrdenVenta orden = new OrdenVenta("Compra de comida");
            orden.agregarLineaOrden(lin1);
            orden.agregarLineaOrden(lin2);
            orden.crearComprobantePago(FormaDePago.TARJETA_CREDITO);
            comprobanteSQL.insertarComprobante(orden.getComprobantePago());
            ordenSQL.insertarOrdenVenta(orden, 1, 3, cliente.getIdCliente(), orden.getComprobantePago().getIdComprobante());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
}