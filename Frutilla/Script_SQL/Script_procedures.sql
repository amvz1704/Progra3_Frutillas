DROP PROCEDURE IF EXISTS INSERTAR_ORDEN_VENTA;
DROP PROCEDURE IF EXISTS ACTUALIZAR_ORDEN_VENTA;
DROP PROCEDURE IF EXISTS ELIMINAR_ORDEN_VENTA;
DROP PROCEDURE IF EXISTS ORDEN_VENTA_LISTAR_X_ID;
DROP PROCEDURE IF EXISTS ORDEN_VENTA_LISTAR;
DROP PROCEDURE IF EXISTS ORDEN_VENTA_LISTAR_X_CLIENTE;
DROP PROCEDURE IF EXISTS ORDEN_VENTA_LISTAR_X_EMPLEADO;
DROP PROCEDURE IF EXISTS ORDEN_VENTA_LISTAR_X_LOCAL;
DROP PROCEDURE IF EXISTS INSERTAR_COMPROBANTE;
DROP PROCEDURE IF EXISTS ACTUALIZAR_COMPROBANTE;
DROP PROCEDURE IF EXISTS COMPROBANTE_LISTAR_X_ID;
DROP PROCEDURE IF EXISTS COMPROBANTE_LISTAR;
DROP PROCEDURE IF EXISTS COMPROBANTE_LISTAR_X_LOCAL;
DROP PROCEDURE IF EXISTS INSERTAR_LINEA_ORDEN_VENTA;
DROP PROCEDURE IF EXISTS ACTUALIZAR_LINEA_ORDEN_VENTA;
DROP PROCEDURE IF EXISTS ELIMINAR_LINEA_ORDEN_VENTA;
DROP PROCEDURE IF EXISTS LINEA_ORDEN_VENTA_LISTAR_X_ID;
DROP PROCEDURE IF EXISTS LINEA_ORDEN_VENTA_LISTAR_X_ORDEN;
DROP PROCEDURE IF EXISTS INSERTAR_Notificacion_CLIENTE;
DROP PROCEDURE IF EXISTS INSERTAR_Notificacion_SUPERVISOR;
DROP PROCEDURE IF EXISTS MODIFICAR_Notificacion;
DROP PROCEDURE IF EXISTS Notificacion_LISTAR_X_ID;
DROP PROCEDURE IF EXISTS Notificacion_LISTAR;
DROP PROCEDURE IF EXISTS Notificacion_LISTAR_X_CLIENTE;
DROP PROCEDURE IF EXISTS Notificacion_LISTAR_X_EMPLEADO;
DROP PROCEDURE IF EXISTS ORDEN_V_LISTAR_X_LOCALCLIENTE;


DELIMITER $

CREATE PROCEDURE INSERTAR_ORDEN_VENTA (
	OUT _idOrdenVenta INT,
    IN _fecha DATE,
	IN _horaFinEntrega TIME,
	IN _descripcion VARCHAR(45),
	IN _montoTotal DOUBLE,
	IN _entregado boolean,
	IN _estadoVenta ENUM("ENTREGADO", "PROCESO", "CAMBIO", "POR_ENTREGAR", "FALTA_PAGO","CANCELADO"),
	IN _idLocal INT,
	IN _idCliente INT,
	IN _idEmpleado INT
)
BEGIN
	INSERT INTO OrdenVenta (fecha,horaFinEntrega,descripcion,montoTotal,
    entregado,estadoVenta,idLocal,idCliente,idEmpleado) VALUES (_fecha,
    _horaFinEntrega,_descripcion,_montoTotal,_entregado,_estadoVenta,
    _idLocal,_idCliente,_idEmpleado);
    SET _idOrdenVenta = @@last_insert_id;
END$

CREATE PROCEDURE ACTUALIZAR_ORDEN_VENTA (
	IN _idOrdenVenta INT,
    IN _fecha DATE,
	IN _horaFinEntrega TIME,
	IN _descripcion VARCHAR(45),
	IN _montoTotal DOUBLE,
	IN _entregado boolean,
	IN _estadoVenta ENUM("ENTREGADO", "PROCESO", "CAMBIO", "POR_ENTREGAR", "FALTA_PAGO","CANCELADO"),
	IN _idLocal INT,
    IN _idComprobante INT,
	IN _idCliente INT,
	IN _idEmpleado INT
)
BEGIN
	UPDATE OrdenVenta SET fecha=_fecha,horaFinEntrega=_horaFinEntrega,descripcion=_descripcion,
    montoTotal=_montoTotal,entregado=_entregado,estadoVenta=_estadoVenta,
    idLocal=_idLocal,idComprobante=_idComprobante,idCliente=_idCliente,
    idEmpleado=_idEmpleado WHERE idOrdenVenta=_idOrdenVenta;
END $

CREATE PROCEDURE ELIMINAR_ORDEN_VENTA(
	IN _idOrdenVenta int
)
BEGIN 
	UPDATE OrdenVenta SET estadoVenta="CANCELADO",entregado=0 WHERE 
    idOrdenVenta=_idOrdenVenta;
END$

CREATE PROCEDURE ORDEN_VENTA_LISTAR_X_ID(
	IN _idOrdenVenta int
)
BEGIN 
	SELECT idOrdenVenta,fecha,horaFinEntrega,descripcion,montoTotal,
    estadoVenta,idLocal,idComprobante,idCliente,idEmpleado
    FROM OrdenVenta WHERE idOrdenVenta=_idOrdenVenta AND 
    NOT (estadoVenta="CANCELADO");  
END$

CREATE PROCEDURE ORDEN_VENTA_LISTAR ()
BEGIN
	SELECT idOrdenVenta,fecha,horaFinEntrega,descripcion,montoTotal,
    estadoVenta,idLocal,idComprobante,idCliente,idEmpleado
    FROM OrdenVenta WHERE NOT(estadoVenta="CANCELADO");  
END$

CREATE PROCEDURE ORDEN_VENTA_LISTAR_X_CLIENTE(
	IN _idCliente INT
)
BEGIN
	SELECT idOrdenVenta,fecha,horaFinEntrega,OrdenVenta.descripcion,montoTotal,
    estadoVenta,idComprobante,idCliente,idEmpleado,OrdenVenta.idLocal,L.nombre
    FROM OrdenVenta, Local as L WHERE OrdenVenta.idLocal=L.idLocal
    AND idCliente=_idCliente AND NOT (estadoVenta="CANCELADO") ORDER BY fecha DESC, horaFinEntrega DESC; 
END$

CREATE PROCEDURE ORDEN_VENTA_LISTAR_X_LOCAL(
	IN _idLocal INT
)
BEGIN 
	SELECT idOrdenVenta,fecha,horaFinEntrega,descripcion,montoTotal,
    estadoVenta,idComprobante,idCliente,idEmpleado,idLocal
    FROM OrdenVenta WHERE idLocal=_idLocal AND 
    NOT (estadoVenta="CANCELADO");  
END$

CREATE PROCEDURE ORDEN_V_LISTAR_X_LOCALCLIENTE(
	IN _idLocal INT,
    IN _idCliente INT
)
BEGIN 
	SELECT idOrdenVenta,fecha,horaFinEntrega,OrdenVenta.descripcion,montoTotal,
    estadoVenta,idComprobante,idCliente,idEmpleado,OrdenVenta.idLocal,L.nombre
    FROM OrdenVenta, Local as L WHERE OrdenVenta.idLocal=_idLocal AND OrdenVenta.idLocal=L.idLocal
    AND idCliente=_idCliente AND
    NOT (estadoVenta="CANCELADO") ORDER BY fecha DESC, horaFinEntrega DESC;  
END$


CREATE PROCEDURE ORDEN_VENTA_LISTAR_X_EMPLEADO(
	IN _idEmpleado INT
)
BEGIN 
	SELECT idOrdenVenta,fecha,horaFinEntrega,descripcion,montoTotal,
    estadoVenta,idLocal,idComprobante,idCliente
    FROM OrdenVenta WHERE idEmpleado=_idEmpleado AND 
    NOT (estadoVenta="CANCELADO");  
END$

CREATE PROCEDURE INSERTAR_COMPROBANTE(
	OUT _idComprobante INT,
    IN _numeroArticulos INT,
    IN _subtotal DOUBLE,
    IN _montoIGV DOUBLE,
    IN _total DOUBLE,
    IN _fecha DATE,
    IN _formaPago ENUM("TARJETA_CREDITO", "TARJETA_DEBITO", "PLIN", "YAPE")
)
BEGIN
	INSERT INTO ComprobantePago(numeroArticulos,subtotal,montoIGV,
    total,fecha,formaPago) VALUES (_numeroArticulos,_subtotal,
    _montoIGV,_total,_fecha,_formaPago);
    SET _idComprobante=@@last_insert_id;
END$

CREATE PROCEDURE ACTUALIZAR_COMPROBANTE(
	IN _idComprobante INT,
    IN _numeroArticulos INT,
    IN _subtotal DOUBLE,
    IN _montoIGV DOUBLE,
    IN _total DOUBLE,
    IN _fecha DATE,
    IN _formaPago ENUM("TARJETA_CREDITO", "TARJETA_DEBITO", "PLIN", "YAPE")
)
BEGIN
	UPDATE ComprobantePago SET numeroArticulos=_numeroArticulos,
    subtotal=_subtotal,montoIGV=_montoIGV,total=_total,fecha=_fecha,
    formaDePago=_formaPago WHERE idComprobante=_idComprobante;
END$

CREATE PROCEDURE COMPROBANTE_LISTAR_X_ID(
	IN _idComprobante INT
)
BEGIN
	SELECT idComprobante,numeroArticulos,subtotal,montoIGV,total,
    fecha,formaDePago FROM ComprobantePago WHERE 
    idComprobante=_idComprobante;
END$

CREATE PROCEDURE COMPROBANTE_LISTAR()
BEGIN
	SELECT idComprobante,numeroArticulos,subtotal,montoIGV,total,
    fecha,formaDePago FROM ComprobantePago;
END$

CREATE PROCEDURE COMPROBANTE_LISTAR_X_LOCAL(
	IN _idLocal INT
)
BEGIN
	SELECT c.idComprobante,numeroArticulos,subtotal,montoIGV,total,
    fecha,formaDePago FROM ComprobantePago c, OrdenVenta o
    WHERE o.idComprobante=c.idComprobante AND o.idLocal=_idLocal;
END$

CREATE PROCEDURE INSERTAR_LINEA_ORDEN_VENTA(
	IN _idOrdenVenta INT,
    IN _cantidad INT,
    IN _subtotal DOUBLE,
    IN _idProducto INT
)
BEGIN
	INSERT INTO LineaOrdenVenta (idOrdenVenta,cantidad,subtotal,idProducto) 
    VALUES (_idOrdenVenta,_cantidad,_subtotal,_idProducto);
END$

CREATE PROCEDURE ACTUALIZAR_LINEA_ORDEN_VENTA(
	IN _idLineaOrdenVenta INT,
	IN _idOrdenVenta INT,
    IN _cantidad INT,
    IN _subtotal DOUBLE,
    IN _idProducto INT
)
BEGIN
	UPDATE LineaOrdenVenta SET cantidad=_cantidad,subtotal=_subtotal,
    idProducto=_idProducto WHERE idOrdenVenta=_idOrdenVenta AND 
    idLineaOrdenVenta=_idLineaOrdenVenta;
END$

CREATE PROCEDURE ELIMINAR_LINEA_ORDEN_VENTA (
	IN _idLineaOrdenVenta INT,
	IN _idOrdenVenta INT
)
BEGIN
	DELETE FROM LineaOrdenVenta WHERE idOrdenVenta=_idOrdenVenta
    AND idLineaOrdenVenta=_idLineaOrdenVenta;
END$

CREATE PROCEDURE LINEA_ORDEN_VENTA_LISTAR_X_ID (
	IN _idLineaOrdenVenta INT,
	IN _idOrdenVenta INT
)
BEGIN
	SELECT idOrdenVenta,idLineaOrdenVenta,cantidad,subtotal,
    idProducto FROM LineaOrdenVenta WHERE 
    idOrdenVenta=_idOrdenVenta AND idLineaOrdenVenta=_idLineaOrdenVenta;
END$

CREATE PROCEDURE LINEA_ORDEN_VENTA_LISTAR_X_ORDEN(
	IN _idOrdenVenta INT
)
BEGIN
	SELECT idOrdenVenta,idLineaOrdenVenta,cantidad,subtotal,
    idProducto FROM LineaOrdenVenta WHERE 
    idOrdenVenta=_idOrdenVenta;
END$

CREATE PROCEDURE INSERTAR_Notificacion_CLIENTE(
	OUT _idNotificacion INT,
    IN _fechaHora DATETIME,
    IN _titulo VARCHAR(45),
    IN _descripcion VARCHAR(45),
    IN _idCliente INT
)
BEGIN
	INSERT INTO Notificacion (tipoReceptor,fechaHora,titulo,
    descripcion,idCliente) VALUES ('CLIENTE',_fechaHora,
    _titulo,_descripcion,_idCliente);
    SET _idNotificacion=@@last_insert_id;
END$

CREATE PROCEDURE INSERTAR_Notificacion_SUPERVISOR(
	OUT _idNotificacion INT,
    IN _fechaHora DATETIME,
    IN _titulo VARCHAR(45),
    IN _descripcion VARCHAR(45),
    IN _idEmpleado INT
)
BEGIN
	INSERT INTO Notificacion (tipoReceptor,fechaHora,titulo,
    descripcion,idCliente) VALUES ('SUPERVISOR',_fechaHora,
    _titulo,_descripcion,_idEmpleado);
    SET _idNotificacion=@@last_insert_id;
END$

CREATE PROCEDURE MODIFICAR_Notificacion(
	IN _idNotificacion INT,
    IN _tipoReceptor ENUM('CLIENTE', 'SUPERVISOR'),
    IN _fechaHora DATETIME,
    IN _titulo VARCHAR(45),
    IN _descripcion VARCHAR(45),
    IN _idCliente INT,
    IN _idEmpleado INT
)
BEGIN
	UPDATE Notificacion SET tipoReceptor=_tipoReceptor,
    fechaHora=_fechaHora,titulo=_titulo,descripcion=_descripcion,
    idCliente=_idCliente,idEmpleado=_idEmpleado WHERE
    idNotificacion=_idNotificacion;
END$

CREATE PROCEDURE Notificacion_LISTAR_X_ID(
	IN _idNotificacion INT
)
BEGIN
	SELECT idNotificacion,tipoReceptor,fechaHora,titulo,
    descripcion,idCliente,idEmpleado FROM Notificacion
    WHERE idNotificacion=_idNotificacion;
END$

CREATE PROCEDURE Notificacion_LISTAR()
BEGIN
	SELECT idNotificacion,tipoReceptor,fechaHora,titulo,
    descripcion,idCliente,idEmpleado FROM Notificacion;
END$

CREATE PROCEDURE Notificacion_LISTAR_X_CLIENTE (
	IN _idCliente INT
)
BEGIN
	SELECT idNotificacion,tipoReceptor,fechaHora,titulo,
    descripcion,idCliente,idEmpleado FROM Notificacion
    WHERE idCliente=_idCliente;
END$

CREATE PROCEDURE Notificacion_LISTAR_X_EMPLEADO (
	IN _idEmpleado INT
)
BEGIN
	SELECT idNotificacion,tipoReceptor,fechaHora,titulo,
    descripcion,idCliente,idEmpleado FROM Notificacion
    WHERE idEmpleado=_idEmpleado;
END$





