package pe.edu.pucp.frutilla.models.rrhh;
import pe.edu.pucp.frutilla.models.venta.EstadoVenta;
import pe.edu.pucp.frutilla.models.venta.OrdenVenta;
import java.time.LocalDate;
import java.util.Date; 

public class Repartidor extends Empleado{

    public Repartidor(String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String telefono, Date fechaContrato, double salario, String usuarioSistema, String contraSistema, int idLocal, int idUsuario){
        super(nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, fechaContrato, salario, usuarioSistema, contraSistema, 'R', idLocal, idUsuario);
    }

	public Repartidor(){
		super();
	}

	//este es el constructor agregado
	public Repartidor(Repartidor rep){
		super(rep);
	}

	//cambie las funciones 
	public void prepararPedido(OrdenVenta orden){
		//inicia a preparar 
		orden.setEstado(EstadoVenta.PROCESO);
		orden.setIdEmpleado(getIdUsuario());
	}
	
	public void confirmarEntregaCliente(OrdenVenta orden, boolean cambio){	
		orden.entregaExitosa(cambio); //esto actualiza la orden como ENTREGADO o CAMBIO
		orden.setEntregado(true); //en ambos casos es entregado pues :3 
	}
}