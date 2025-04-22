package com.frutilla.crud.dao.venta;

import com.frutilla.models.venta.LineaOrdenDeVenta;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LineaOrdenVentaDAO {
    int insertarLineaVenta(LineaOrdenDeVenta lVenta, int idOrdenVenta, int idProducto) throws SQLException;
<<<<<<< HEAD
    void actualizarLineaOrdenVenta(LineaOrdenDeVenta lVenta) throws SQLException;
=======
    int actualizarLineaOrdenVenta(LineaOrdenDeVenta lVenta) throws SQLException;
>>>>>>> origin/main
    ArrayList<LineaOrdenDeVenta> obtenerLineasPorOrden(int idOrdenVenta) throws SQLException;
}
