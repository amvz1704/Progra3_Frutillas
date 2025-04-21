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
			
			LocalDAO primerLocal = new LocalMySQL(); //instanciamos nuestro local
			//ahora creamos el local 
			
			EmpleadoDAO primerEmpleado = new EmpleadoMySQL(); 
			EmpleadoDAO segundoEmpleado = new EmpleadoMySQL(); 
			
			//tenemos que crear un supervisor
			Supervisor supervisor1 = new Supervisor("Nayane", "Melendez", "Saire", "nayane@gmail.com", "999999999", LocalDate.now() , 2000, "nayane", "1234"); 
			
			//Creamos un repartidor
			Repartidor repartidor1 = new Repartidor("Enzo", "Avila", "Mamani", "enzo@gmail.com", "999999999", LocalDate.now(), 1000, "enzo", "1234");
			
			primerEmpleado.insertarEmpleado(repartidor1, 1);
			segundoEmpleado.insertarEmpleado(supervisor1, 1); //esto es nuevo estamos agregando un supervisor al local de id 1 owo
			
			
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
}