import java.time.*;

class Repartidor extends Empleado{

    public Repartidor(String nombre, String apellidoPaterno, String apellidoMaterno,
    String correoElectronico, String telefono, LocalDate fechaContrato,
    double salario, String usuarioSistema, String contraSistema){
        super(nombre, apellidoPaterno, apellidoMaterno, correoElectronico,
        telefono, fechaContrato, salario, usuarioSistema, contraSistema);
    }
    
    public void prepararPedido(OrdenVenta orden){
        //Settear Orden de venta como preparado
        orden.setEstado(estadoVenta.PROCESO);
    }

    public void confirmarEntregaCliente(OrdenVenta orden){
        orden.setEstado(estadoVenta.ENTREGADO);
        orden.setEntregado(true);
    }
}