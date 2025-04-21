
package com.frutilla.crud.dao.venta;

import com.frutilla.models.venta.ComprobantePago;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ComprobantePagoDAO {

    // Método para insertar un nuevo comprobante de pago
    public int insertar(ComprobantePago comprobantePago) throws SQLException;

    // Método para modificar un comprobante de pago
    public int modificar(ComprobantePago comprobantePago) throws SQLException;

    // Método para eliminar un comprobante de pago
    public int eliminar(int idComprobante) throws SQLException;

    // Método para obtener todos los comprobantes de pago
    public ArrayList<ComprobantePago> obtenerTodos() throws SQLException;
    
    // Método para obtener un comprobante de pago por su ID
    public ComprobantePago obtenerPorId(int idComprobante) throws SQLException;
}

