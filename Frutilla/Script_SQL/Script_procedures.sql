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
DROP PROCEDURE IF EXISTS INSERTAR_NOTIFICACION_CLIENTE;
DROP PROCEDURE IF EXISTS INSERTAR_NOTIFICACION_SUPERVISOR;
DROP PROCEDURE IF EXISTS MODIFICAR_NOTIFICACION;
DROP PROCEDURE IF EXISTS NOTIFICACION_LISTAR_X_ID;
DROP PROCEDURE IF EXISTS NOTIFICACION_LISTAR;
DROP PROCEDURE IF EXISTS NOTIFICACION_LISTAR_X_CLIENTE;
DROP PROCEDURE IF EXISTS NOTIFICACION_LISTAR_X_EMPLEADO;


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
	INSERT INTO ordenventa (fecha,horaFinEntrega,descripcion,montoTotal,
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
	UPDATE ordenventa SET fecha=_fecha,horaFinEntrega=_horaFinEntrega,descripcion=_descripcion,
    montoTotal=_montoTotal,entregado=_entregado,estadoVenta=_estadoVenta,
    idLocal=_idLocal,idComprobante=_idComprobante,idCliente=_idCliente,
    idEmpleado=_idEmpleado WHERE idOrdenVenta=_idOrdenVenta;
END $

CREATE PROCEDURE ELIMINAR_ORDEN_VENTA(
	IN _idOrdenVenta int
)
BEGIN 
	UPDATE ordenventa SET estadoVenta="CANCELADO",entregado=0 WHERE 
    idOrdenVenta=_idOrdenVenta;
END$

CREATE PROCEDURE ORDEN_VENTA_LISTAR_X_ID(
	IN _idOrdenVenta int
)
BEGIN 
	SELECT idOrdenVenta,fecha,horaFinEntrega,descripcion,montoTotal,
    estadoVenta,idLocal,idComprobante,idCliente,idEmpleado
    FROM ordenventa WHERE idOrdenVenta=_idOrdenVenta AND 
    NOT (estadoVenta="CANCELADO");  
END$

CREATE PROCEDURE ORDEN_VENTA_LISTAR ()
BEGIN
	SELECT idOrdenVenta,fecha,horaFinEntrega,descripcion,montoTotal,
    estadoVenta,idLocal,idComprobante,idCliente,idEmpleado
    FROM ordenVenta WHERE NOT(estadoVenta="CANCELADO");  
END$

CREATE PROCEDURE ORDEN_VENTA_LISTAR_X_CLIENTE(
	IN _idCliente INT
)
BEGIN
	SELECT idOrdenVenta,fecha,horaFinEntrega,descripcion,montoTotal,
    estadoVenta,idLocal,idComprobante,idEmpleado
    FROM ordenventa WHERE idCliente=_idCliente AND 
    NOT (estadoVenta="CANCELADO");  
END$

CREATE PROCEDURE ORDEN_VENTA_LISTAR_X_LOCAL(
	IN _idLocal INT
)
BEGIN 
	SELECT idOrdenVenta,fecha,horaFinEntrega,descripcion,montoTotal,
    estadoVenta,idComprobante,idCliente,idEmpleado
    FROM ordenventa WHERE idLocal=_idLocal AND 
    NOT (estadoVenta="CANCELADO");  
END$

CREATE PROCEDURE ORDEN_VENTA_LISTAR_X_EMPLEADO(
	IN _idEmpleado INT
)
BEGIN 
	SELECT idOrdenVenta,fecha,horaFinEntrega,descripcion,montoTotal,
    estadoVenta,idLocal,idComprobante,idCliente
    FROM ordenventa WHERE idEmpleado=_idEmpleado AND 
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
	INSERT INTO comprobantepago(numeroArticulos,subtotal,montoIGV,
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
	UPDATE comprobantepago SET numeroArticulos=_numeroArticulos,
    subtotal=_subtotal,montoIGV=_montoIGV,total=_total,fecha=_fecha,
    formaDePago=_formaPago WHERE idComprobante=_idComprobante;
END$

CREATE PROCEDURE COMPROBANTE_LISTAR_X_ID(
	IN _idComprobante INT
)
BEGIN
	SELECT idComprobante,numeroArticulos,subtotal,montoIGV,total,
    fecha,formaDePago FROM comprobantepago WHERE 
    idComprobante=_idComprobante;
END$

CREATE PROCEDURE COMPROBANTE_LISTAR()
BEGIN
	SELECT idComprobante,numeroArticulos,subtotal,montoIGV,total,
    fecha,formaDePago FROM comprobantepago;
END$

CREATE PROCEDURE COMPROBANTE_LISTAR_X_LOCAL(
	IN _idLocal INT
)
BEGIN
	SELECT c.idComprobante,numeroArticulos,subtotal,montoIGV,total,
    fecha,formaDePago FROM comprobantepago c, ordenventa o
    WHERE o.idComprobante=c.idComprobante AND o.idLocal=_idLocal;
END$

CREATE PROCEDURE INSERTAR_LINEA_ORDEN_VENTA(
	IN _idLineaOrdenVenta INT,
	IN _idOrdenVenta INT,
    IN _cantidad INT,
    IN _subtotal DOUBLE,
    IN _idProducto INT
)
BEGIN
	INSERT INTO lineaordenventa (idLineaOrdenVenta,idOrdenVenta,cantidad,subtotal,idProducto) 
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
	UPDATE lineaordenventa SET cantidad=_cantidad,subtotal=_subtotal,
    idProducto=_idProducto WHERE idOrdenVenta=_idOrdenVenta AND 
    idLineaOrdenVenta=_idLineaOrdenVenta;
END$

CREATE PROCEDURE ELIMINAR_LINEA_ORDEN_VENTA (
	IN _idLineaOrdenVenta INT,
	IN _idOrdenVenta INT
)
BEGIN
	DELETE FROM lineaordenventa WHERE idOrdenVenta=_idOrdenVenta
    AND idLineaOrdenVenta=_idLineaOrdenVenta;
END$

CREATE PROCEDURE LINEA_ORDEN_VENTA_LISTAR_X_ID (
	IN _idLineaOrdenVenta INT,
	IN _idOrdenVenta INT
)
BEGIN
	SELECT idOrdenVenta,idLineaOrdenVenta,cantidad,subtotal,
    idProducto FROM lineaordenventa WHERE 
    idOrdenVenta=_idOrdenVenta AND idLineaOrdenVenta=_idLineaOrdenVenta;
END$

CREATE PROCEDURE LINEA_ORDEN_VENTA_LISTAR_X_ORDEN(
	IN _idOrdenVenta INT
)
BEGIN
	SELECT idOrdenVenta,idLineaOrdenVenta,cantidad,subtotal,
    idProducto FROM lineaordenventa WHERE 
    idOrdenVenta=_idOrdenVenta;
END$

CREATE PROCEDURE INSERTAR_NOTIFICACION_CLIENTE(
	OUT _idNotificacion INT,
    IN _fechaHora DATETIME,
    IN _titulo VARCHAR(45),
    IN _descripcion VARCHAR(45),
    IN _idCliente INT
)
BEGIN
	INSERT INTO notificacion (tipoReceptor,fechaHora,titulo,
    descripcion,idCliente) VALUES ('CLIENTE',_fechaHora,
    _titulo,_descripcion,_idCliente);
    SET _idNotificacion=@@last_insert_id;
END$

CREATE PROCEDURE INSERTAR_NOTIFICACION_SUPERVISOR(
	OUT _idNotificacion INT,
    IN _fechaHora DATETIME,
    IN _titulo VARCHAR(45),
    IN _descripcion VARCHAR(45),
    IN _idEmpleado INT
)
BEGIN
	INSERT INTO notificacion (tipoReceptor,fechaHora,titulo,
    descripcion,idCliente) VALUES ('SUPERVISOR',_fechaHora,
    _titulo,_descripcion,_idEmpleado);
    SET _idNotificacion=@@last_insert_id;
END$

CREATE PROCEDURE MODIFICAR_NOTIFICACION(
	IN _idNotificacion INT,
    IN _tipoReceptor ENUM('CLIENTE', 'SUPERVISOR'),
    IN _fechaHora DATETIME,
    IN _titulo VARCHAR(45),
    IN _descripcion VARCHAR(45),
    IN _idCliente INT,
    IN _idEmpleado INT
)
BEGIN
	UPDATE notificacion SET tipoReceptor=_tipoReceptor,
    fechaHora=_fechaHora,titulo=_titulo,descripcion=_descripcion,
    idCliente=_idCliente,idEmpleado=_idEmpleado WHERE
    idNotificacion=_idNotificacion;
END$

CREATE PROCEDURE NOTIFICACION_LISTAR_X_ID(
	IN _idNotificacion INT
)
BEGIN
	SELECT idNotificacion,tipoReceptor,fechaHora,titulo,
    descripcion,idCliente,idEmpleado FROM notificacion
    WHERE idNotificacion=_idNotificacion;
END$

CREATE PROCEDURE NOTIFICACION_LISTAR()
BEGIN
	SELECT idNotificacion,tipoReceptor,fechaHora,titulo,
    descripcion,idCliente,idEmpleado FROM notificacion;
END$

CREATE PROCEDURE NOTIFICACION_LISTAR_X_CLIENTE (
	IN _idCliente INT
)
BEGIN
	SELECT idNotificacion,tipoReceptor,fechaHora,titulo,
    descripcion,idCliente,idEmpleado FROM notificacion
    WHERE idCliente=_idCliente;
END$

CREATE PROCEDURE NOTIFICACION_LISTAR_X_EMPLEADO (
	IN _idEmpleado INT
)
BEGIN
	SELECT idNotificacion,tipoReceptor,fechaHora,titulo,
    descripcion,idCliente,idEmpleado FROM notificacion
    WHERE idEmpleado=_idEmpleado;
END$





