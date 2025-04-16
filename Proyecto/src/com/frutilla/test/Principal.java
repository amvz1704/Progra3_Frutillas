//agregar lo hecho en producto
import java.time.LocalDate;
import com.frutilla.models.Cliente.*;
import com.frutilla.models.Inventario.*;
import com.frutilla.models.Empleado.*;
import com.frutilla.models.Local.*;
import java.util.ArrayList;

public class Principal{

	public static void main(String[] args){
		//Creamos un local
		Local local1 = new Local("Sociales","Entre el polideportivo y generales ", "Estudios Generales Letras, Av. Universitaria 1801, San Miguel 15088", "999999999");

		System.out.println("Se creo un local");
		
		//Creamos un supervisor
		Supervisor sup = new Supervisor("Nayane", "Melendez", "Saire", "nayane@gmail.com", "999999999", LocalDate.now() , 2000, "nayane", "1234");
		System.out.println("Se creo un supervisor");


		//Agregamos el supervisor al local
		local1.agregarEmpleado(sup);
		System.out.println("Se agrego el supervisor al local");

		//Creamos un repartidor
		Repartidor repartidor1 = new Repartidor("Enzo", "Avila", "Mamani", "enzo@gmail.com", "999999999", LocalDate.now(), 1000, "enzo", "1234");
		System.out.println("Se creo un empleado repartidor");

		//Agregamos el empleado al local
		local1.agregarEmpleado(repartidor1);
		System.out.println("Se agrego el repartidor al local");

		//Agregamos productos al local
		//Creamos un producto
		Producto producto1 = new Producto(1, "Producto 1", "Descripcion del producto 1", "P001", 5.50, 150, 20);
		System.out.println("Se creo un producto");

		System.out.println(producto1.toString() + "\n");

		//Creamos una bebida
		ArrayList<FrutasBebida> frutasbebida1 = new ArrayList<FrutasBebida>();//Arraylist con las frutas que contiene la bebida
		frutasbebida1.add(FrutasBebida.FRUTA1);
		frutasbebida1.add(FrutasBebida.FRUTA2);
		Bebida bebida1 = new Bebida(1, "Bebida 1","Descripcion de la bebida 1", "B001", 7.0, 12, 5, 1, 12, "Tipo 1", "Endulzante 1", tipoLeche.ENTERA, frutasbebida1);
		
		System.out.println("Se creo una bebida");

		System.out.println(bebida1.toString());

		//Creamos un snack
		Snack snack1 = new Snack(1, "Snack 1", "Descripcion del snack 1", "S001", 3.0, 10, 5, 1, "Tipo 1", "Envase 1", true, true);

		System.out.println("Se creo un snack");

		System.out.println(snack1.toString());

		//Creamos una fruta
		Fruta fruta1 = new Fruta(1, "Fruta 1", "Descripcion de la fruta 1", "F001", 2.0, 25, 10, 1, true, true, false, false, "Envase 1");

		System.out.println("Se creo una fruta");

		System.out.println(fruta1.toString());

		//Creamos otra fruta
		Fruta fruta2 = new Fruta(2, "Fruta 2", "Descripcion de la fruta 2", "F002", 4.0, 20, 15, 2, true, true, true, true, "Envase 2");

		System.out.println("Se creo otra fruta");

		System.out.println(fruta2.toString());

		//Agregamos los productos al local
		local1.agregarProducto(producto1);
		local1.agregarBebida(bebida1);
		local1.agregarSnack(snack1);
		local1.agregarFruta(fruta1);

		//Reporte de productos

		local1.generarReporteProductos();

		//Creamos un cliente
		Cliente cliente1 = new Cliente("Junior", "Herrera", "Valverde", "99999999", "junior@gmail.com");
		System.out.println("Se creo un cliente");
		
		cliente1.realizarCompra();
		

		//Creamos orden de venta
		//OrdenVenta orden1 = new OrdenVenta("Primera orden de venta");

		//Agregamos lineas de orden a la orden de venta
		//orden1.agregarLineaOrden(new LineaOrdenDeVenta(1, 3, producto1));
		//orden1.agregarLineaOrden(new LineaOrdenDeVenta(2, 5, snack1));
		//orden1.agregarLineaOrden(new LineaOrdenDeVenta(3, 1, bebida1));

		//ComprobantePago primerComprobante = orden1.crearComprobantePago(); 

		//repartidor1.prepararPedido(orden1);

		//repartidor1.confirmarEntregaCliente(orden1, true);

		//Asignamos la orden de venta al local
		//local1.agregarOrdenVenta(orden1);

		//Reporte de empleados
		//local1.generarReporteEmpleados();
		
		//Reporte de ventas
		//local1.generarReporteVentas(LocalDate.now());
		


		

		//Version 2 de proceso de ventas
		//Lista de productos y cantidad que se desea comprar
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		ArrayList<Integer> listaCantidad = new ArrayList<>();
		//Se agregan los productos que se desean comprar y la cantidad
		boolean sePuedeComprar;
		listaProductos.add(producto1);
		listaCantidad.add(1);
		sePuedeComprar = local1.verificarStock(producto1, 1); //verifica el stock del producto
		listaProductos.add(snack1);
		listaCantidad.add(1);
		sePuedeComprar = local1.verificarStock(snack1, 1); //verifica el stock del snack
		listaProductos.add(bebida1);
		listaCantidad.add(1);
		sePuedeComprar = local1.verificarStock(bebida1, 1); //verifica el stock de la bebida
		listaProductos.add(fruta1);
		sePuedeComprar = local1.verificarStock(fruta1, 1); //verifica el stock de la fruta
		System.out.println("Se agregaron los productos a la lista de compra y se verifico su stock en el local");

		System.out.println("Solicitando compra de los productos");
		if(sePuedeComprar){
			//Cliente solicita compra
			boolean pago = cliente1.solicitarCompra(listaProductos, listaCantidad);
			if(pago){
				System.out.println("Compra realizada con exito");
				local1.actualizarStock(listaProductos, listaCantidad); //actualiza el stock de los productos
			}
		}
		else{
			System.out.println("No se puede realizar la compra, stock insuficiente");
		}
	}
}
