public class LineaOrdenPedido {
	private int idLineaVenta;
	private int cantidad;
	private char tipo;//P: si es producto o U: si es utileria o N: no asignado
	private double subtotal;
	private Producto producto;
	private Utileria utileria;
	
	public LineaOrdenPedido (){
		this.idLineaVenta=-1;
		this.cantidad=0;
		this.tipo='N';
	}
	public LineaOrdenPedido(LineaOrdenPedido lin){
		this.idLineaVenta=lin.getId;
		this.cantidad=lin.getCantidad;
		this.tipo=lin.getTipo;
		this.producto=lin.getProducto();
		this.utileria=lin.getUtileria();
	}
	public void setIdLinea(int idLineaVenta){
		this.idLineaVenta=idLineaVenta;
	}
	public int getIdLinea(){
		return idLineaVenta;
	}
	public void setCantidad(int cantidad){
		this.cantidad=cantidad;
	}
	public int getCantidad(){
		return cantidad;
	}
	public void setTipo(char tipo){
		this.tipo=tipo;
	}
	public char getTipo(){
		return tipo;
	}
	public void calcularSubtotal(){
		if (tipo=='P')
			subtotal=cantidad*producto.getPrecioUnitario();
		else 
			subtotal=cantidad*utileria.getCosto();
	}
	public double getSubtotal(){
		return subtotal;
	}
	public void setProducto(Producto pro){
		if (pro instanceof Bebida){
			producto=new Bebida (pro);
		}
		else if (pro instanceof Snack){
			producto=new Snack (pro);
		}
		else if (pro instanceof Fruta){
			producto=new Fruta (pro);
		}
		else{
			producto=new Producto (pro);
		}
	}
	public Producto getProducto (){
		if (producto instanceof Bebida){
			return new Bebida (pro);
		}
		else if (producto instanceof Snack){
			return new Snack (pro);
		}
		else if (producto instanceof Fruta){
			return new Fruta (pro);
		}
		else{
			return new Producto (pro);
		}
	}
	//de verdad no hago lo de utileria pq siento que de verdad esta miedo raro y sobrecomplicado
	
}