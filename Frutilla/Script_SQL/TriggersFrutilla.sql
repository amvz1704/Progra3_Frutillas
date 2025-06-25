Drop trigger Notificacion_Inventario;
Drop trigger Notificacion_Prod_Agotado;
Drop trigger Notificacion_Entrega_Cliente;

DELIMITER $$
CREATE TRIGGER Notificacion_Inventario
AFTER UPDATE ON Inventario
FOR EACH ROW
BEGIN
	DECLARE stock_Min INT;
    DECLARE idSup_Local INT;
    
    SELECT idUsuario INTO idSup_Local FROM Empleado 
    WHERE New.idLocal = Empleado.idLocal AND Empleado.tipo='S';
    
    SELECT stockMinimo INTO stock_Min FROM
    Producto WHERE NEW.idProducto = Producto.idProducto;
    
    IF New.stock <= stock_Min AND New.stock != 0 THEN
		INSERT INTO Notificacion (tipoReceptor,fecha, hora,
        titulo,descripcion,idEmpleado) VALUES ('SUPERVISOR',
        curdate(),curtime(),"Bajo stock",
        CONCAT("El producto con id ",New.idProducto," esta con el stock por debajo del minimo"),
        idSup_Local);
    END IF;
END$$

DELIMITER $$
CREATE TRIGGER Notificacion_Prod_Agotado
AFTER UPDATE ON Inventario
FOR EACH ROW
BEGIN
	DECLARE idSup_Local INT;
    SELECT idUsuario INTO idSup_Local FROM Empleado 
    WHERE New.idLocal = Empleado.idLocal AND Empleado.tipo='S';
	IF New.stock = 0 THEN 
		INSERT INTO Notificacion (tipoReceptor,fecha, hora,
        titulo,descripcion,idEmpleado) VALUES 
        ('SUPERVISOR',curdate(),curtime(),"Sin stock",CONCAT("El producto con id ",New.idProducto," esta agotado"),idSup_Local);
    END IF;
END$$

DELIMITER $$
CREATE TRIGGER Notificacion_Entrega_Cliente
AFTER UPDATE ON OrdenVenta
FOR EACH ROW
BEGIN
	IF New.estadoVenta = 'POR_ENTREGAR' THEN
		INSERT INTO Notificacion (tipoReceptor,fecha, hora,
        titulo,descripcion,idCliente) VALUES 
        ('CLIENTE',curdate(),curtime(),"Pedido Listo",CONCAT("El pedido de id ",New.idOrdenVenta," esta listo para entregar"),New.idCliente);
    END IF;
END$$

