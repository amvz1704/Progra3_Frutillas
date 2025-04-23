package com.frutilla.crud.dao.venta;

import com.frutilla.models.venta.OrdenVenta;
import java.sql.SQLException;
import java.util.ArrayList;

	public interface OrdenVentaDAO {
		void insertarOrdenVenta(OrdenVenta ordenVenta, int idLocal, int idEmpleado, int idCliente, int idComprobante) throws SQLException;
		void actualizarOrdenVenta(OrdenVenta ordenVenta) throws SQLException;
		OrdenVenta obtenerOrdenPorId(int idOrdenVenta) throws SQLException;
		void eliminarOrdenVenta(int idOrdenVenta) throws SQLException;
		ArrayList<OrdenVenta> listarOrdenesPorLocal(int idLocal) throws SQLException;
	}