import java.util.ArrayList;
import java.time.LocalDate;

public class OrdenPedido {
	private static int correlativo=1;
	private int idOrdenPedido;
	private LocalDate fecha;
	private String descripcion;
	private double subtotal;
	private double montoIGV;
	private double descuento;
	private double costoLogistico;
	private double total;
	private Proveedor proveedor;
	private Local local;
	private ArrayList<LineaOrdenPedido> detalle;
	
	public OrdenPedido(){
		this.idOrdenPedido=correlativo;
		this.fecha=LocalDate.now();
		this.subtotal=0;
		this.montoIGV=0;
		this.descuento=0;
		this.costoLogistico=0;
		this.total=0;
		this.detalle=new ArrayList<LineaOrdenPedido> ();
		correlativo++;
	}
	public void calcularSubtotal(){
		double suma=0;
		for(LineaOrdenPedido lin:detalle){
			suma+=lin.getSubtotal();
		}
		subtotal=suma;
		montoIGV=(subtotal*0.18);
	}
	public double getSubtotal(){
		return subtotal;
	}
	public double getMontoIGV(){
		return montoIGV;
	}
	public void setDescuento (double porcentajeDescuento){
		descuento=subtotal*porcentajeDescuento;
	}
	public double getDescuento(){
		return descuento;
	}
	public void setCostoLogistico (double costo){
		costoLogistico=costo;
	}
	public double getCostoLogistico(){
		return costoLogistico;
	}
	public void calcularTotal(){
		total=subtotal+montoIGV-descuento+costoLogistico;
	}
	public double getTotal(){
		return total;
	}
	public void setDescripcion(String descripcion){
		this.descripcion=descripcion;
	}
	public String getDescripcion (){
		return descripcion;
	}
	public LocalDate getFecha(){
		return fecha;
	//se haec asumiendo que va a haber un constructor copia
	public void setProveedor(Proveedor pro){
		proveedor=new Proveedor(pro);
	}
	public Proveedor getProveedor (){
		return new Proveedor(proveedor);
	}
	//se hace asumiendo que se va a realizar el constructor copia 
	public void setLocal (Local local){
		this.local=new Local(local);
	}
	public Local getLocal (){
		return new Local (local);
	}
}
