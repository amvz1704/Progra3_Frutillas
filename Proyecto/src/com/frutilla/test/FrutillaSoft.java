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
			LocalDAO primerLocal = new LocalMySQL(); //instanciamos nuestro local
			//ahora creamos el objeto local 
			Local localSociales = new Local("Sociales","Entre el polideportivo y generales ", "Estudios Generales Letras, Av. Universitaria 1801, San Miguel 15088", "999999999");
			
			primerLocal.crearLocal(localSociales);
			
			
			//obtener el id del local no necesario pero para asegurarnos que el incremnet esta bien
			int idLocal_Sociales = localSociales.getIdLocal(); //nota Nayane: solo funciona si las tablas en la BD tiene autoincrement 
			System.out.println(idLocal_Sociales);
		/**/	
			
		/*A: Todo esto debajo funciona pero por x motivo se demora :'v --> 
			EmpleadoDAO primerEmpleado = new EmpleadoMySQL(); 
			EmpleadoDAO segundoEmpleado = new EmpleadoMySQL(); 
			
			//tenemos que crear un supervisor
			Supervisor supervisor1 = new Supervisor("Nayane", "Melendez", "Saire", "nayane@gmail.com", "999999999", LocalDate.now() , 2000, "nayane", "1234"); 
			
			//Creamos un repartidor
			Repartidor repartidor1 = new Repartidor("Enzo", "Avila", "Mamani", "enzo@gmail.com", "999999999", LocalDate.now(), 1000, "enzo", "1234");
			
			//Como dijo junior el idLocal lo asignamos al nuevo local creado owo 
			primerEmpleado.insertarEmpleado(repartidor1, localSociales.getIdLocal());
			segundoEmpleado.insertarEmpleado(supervisor1, localSociales.getIdLocal(); ); //esto es nuevo estamos agregando un supervisor al local de id 1 owo
		/*A: Todo esto debajo funcion --> */
		
			
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
}