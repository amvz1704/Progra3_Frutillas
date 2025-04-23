package com.frutilla.test; 

import com.frutilla.config.DBManager; //incluimos el DBManager que maneja todos nuestros CRUDS


//importando todo lo referente a RRHH
import com.frutilla.models.rrhh.Empleado; 
import com.frutilla.models.rrhh.Supervisor; 
import com.frutilla.models.rrhh.Repartidor; 
	//agregamos el dao y el mysql para RRHH
	import com.frutilla.crud.dao.rrhh.*;
	import com.frutilla.crud.mysql.rrhh.*;

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

public class FrutillaSoft{
    public static void main(String[] args){
		
		try{
			
		LocalDAO segundoLocal = new LocalMySQL(); //instanciamos nuestra interfaz de LOCAL para empezar a realizar las cosas 
		
		/*B: CRUD Local Todo esto funciona correctamente uwu 
			
			//ahora creamos el objeto local 
			Local localA = new Local("NuevoNuevo","CIA 9no piso", "CENTRO DE INNOVACION PUCP, Av. Universitaria 1801, San Miguel 15088", "995-111-011");
			
			segundoLocal.crearLocal(localA);
			
			Local localB = new Local("Sociales","Entre el polideportivo y generales ", "Estudios Generales Letras, Av. Universitaria 1801, San Miguel 15088", "999999999");
			
			segundoLocal.crearLocal(localB);
			
			Local localC = new Local("Polideportivo","Cerca de letras", "EEGGLL PUCP, Av. Universitaria 1801, San Miguel 15088", "995-777-011");
			
			segundoLocal.crearLocal(localC);
			
			
			//obtener el id del local no necesario pero para asegurarnos que el incremnet esta bien
			int idLocal_Sociales = localA.getIdLocal(); //nota Nayane: solo funciona si las tablas en la BD tiene autoincrement 
			System.out.println(idLocal_Sociales);
			
		/*B: Todo esto funciona correctamente uwu */	
		
		
		
		
		/*C: Vamos a probar obtener e imprimir un dato por id FUNCIONA!*/
		
			Local pruebaSelect = segundoLocal.obtenerLocalPorId(1); //Elegimos esto 
			//quiero ver el segundo ps cambiar si se requiere de otro (si elegimos esto entonces todo será insertado a este local específico)
			
			//como no hay un toString de local imprimo lo basico! 
			System.out.println("Local actual es: "+ pruebaSelect.getNombre() + " "+ pruebaSelect.getDescripcion() + " ubicado en " + pruebaSelect.getDireccion());
		
		/*C: Vamos a probar obtener e imprimir un dato por id*/
		
		
			
			
			
		
		/*D3: Probemos que imprime correctamente todos los locales ! SI LO HACE :3 */
		
		ArrayList<Local> locales = obtenerTodosLocales(); //obtenemos todos los locales 
		
		System.out.println("Lista de los locales: ");
		for(Local i: locales){
			System.out.println("Local actual es: "+ i.getNombre() + " "+ i.getDescripcion() + " ubicado en "+ i.getDireccion());
		} 
			
		
			
		/*A: CRUD Empleado Todo esto debajo funciona pero por x motivo se demora :'v supongo porque toma tiempo actualizar uwu--> */
		
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
			
			primerEmpleado.insertarEmpleado(repartidor1, pruebaSelect.getIdLocal());
			primerEmpleado.insertarEmpleado(sup, pruebaSelect.getIdLocal()); //esto es nuevo estamos agregando un supervisor al local de id 1 owo
			primerEmpleado.insertarEmpleado(supervisor1, pruebaSelect.getIdLocal()); 
			primerEmpleado.insertarEmpleado(repartidor2, pruebaSelect.getIdLocal()); 
			
		/*A: Todo esto debajo funcion --> */
		// Ya funciona eliminarLocal y actualizarLocal
		
		
		/*E: CRUD Inventario probando la insercion de los DAOs y MySQLs Codigo INVENTARIO PROBAR LUEGO! 
		
		
		
		
		Snack sna1=new Snack("galleta 1", "galleta de salvado", "GAL", 3.5, 
                10, 5, TipoEstado.DISPONIBLE, "galleta", "empaquetado propio", false, false);
        Snack sna2=new Snack("keke 1", "keke de platano", "KEK", 2.5, 
                11, 3, TipoEstado.DISPONIBLE, "keke", "empaquetado propio", false, false);
        
        SnackMySQL snaSQL=new SnackMySQL();
		
        snaSQL.insertar(sna1, pruebaSelect.getIdLocal());
        snaSQL.insertar(sna2, pruebaSelect.getIdLocal());
		
        sna1.setEstaEnvasado(true);
        snaSQL.actualizar(sna1, pruebaSelect.getIdLocal());
        Snack prueba=snaSQL.obtenerDatosSnack(sna1.getIdProducto(), pruebaSelect.getIdLocal());
        System.out.println("Producto de prueba: " + prueba);
		
        ArrayList<FrutasBebida> ejmeok=new ArrayList<>();
        Bebida beb1=new Bebida("jugo", "jugo de frutas", "JUG", 7.5, 4, 1, TipoEstado.DISPONIBLE, 7,
                "jugo", "stevia", TipoLeche.ENTERA, ejmeok);
        
		
        BebidaMySQL beb=new BebidaMySQL();
        beb.insertar(beb1, pruebaSelect.getIdLocal());
        beb1.setEndulzante("azucar_rubia");
        beb.actualizar(beb1, pruebaSelect.getIdLocal());
        beb.eliminar(11, pruebaSelect.getIdLocal());
		
        Fruta fru1=new Fruta("platano", "platano de la isla", "FRU", 2, 
                3, 1, TipoEstado.DISPONIBLE, true, true, true, true, "no tiene envase");
				
        fru1.setTipoEstado(TipoEstado.DISPONIBLE);
        FrutaMySQL fru=new FrutaMySQL();
        fru.insertar(fru1, pruebaSelect.getIdLocal());
		
		
		/*F: Generar reportes de productos y empleadoss de un local */
		
		//seleccionamos dos locales:
		Local pruebaLocal1 = segundoLocal.obtenerLocalPorId(1);
		Local pruebaLocal2 = segundoLocal.obtenerLocalPorId(3);
		
		
		ArrayList<Empleado> listaEmpleadosLocal = segundoLocal.encontrarEmpleados(pruebaLocal1.getIdLocal());  
		
		
		/*Aquí solo nos encargamos del formato de impresión dejamos el manejo del acceso*/
		System.out.println("Lista de empleados del local: " + pruebaLocal1.getNombre());
		
		
		for(Empleado e: listaEmpleadosLocal){
			System.out.println(e);
		}
		
		ArrayList<Producto> listaProductosLocal = segundoLocal.encontrarProductos(pruebaLocal1.getIdLocal());  
		
		
		/*Aquí solo nos encargamos del formato de impresión dejamos el manejo del acceso*/
		System.out.println("Lista de productos del local: " + pruebaLocal1.getNombre());
		
		System.out.println("Direccion del local: " + pruebaLocal1.getDireccion());
		
		for(Producto e: listaProductosLocal){
			System.out.println(e); //sí tiene toString :'v 
		}
		
		
		listaEmpleadosLocal = segundoLocal.encontrarEmpleados(pruebaLocal2.getIdLocal());  
		
		System.out.println("Lista de empleados del local: " + pruebaLocal2.getNombre());
		
		
		for(Empleado e: listaEmpleadosLocal){
			System.out.println(e);
		}
		
		
		
		
		
		
		listaProductosLocal = segundoLocal.encontrarProductos(pruebaLocal2.getIdLocal());  
		
		
		/*Aquí solo nos encargamos del formato de impresión dejamos el manejo del acceso*/
		System.out.println("Lista de productos del local: " + pruebaLocal2.getNombre());
		
		System.out.println("Direccion del local: " + pruebaLocal2.getDireccion());
		
		for(Producto e: listaProductosLocal){
			System.out.println(e); //sí tiene toString :'v 
		}
		
		/*G: CRUD de ventas*/
		
		/*H: Reporte de ventas* totales por local*/

		/*I: Reporte de ventas* por empleado en un local! */
		
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
}