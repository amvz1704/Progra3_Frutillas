package com.frutilla.crud.dao.local;

import java.sql.SQLException;
import java.util.ArrayList;

import com.frutilla.models.local.Local; //incluimos LOCAL 

public interface LocalDAO {
    void crearLocal(Local local) throws SQLException; 
	
    Local obtenerLocalPorId(int idLocal) throws SQLException;
    void actualizarLocal(Local local) throws SQLException;
    void eliminarLocal(int idLocal) throws SQLException;
	
	//falta agregar uwu 
}
