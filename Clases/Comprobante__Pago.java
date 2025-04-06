public class Comprobante_Pago {
    private int idComprobante;
    private int numeroArticulos;
    private double subtotal;
    private double montoIGV;
    private double total;
    private LocalDate fecha;
    
    
    public Comprobante_Pago(){
        this.fecha = LocalDate.now();
        this.idComprobante = 0;
        this.numeroArticulos = 0;
        this.subtotal = 0;
        this.montoIGV = 0;
        this.total  = 0;
       
                
    }
    
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
        return fecha;
    }

    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public void calcularTotal(){
        montoIGV = subtotal*0.18;
        total = subtotal + montoIGV;
    }
    public void calcularArticulos(){
        
    }
    
}
