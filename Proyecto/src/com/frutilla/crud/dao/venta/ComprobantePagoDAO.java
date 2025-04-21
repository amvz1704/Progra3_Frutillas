
package com.frutilla.crud.dao.venta;

import com.frutilla.models.venta.ComprobantePago;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ComprobantePagoDAO {

    // Método para insertar un nuevo comprobante de pago
    int insertar(ComprobantePago comprobantePago) throws SQLException;

    // Método para modificar un comprobante de pago
    int modificar(ComprobantePago comprobantePago) throws SQLException;

    // Método para eliminar un comprobante de pago
    int eliminar(int idComprobante) throws SQLException;

    // Método para obtener todos los comprobantes de pago
    ArrayList<ComprobantePago> obtenerTodos() throws SQLException;
    
    // Método para obtener un comprobante de pago por su ID
    ComprobantePago obtenerPorId(int idComprobante) throws SQLException;
}

