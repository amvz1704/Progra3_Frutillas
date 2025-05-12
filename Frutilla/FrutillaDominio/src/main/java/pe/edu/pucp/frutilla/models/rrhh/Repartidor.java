package pe.edu.pucp.frutilla.models.rrhh;
import pe.edu.pucp.frutilla.models.venta.EstadoVenta;
import pe.edu.pucp.frutilla.models.venta.OrdenVenta;
import java.time.LocalDate;


public class Repartidor extends Empleado{

    public Repartidor(String nombre, String apellidoPaterno, String apellidoMaterno,
    String correoElectronico, String telefono, LocalDate fechaContrato,
    double salario, String usuarioSistema, String contraSistema){
        super(nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, fechaContrato, salario, usuarioSistema, contraSistema, 'R');
    }

	public Repartidor(){
		super();
	}

	//este es el constructor agregado
	public Repartidor(Repartidor rep){
		this.setNombre(rep.getNombre());
		this.setApellidoPaterno(rep.getApellidoPaterno());
		this.setApellidoMaterno(rep.getApellidoMaterno());
        this.setCorreoElectronico(rep.getCorreoElectronico());
        this.setTelefono(rep.getTelefono());
        this.setFechaContrato(rep.getFechaContrato());
        this.setSalario(rep.getSalario());
        this.setUsuarioSistema(rep.getUsuarioSistema());
        this.setContraSistema (rep.getContraSistema());
        this.setIdEmpleado(rep.getIdEmpleado());
		this.setTurnoTrabajo(rep.getTurnoTrabajo());
	}

	//cambie las funciones 
	public void prepararPedido(OrdenVenta orden){
		//inicia a preparar 
		orden.setEstado(EstadoVenta.PROCESO);
		orden.setIdEmpleado(getIdEmpleado());
	}
	
	public void confirmarEntregaCliente(OrdenVenta orden, boolean cambio){	
		orden.entregaExitosa(cambio); //esto actualiza la orden como ENTREGADO o CAMBIO
		orden.setEntregado(true); //en ambos casos es entregado pues :3 
	}
}