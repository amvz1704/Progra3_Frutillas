
package com.frutilla.crud.dao.venta;

import com.frutilla.models.venta.ComprobantePago;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ComprobantePagoDAO {

    // Método para insertar un nuevo comprobante de pago
    void insertarComprobante(ComprobantePago comprobantePago) throws SQLException;

    // Método para modificar un comprobante de pago
    void actualizarComprobante(ComprobantePago comprobantePago) throws SQLException;

    // Método para obtener todos los comprobantes de pago
    ArrayList<ComprobantePago> obtenerTodos() throws SQLException;
    
    // Método para obtener un comprobante de pago por su ID
    ComprobantePago obtenerComprobantePorId(int idComprobante) throws SQLException;
}
