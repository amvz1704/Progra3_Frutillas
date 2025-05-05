package pe.edu.pucp.frutilla.crud.dao.local;

import java.sql.SQLException;
import java.util.ArrayList;

import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.local.Local; //incluimos LOCAL 
import pe.edu.pucp.frutilla.models.rrhh.Empleado;
import pe.edu.pucp.frutilla.models.inventario.Producto;

public interface LocalDAO {
	
    void insertarLocal(Local local) throws SQLException; 
		
    Local obtenerLocalPorId(int idLocal) throws SQLException;
	
	//obtener una lista de locales de frutilla que estan activos
	
	ArrayList<Local> obtenerTodosLocales() throws SQLException;
	
//consultas especificas decido mantenerlo con encontrarEmpleados y Productos (esto es privado solo para local! )
	ArrayList<Empleado> encontrarEmpleados(int idLocal) throws SQLException; 
	ArrayList<Producto> encontrarProductos(int idLocal) throws SQLException; 
	
	
	//podriamos agregar a unas consultas cuando no esten activos o algo asi x
	
	//Implementar cuando esté terminado Ventas
//	ArrayList<OrdenVenta> obtenerOrdenes(int idLocal) throws SQLException; 
//	ArrayList<OrdenVenta> obtenerOrdenesPorFecha(int idLocal, LocalDate fecha) throws SQLException; 
	
	
	//UPDATEs
	
	void actualizarLocal(Local local) throws SQLException; //puedes actualizar varios datos a la vez --> por ejemplo el id de supervisor (si es un empleado tipo supervisor)
	
	//Otros UPDATES aun no implementados
		//activar un supervisor
		//agregar un empleado o supervisor
		//agregar un producto	
		//eliminar un empleado o supervisor 
		//eliminar un producto 
		
		
	
	void eliminarLocalPorId(int idLocal) throws SQLException;
	
	//falta agregar uwu 
}
