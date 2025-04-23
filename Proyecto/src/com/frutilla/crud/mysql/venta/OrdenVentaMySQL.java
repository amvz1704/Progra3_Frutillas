/**
 *
 * @author  Gabriel Medrano
 * Consultas: a20200948@pucp.edu.pe
 * Telf: 989299092
 */

package com.frutilla.crud.mysql.venta;

import com.frutilla.models.venta.OrdenVenta;
import com.frutilla.models.venta.EstadoVenta;
import com.frutilla.models.venta.LineaOrdenDeVenta;
import com.frutilla.config.DBManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.time.*; 


public class OrdenVentaMySQL {
    
    //Metodo para insertar una nueva orden de Venta
    public void insertarOrdenVenta(OrdenVenta ordenVenta,int idLocal,int idEmpleado,
                int idCliente,int idComprobante) throws SQLException{
        String query="INSERT INTO OrdenVenta(descripcion,montoTotal,entregado,estadoVenta,idLocal,idEmpleado,idCliente,idComprobante,fecha,horaFinEntrega) values(?,?,?,?,?,?,?,?,?,?) ";
        try(Connection con = DBManager.getInstance().getConnection(); //conecta a la base de datos
                PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);){
            //inserta datos
            ps.setString(1,ordenVenta.getDescripcion());
            ps.setDouble(2,ordenVenta.getMontoTotal());
            ps.setBoolean(3,ordenVenta.getEntregado());
            ps.setString(4,ordenVenta.getEstado().name());
            ps.setInt(5,idLocal);
            ps.setInt(6,idEmpleado);
            ps.setInt(7,idCliente);
            ps.setInt(8,idComprobante);
            ps.setDate(9, Date.valueOf(ordenVenta.getFecha()));
            ps.setTime(10, Time.valueOf(ordenVenta.getHoraFinEntrega()));
            //ejecuta la insercion 
            ps.executeUpdate();
            
            //Traer el ultimo idOrdenVenta generado;
            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    ordenVenta.setIdOrdenVenta(rs.getInt(1));
                }
            }
            LineaOrdenDeVentaMySQL linSQL = new LineaOrdenDeVentaMySQL();
            for(LineaOrdenDeVenta lin: ordenVenta.getLineasOrdenes()){
                linSQL.insertarLineaVenta(lin, ordenVenta.getIdOrdenVenta(), lin.getProducto().getIdProducto());
            }
        }
    }
    
    //metodo para actualizar dentro de la tabla OrdenVenta
    public void actualizarOrdenVenta(OrdenVenta ordenVenta) throws SQLException {
        String query = "UPDATE OrdenVenta SET entregado = ?, estadoVenta = ? WHERE idOrdenVenta = ?";

        try (Connection con = DBManager.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            
            //En este caso se permite actualizar si ya se entrego, y cambiar el estado de la orden
            ps.setBoolean(1, ordenVenta.getEntregado());
            ps.setString(2, ordenVenta.getEstado().name()); 
            //Es necesario el id para saber que linea actualizar
            ps.setInt(3, ordenVenta.getIdOrdenVenta());

            ps.executeUpdate();
        }
    }
    
    public OrdenVenta obtenerOrdenPorId(int idOrdenVenta) throws SQLException{
        OrdenVenta orden = new OrdenVenta();
        String query = "SELECT idOrdenVenta, descripcion, montoTotal, entregado, estadoVenta "
                        + " FROM OrdenVenta WHERE idOrdenVenta = ?";
        try(Connection con = DBManager.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1,idOrdenVenta);
            try(ResultSet rs = ps.executeQuery() ){
                if(rs.next()){
                    orden.setIdOrdenVenta(rs.getInt("idOrdenVenta"));
                    orden.setDescripcion(rs.getString("descripcion"));
                    orden.setMontoTotal(rs.getDouble("montoTotal"));
                    orden.setEntregado(rs.getBoolean("entregado"));
                    orden.setEstado(EstadoVenta.valueOf(rs.getString("estadoVenta")));
                    
                }
            }
        }
        return orden;
    }
    
    //metodo para eliminar en OrdenVenta
    public void eliminarOrdenVenta(int idOrdenVenta) throws SQLException {
        //De manera logica, la ordenVenta pasa a ser ENTREGADO en estado venta.
        String query = "UPDATE OrdenVenta SET estadoVenta = ? WHERE idOrdenVenta = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            // Asignar el valor del estado CANCELADO 
            ps.setString(1, EstadoVenta.CANCELADO.name());  
            ps.setInt(2, idOrdenVenta);

            // Ejecuta la actualización
            ps.executeUpdate();
        }
    }
    
    //Metodo que permite obtener todas las ordenes en cada local, con el objetivo
    //de mostrar mejor la información.
    public ArrayList<OrdenVenta> obtenerTodos(int idLocal) throws SQLException {
        ArrayList<OrdenVenta> ordenesVentas = new ArrayList<>();//creamos la nueva lista
        
        String query = "SELECT idOrdenVenta, descripcion, montoTotal, entregado, estadoVenta "
                + " FROM OrdenVenta WHERE idLocal = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, idLocal);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrdenVenta orden = new OrdenVenta();
                orden.setIdOrdenVenta(rs.getInt("idOrdenVenta"));
                orden.setDescripcion(rs.getString("descripcion"));
                orden.setMontoTotal(rs.getDouble("montoTotal"));
                orden.setEntregado(rs.getBoolean("entregado"));
                orden.setEstado(EstadoVenta.valueOf(rs.getString("estadoVenta")));
                ordenesVentas.add(orden);
            }
        }

        return ordenesVentas;
    }
    
}
