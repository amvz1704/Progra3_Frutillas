package com.frutilla.crud.dao.venta;

import com.frutilla.models.venta.LineaOrdenDeVenta;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LineaOrdenVentaDAO {
    void insertarLineaVenta(LineaOrdenDeVenta lVenta, int idOrdenVenta, int idProducto) throws SQLException;
    void actualizarLineaOrdenVenta(LineaOrdenDeVenta lVenta) throws SQLException;
    ArrayList<LineaOrdenDeVenta> obtenerLineasPorOrden(int idOrdenVenta) throws SQLException;
}
