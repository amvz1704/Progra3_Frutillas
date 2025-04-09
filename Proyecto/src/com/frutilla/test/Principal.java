//agregar lo hecho en producto
import java.time.*; 
import com.frutilla.models.Venta.*;
import com.frutilla.models.Cliente.*;
import com.frutilla.models.Inventario.*;
import com.frutilla.models.Empleado.*;
import com.frutilla.models.Local.*;

public class Principal{

	public static void main(String[] args){
		//Creamos un local
		Local local1 = new Local("Sociales","Entre el polideportivo y generales ", "Estudios Generales Letras, Av. Universitaria 1801, San Miguel 15088", "999999999");
		
		//Creamos un supervisor
		Supervisor sup = new Supervisor("Nayane", "Melendez", "Saire", "nayane@gmail.com", "999999999", LocalDate.now() , 2000, "nayane", "1234");

		//Agregamos el supervisor al local
		local1.agregarEmpleado(sup);

		//Creamos un repartidor
		Repartidor repartidor1 = new Repartidor("Enzo", "Avila", "Mamani", "enzo@gmail.com", "999999999", LocalDate.now(), 1000, "enzo", "1234");

		//Agregamos el empleado al local
		local1.agregarEmpleado(repartidor1);

		//Agregamos productos al local
		//Creamos un producto
		Producto producto1 = new Producto(1, "Producto 1", "Descripcion del producto 1", "P001", LocalDate.of(2025, 1,2), LocalDate.of(2030,1,4), 5.50, 150, 20);

		System.out.println(producto1.toString() + "\n");

		//Creamos una bebida
		Bebida bebida1 = new Bebida(1, "Bebida 1","Descripcion de la bebida 1", "B001", LocalDate.of(2025,1,2), LocalDate.of(2025,5,4), 7.0, 12, 5, 1, 12, "Tipo 1", "Endulzante 1", tipoLeche.ENTERA);
		System.out.println(bebida1.toString());

		//Creamos un snack
		Snack snack1 = new Snack(1, "Snack 1", "Descripcion del snack 1", "S001", LocalDate.of(2025,1,2), LocalDate.of(2025,5,4), 3.0, 10, 5, 1, "Tipo 1", "Envase 1", true, true);

		System.out.println(snack1.toString());

		//Creamos una fruta
		Fruta fruta1 = new Fruta(1, "Fruta 1", "Descripcion de la fruta 1", "F001", LocalDate.of(2025,1,2), LocalDate.of(2025,5,4), 2.0, 25, 10, 1, true, true, false, false, "Envase 1");

		System.out.println(fruta1.toString());

		//Creamos otra fruta
		Fruta fruta2 = new Fruta(2, "Fruta 2", "Descripcion de la fruta 2", "F002", LocalDate.of(2025,1,2), LocalDate.of(2025,5,4), 4.0, 20, 15, 2, true, true, true, true, "Envase 2");

		System.out.println(fruta2.toString());

		//Agregamos frutas a bebida
		bebida1.agregarFruta(fruta1);
		bebida1.agregarFruta(fruta2);
		System.out.println(bebida1.toString());

		//Creamos un cliente
		Cliente cliente1 = new Cliente("Junior", "Herrera", "Valverde", "99999999", "junior@gmail.com");
		cliente1.realizarCompra();

		//Creamos orden de venta
		OrdenVenta orden1 = new OrdenVenta("Primera orden de venta");

		//Agregamos lineas de orden a la orden de venta
		orden1.agregarLineaOrden(new LineaOrdenDeVenta(1, 3, producto1));
		orden1.agregarLineaOrden(new LineaOrdenDeVenta(2, 5, snack1));
		orden1.agregarLineaOrden(new LineaOrdenDeVenta(3, 1, bebida1));

		ComprobantePago primerComprobante = orden1.crearComprobantePago(); 

		repartidor1.prepararPedido(orden1);

		repartidor1.confirmarEntregaCliente(orden1, true);

		//Asignamos la orden de venta al local
		local1.agregarOrdenVenta(orden1);

		//Reporte de empleados
		local1.generarReporteEmpleados();
		
		//Reporte de ventas
		local1.generarReporteVentas(LocalDate.now());
		


		//Cliente solicita compra
		//cliente1.solicitarCompra(bebida1, 1);
		//cliente1.solicitarCompra(snack1, 1);
		//cliente1.solicitarCompra(producto1, 1);
		

	}
}
