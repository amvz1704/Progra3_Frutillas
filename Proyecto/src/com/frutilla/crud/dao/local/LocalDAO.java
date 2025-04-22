package com.frutilla.crud.dao.local;

import java.sql.SQLException;
import java.util.ArrayList;

import com.frutilla.models.local.Local; //incluimos LOCAL 

public interface LocalDAO {
	
    void insertarLocal(Local local) throws SQLException; 
		
    Local obtenerLocalPorId(int idLocal) throws SQLException;
	
	//obtener una lista de locales de frutilla desde una instancia 
	ArrayList<Local> obtenerTodosLocales() throws SQLException;
	
	//consultas especificas
//	ArrayList<Local> encontrarActivos() throws SQLException;
//	ArrayList<Empleado> encontrarEmpleados(int idLocal) throws SQLException; 
//	ArrayList<Producto> encontrarProductos(int idLocal) throws SQLException; 
	
	
	//Implementar cuando esté terminado Ventas
//	ArrayList<OrdenVenta> encontrarOrdenes(int idLocal) throws SQLException; 
//	ArrayList<OrdenVenta> encontrarOrdenesPorFecha(int idLocal, LocalDate fecha) throws SQLException; 
	
	
	//UPDATEs
	
//void actualizarLocal(Local local) throws SQLException; //puedes actualizar varios datos a la vez --> por ejemplo el id de supervisor (si es un empleado tipo supervisor)
    
	//asignar un supervisor
	//agregar un empleado o supervisor
	//agregar un producto	
	
	//eliminar un empleado o supervisor 
	//eliminar un producto 
	
	//generar reportes de:
	
//void eliminarLocalPorId(int idLocal) throws SQLException;
	
	//falta agregar uwu 
}
