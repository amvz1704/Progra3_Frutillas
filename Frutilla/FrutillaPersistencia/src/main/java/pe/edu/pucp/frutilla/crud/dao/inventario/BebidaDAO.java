/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.dao.inventario;

/**
 * 
 * @author Regina 
 */


import pe.edu.pucp.frutilla.models.inventario.Bebida;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BebidaDAO {
    void insertarBebida(Bebida bebida) throws SQLException;
    void actualizarBebida(Bebida bebida) throws SQLException;
    Bebida obtenerBebidaPorId (int idProducto) throws SQLException;
    void eliminarBebida (int idProducto,int idLocal) throws SQLException;
    ArrayList<Bebida> obtenerTodos(int idLocal) throws SQLException;
}
