



package com.frutilla.test; 


import com.frutilla.config.DBManager; //incluimos el DBManager que maneja todos nuestros CRUDS


import java.sql.Connection;
import java.util.ArrayList; 

public class FrutillaSoft{
    public static void main(String[] args){
		Connection con = DBManager.getInstance().getConnection(); // Ajusta a tu método real
		
	}
	
}