package pe.edu.pucp.frutilla.models.venta;

import java.time.LocalDate;
import java.time.LocalTime; 
import java.util.ArrayList;
//solo usar set y gets con los realmente usados preguntarle a regina**

/**
 *
 * @author User
 */
public class OrdenVenta{
	private int idOrdenVenta; 
	private LocalDate fecha; 
        private String fechaStr;
	private LocalTime horaFinEntrega;
        private String horaStr;
	private String descripcion; 
	private double montoTotal; 
	private EstadoVenta estado; 
	private boolean entregado; 
	private int idEmpleado;
	private int idLocal;
	private int idComprobante;
	private int idCliente;


    @Override
    public String toString(){
            return "Id: " + String.valueOf(idOrdenVenta) + ", Estado: " + estado + ", Descripcion: " + descripcion + ", Monto: s/" + String.valueOf(montoTotal) + "\n";
    }

    public OrdenVenta(){
        this.entregado = false;
        this.estado = EstadoVenta.PROCESO;
    }

    public OrdenVenta(String descripcion){
            this.descripcion = descripcion; 
            this.estado = EstadoVenta.FALTA_PAGO; 
            this.fecha = LocalDate.now();
            this.horaFinEntrega = LocalTime.now(); //el dia pedido debe ser el de entrega
            //this.lineasOrdenes = new ArrayList<LineaOrdenDeVenta>();
            this.entregado = false; 
            this.montoTotal = 0; 
            this.idEmpleado = -1;
    }

    public OrdenVenta(String descripcion, ArrayList <LineaOrdenDeVenta> lista){
            this.descripcion = descripcion; 		
            this.fecha = LocalDate.now();//el dia pedido debe ser el de entrega
            this.idEmpleado = -1;
            this.estado = EstadoVenta.FALTA_PAGO; 	
            this.entregado = false; //se actualizara despues
    }

    public void entregaExitosa(boolean cambio){
            if(cambio) this.estado = EstadoVenta.CAMBIO;
            else this.estado = EstadoVenta.ENTREGADO;
    }

    public void setIdOrdenVenta(int idOrdenVenta){
            this.idOrdenVenta=idOrdenVenta;
    }
	
    public int getIdOrdenVenta(){
		return this.idOrdenVenta;
	}

    public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

    public int getIdEmpleado() {
		return this.idEmpleado;
	}
	
    public void setFecha(LocalDate fecha){
		this.fecha = fecha; 
	} 

    public LocalDate getFecha() {
		return this.fecha;
	}

    public void setHoraFinEntrega(LocalTime horaFinEntrega) {
		this.horaFinEntrega = horaFinEntrega;
	}

    public LocalTime getHoraFinEntrega() {
		return this.horaFinEntrega;
	}

    public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    public String getDescripcion() {
		return this.descripcion;
	}

    public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

    public double getMontoTotal() {
		return this.montoTotal;
	}

    public void setEstado(EstadoVenta estado) {
		this.estado = estado;
	}

    public EstadoVenta getEstado() {
		return this.estado;
	}

    public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
    
    public boolean getEntregado() {
		return this.entregado;
	} 

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaStr() {
        return fechaStr;
    }

    public void setFechaStr(String fechaStr) {
        this.fechaStr = fechaStr;
    }

    public String getHoraStr() {
        return horaStr;
    }

    public void setHoraStr(String horaStr) {
        this.horaStr = horaStr;
    }
    
    
	
}
