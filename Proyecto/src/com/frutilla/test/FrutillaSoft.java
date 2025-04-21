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
			
		/*B: Todo esto funciona correctamente uwu 
			LocalDAO segundoLocal = new LocalMySQL(); //instanciamos nuestro local
			//ahora creamos el objeto local 
			Local localA = new Local("Polideportivo","Cerca del polideportivo ", "CENTRUM PUCP, Av. Universitaria 1801, San Miguel 15088", "995-777-011");
			
			segundoLocal.crearLocal(localA);
			
			
			//obtener el id del local no necesario pero para asegurarnos que el incremnet esta bien
			int idLocal_Sociales = localA.getIdLocal(); //nota Nayane: solo funciona si las tablas en la BD tiene autoincrement 
			System.out.println(idLocal_Sociales);
			
		/*B: Todo esto funciona correctamente uwu */	
			
		/*A: Todo esto debajo funciona pero por x motivo se demora :'v --> 
			EmpleadoDAO primerEmpleado = new EmpleadoMySQL(); 
			EmpleadoDAO segundoEmpleado = new EmpleadoMySQL(); 
			
			//tenemos que crear un supervisor
			Supervisor supervisor1 = new Supervisor("Naruto", "Uzumaki", "Namikaze", "hokageDeLaHoja@gmail.com", "999999993", LocalDate.now() , 2000, "6toHokage", "sasukei<3u"); 
			
			//Creamos un repartidor
			Repartidor repartidor1 = new Repartidor("Itachi", "Uchiha", "Mamani", "uchihaKiller@gmail.com", "999999991", LocalDate.now(), 1066, "itachi", "contra1234");
			
			//Como dijo junior el idLocal lo asignamos al nuevo local creado owo  --Side note: Supongo que dependiendo del local en el que se trabaje esto automaticamente hace el get del local
			
			
			primerEmpleado.insertarEmpleado(repartidor1, localA.getIdLocal());
			segundoEmpleado.insertarEmpleado(supervisor1, localA.getIdLocal()); //esto es nuevo estamos agregando un supervisor al local de id 1 owo
		/*A: Todo esto debajo funcion --> */
		
			
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
}