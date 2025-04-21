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

//import de librerias comunes
import java.sql.Connection;
import java.util.ArrayList; 
import java.time.*;
import java.sql.SQLException; 

public class FrutillaSoft{
    public static void main(String[] args){
		
		try{
			
		LocalDAO segundoLocal = new LocalMySQL(); //instanciamos nuestro localSQL para empezar a realizar las cosas 
		
		/*B: Todo esto funciona correctamente uwu 
			
			//ahora creamos el objeto local 
			/*Local localA = new Local("NuevoNuevo","CIA 9no piso", "CENTRO DE INNOVACION PUCP, Av. Universitaria 1801, San Miguel 15088", "995-111-011");
			
			segundoLocal.crearLocal(localA);
			
			Local localB = new Local("Sociales","Entre el polideportivo y generales ", "Estudios Generales Letras, Av. Universitaria 1801, San Miguel 15088", "999999999");
			
			segundoLocal.crearLocal(localB);
			
			Local localC = new Local("Polideportivo","Cerca de letras", "EEGGLL PUCP, Av. Universitaria 1801, San Miguel 15088", "995-777-011");
			
			segundoLocal.crearLocal(localC);
			
			
			//obtener el id del local no necesario pero para asegurarnos que el incremnet esta bien
			int idLocal_Sociales = localA.getIdLocal(); //nota Nayane: solo funciona si las tablas en la BD tiene autoincrement 
			System.out.println(idLocal_Sociales);
			
		/*B: Todo esto funciona correctamente uwu */	
		
		
		/*C: Vamos a probar obtener e imprimir un dato por id FUNCIONA!
		
			Local imprimir = segundoLocal.obtenerLocalPorId(2); //quiero ver el segundo ps cambiar si se requiere de otro 
			//como no hay un toString de local imprimo lo basico! 
			System.out.println("Local actual es: "+ imprimir.getNombre() + " "+ imprimir.getDescripcion() + " ubicado en " + imprimir.getDireccion());
		
		/*C: Vamos a probar obtener e imprimir un dato por id*/
		
		/*D: Probemos que imprime correctamente todos los locales ! SI LO HACE :3
			ArrayList<Local> impresionesMasivasLocalesBD = segundoLocal.obtenerTodosLocales(); 
			
			System.out.println("Lista de los locales: ");
			for(Local i: impresionesMasivasLocalesBD){
				System.out.println("Local actual es: "+ i.getNombre() + " "+ i.getDescripcion() + " ubicado en "+ i.getDireccion());
			}
			
		
		/*D: Probemos que imprime correctamente todos los locales*/
		
			
		/*A: Todo esto debajo funciona pero por x motivo se demora :'v supongo porque toma tiempo actualizar uwu--> 
			EmpleadoDAO primerEmpleado = new EmpleadoMySQL(); 
			
			//tenemos que crear un supervisor
			Supervisor supervisor1 = new Supervisor("Naruto", "Uzumaki", "Namikaze", "hokageDeLaHoja@gmail.com", "999999993", LocalDate.now() , 2000, "6toHokage", "sasukei<3u"); 
			Supervisor sup = new Supervisor("Nayane", "Melendez", "Saire", "nayane@gmail.com", "999999999", LocalDate.now() , 2000, "nayane", "1234");
			
			//Creamos un repartidor
			Repartidor repartidor1 = new Repartidor("Itachi", "Uchiha", "Mamani", "uchihaKiller@gmail.com", "999999991", LocalDate.now(), 1066, "itachi", "contra1234");
			Repartidor repartidor2 = new Repartidor("Enzo", "Avila", "Mamani", "enzo@gmail.com", "999999999", LocalDate.now(), 1000, "enzo", "1234");
			
			//Como dijo junior el idLocal lo asignamos al nuevo local creado owo  --Side note: Supongo que dependiendo del local en el que se trabaje esto automaticamente hace el get del local
			
			Local localB =  segundoLocal.obtenerLocalPorId(3);  //nada ps 
			
			primerEmpleado.insertarEmpleado(repartidor1, imprimir.getIdLocal());
			primerEmpleado.insertarEmpleado(sup, imprimir.getIdLocal()); //esto es nuevo estamos agregando un supervisor al local de id 1 owo
			primerEmpleado.insertarEmpleado(supervisor1, localB.getIdLocal()); 
			primerEmpleado.insertarEmpleado(repartidor2, localB.getIdLocal()); 
			
		/*A: Todo esto debajo funcion --> */
		
			
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
}