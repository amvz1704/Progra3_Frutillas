



package com.frutilla.test; 


import com.frutilla.config.DBManager; //incluimos el DBManager que maneja todos nuestros CRUDS

//agregamos el dao y el mysql 
import com.frutilla.crud.dao.rrhh.*;
import com.frutilla.crud.mysql.rrhh.*;

//importando empleado
import com.frutilla.models.rrhh.Empleado; 
import com.frutilla.models.rrhh.Supervisor; 
import com.frutilla.models.rrhh.Repartidor; 

import java.sql.Connection;
import java.util.ArrayList; 
import java.time.*;
import java.sql.SQLException; 

public class FrutillaSoft{
    public static void main(String[] args){
		
		try{
			Connection con = DBManager.getConnection(); // Ajusta a tu método real
			
			EmpleadoDAO primerEmpleado = new EmpleadoMySQL(); 
			
			//Creamos un repartidor
			Repartidor repartidor1 = new Repartidor("Enzo", "Avila", "Mamani", "enzo@gmail.com", "999999999", LocalDate.now(), 1000, "enzo", "1234");
			
			primerEmpleado.insertarEmpleado(repartidor1, 1);
			
			
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
}