package pe.edu.pucp.frutilla.models.venta;

import java.time.*;

public class ComprobantePago {
    
    // ATRIBUTOS
    private int idComprobante;
    private int numeroArticulos;
    private double subtotal;
    private double montoIGV;
    private double total;
    private LocalDate fecha;
    private FormaDePago formaPago;
    private static int correlativo = 1;
    // CONSTRUCTORES
    
    public ComprobantePago(){
        this.fecha = LocalDate.now();
        this.idComprobante = correlativo;
        this.numeroArticulos = 0;
        this.subtotal = 0;
        this.montoIGV = 0;
        this.total  = 0;
		correlativo++;
    }
	
    public ComprobantePago(int numeroArticulos, double subtotal, double montoIGV, LocalDate fecha, int idOrdenVenta, FormaDePago formaPago) {
        this.idComprobante = correlativo;
        this.numeroArticulos = numeroArticulos;
        this.subtotal = subtotal; //se lo pasan ps
        this.montoIGV = montoIGV;
        calcularTotal();
        this.fecha = fecha;
        this.formaPago = formaPago;
		correlativo++;
    }
    //copia Comprobante Pago agregado Nyaane 
	
	public ComprobantePago(ComprobantePago original){
		this.idComprobante = original.idComprobante;
		this.numeroArticulos = original.numeroArticulos;
		this.subtotal = original.subtotal;
		this.montoIGV = original.montoIGV;
		this.total = original.total;
		this.fecha = original.fecha;
        this.formaPago = original.formaPago;
	}

    //GETTERS AND SETTERS
    
    public int getIdComprobante() {
        return idComprobante;
    }

    
    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    
    public int getNumeroArticulos() {
        return numeroArticulos;
    }

    
    public void setNumeroArticulos(int numeroArticulos) {
        this.numeroArticulos = numeroArticulos;
    }

    
    public double getSubtotal() {
        return subtotal;
    }

    
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    
    public double getMontoIGV() {
        return montoIGV;
    }

    
    public void setMontoIGV(double montoIGV) {
        this.montoIGV = montoIGV;
    }

    
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
		LocalDate copia = LocalDate.of(fecha.getYear(), fecha.getMonth(),
		fecha.getDayOfMonth());
        return copia;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public FormaDePago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaDePago formaPago) {
        this.formaPago = formaPago;
    }
    
    //METODOS
    
    public void calcularTotal(){
        //es suficiente con esto porque el totalse calcula en OrdenVenta al crear comprobante Pago
        montoIGV = subtotal*0.18;
        this.total = subtotal + montoIGV;
    }

    @Override
    public String toString() {
        return "Comprobante_Pago{" +
                "idComprobante= " + idComprobante +
                ", numeroArticulos=" + numeroArticulos +
                ", subtotal= s/" + subtotal +
                ", montoIGV= s/" + montoIGV +
                ", total= s/" + total +
                ", fecha= " + fecha +
                '}';
    }
    
    
    
    
}

