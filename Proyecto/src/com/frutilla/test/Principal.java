//agregar lo hecho en producto
import java.time.*; 
import com.frutilla.models.Venta.*;
import com.frutilla.models.Cliente.*;
import com.frutilla.models.Inventario.*;
import com.frutilla.models.Empleado.*;
import com.frutilla.models.Local.*;
import com.frutilla.models.Supervisor.*;

public class Principal{

	public static void main(String[] args){
		//Creamos un local
		Local local1 = new Local("Sociales","Entre el polideportivo y generales ", "Estudios Generales Letras, Av. Universitaria 1801, San Miguel 15088", "999999999");
		
		//Creamos un supervisor
		Supervisor supervisor = new Supervisor("Nayane", "Melendez", "No se su otro apellido", "nayane@gmail.com", "999999999", LocalDate.now(), 2000, "nayane", "1234");

		//Agregamos el supervisor al local
		local1.agregarEmpleado(supervisor);

		//Creamos un empleado
		Empleado empleado = new Empleado("Enzo", "Avila", "Mamani", "Enzo@gmail.com", "999999999", LocalDate.now(), 1000, "enzo", "1234");

		//Agregamos el empleado al local
		local1.agregarEmpleado(empleado);

		//Agregamos productos al local
		//Creamos un producto
		Producto producto1 = new Producto(1, "Producto 1", "Descripcion del producto 1", "P001", LocalDate.of(2025,1,2), LocalDate.of(2030.1,4), 5.50, 150, 20);

		system.out.println("Producto: " + producto1.toString());

		//Creamos una bebida
		Bebida bebida1 = new Bebida(1, "Bebida 1", "Descripcion de la bebida 1", "B001", LocalDate.of(2025,1,2), LocalDate.of(2025,5,4), 7.0, 12, 5, 1, 12, "Tipo 1", "Endulzante 1", tipoLeche.ENTERA);
		system.out.println("Bebida: " + bebida1.toString());

		//Creamos un snack
		Snack snack1 = new Snack(1, "Snack 1", "Descripcion del snack 1", "S001", LocalDate.of(2025,1,2), LocalDate.of(2025,5,4), 3.0, 10, 5, "Tipo 1", "Envase 1", true, true);

		system.out.println("Snack: " + snack1.toString());

		//Creamos una fruta
		Fruta fruta1 = new Fruta(1, "Fruta 1", "Descripcion de la fruta 1", "F001", LocalDate.of(2025,1,2), LocalDate.of(2025,5,4), 2.0, 25, 10, 1, true, true, false, false, "Envase 1");

		system.out.println("Fruta: " + fruta1.toString());

		//Creamos otra fruta
		Fruta fruta2 = new Fruta(2, "Fruta 2", "Descripcion de la fruta 2", "F002", LocalDate.of(2025,1,2), LocalDate.of(2025,5,4), 4.0, 20, 15, 2, true, true, true, true, "Envase 2");

		system.out.println("Fruta: " + fruta2.toString());

		//Agregamos frutas a bebida
		bebida1.agregarFruta(fruta1);
		bebida1.agregarFruta(fruta2);
		system.out.println("Bebida con frutas: " + bebida1.toString());

		//Creamos un cliente
		Cliente cliente1 = new Cliente("Junior", "Herrera", "Valverde", "99999999", "junior@gmail.com");
		clinete1.realizarCompra();

		//Cliente solicita compra
		cliente1.solicitarCompra(bebida1, 1);
		cliente1.solicitarCompra(snack1, 1);
		cliente1.solicitarCompra(producto1, 1);
		

	}
}
