
//agregar lo hecho en producto
import java.time.*; 

public class Principal{

	public static void main(String[] args){
		
		//1. Creamos productos 
		
		// some code here in the main() method
		Producto nuevo = new Producto(); 
		
		System.out.println(nuevo.toString());
		
		Producto otro = new Producto(1, "agua", "cara", "0001111", LocalDate.of(2025, 1,2), LocalDate.of(2030, 1, 4), 5.44, 142, 100);
		
		System.out.println(otro.toString());
		
		
		//crear un snack 
		
		Snack a = new Snack(1, "cereal", "dulce", "0001112", LocalDate.of(2025, 1,2), LocalDate.of(2030, 5, 4), 1.44, 56, 50,
		1, "tipoIntegral","pequenio", true,true);
		
		System.out.println(a.toString());
		
		
		//crear una bebida pregunta cual es el stock de bebidas? 
		Bebida b = new Bebida(1, "batidoFresa", "yOtras", "0001123", LocalDate.of(2025, 1,2), LocalDate.of(2030, 5, 4), 10.2, 20, 50,
		2, 12, "batido", 
		"rubia", tipoLeche.ENTERA);
		
		System.out.println(b.toString());
		
		
		//crear unas 2 frutas
		Fruta platano = new Fruta(1, "batidoFresa", "yOtras", "0001123", LocalDate.of(2025, 1,2), LocalDate.of(2030, 5, 4), 10.2, 20, 50,
		5, true, true,false, false, "plastico"); 
		Fruta fresa = new Fruta(); 

		//agregamos frutas		
		
		b.agregarFruta(platano);
		b.agregarFruta(fresa); 
		
		System.out.println(b.toString());
		
		//crear un empleado 
		Repartidor frutiRepartidor = new Repartidor("Enzo", "Avila", "Mamani",
		"a20220123@pucp.edu.pe", "+51 984 321 122", LocalDate.now(),
		1040.5, "EnzitoEmoxito", "VIVAESPANA3"); 
		
		//crear una orden de venta 
		OrdenVenta primera = new OrdenVenta("Mi primera orden");   
		
		//crear 3 linea orden de venta y asociarlas a la orden de venta -- estamos creando propias instancias de producto no asociando los 
		//creados anterioremente
		primera.agregarLineaOrden(new LineaOrdenDeVenta(1, 3, otro));
		primera.agregarLineaOrden(new LineaOrdenDeVenta(2, 5, a));
		primera.agregarLineaOrden(new LineaOrdenDeVenta(3, 1, b));
		
		ComprobantePago primerComprobante = primera.crearComprobantePago(); 
		
		//probar que el Empleado/repartidor chambea y termina la orden de venta
		frutiRepartidor.prepararPedido(primera);
		
		frutiRepartidor.confirmarEntregaCliente(primera, false);
		
		
		//Le dejo a alguien mas terminar cliente, supervisor y generar reportes a ver si no hay error :v
		
		//crear un cliente 
		
		//crear un supervisor
		
		//imprimir reportes
		
	}
}
