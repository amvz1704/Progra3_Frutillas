package com.frutilla.models.venta;

/*
	ENTREGADO: entrega exitos
	PROCESO: esta siendo preparado
	CAMBIO: el pedido al entregarse tuvo que ser cambiado 
	POR_ENTREGAR: ha sido reservado y pagado 
	FALTA_PAGO: aun falta confirmacion de comprobante de pago
*/

public enum EstadoVenta{
	ENTREGADO,PROCESO,CAMBIO,POR_ENTREGAR, FALTA_PAGO
}
