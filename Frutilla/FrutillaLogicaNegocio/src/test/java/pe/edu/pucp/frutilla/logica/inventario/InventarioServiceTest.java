/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.inventario;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.frutilla.models.inventario.Fruta;
import pe.edu.pucp.frutilla.models.inventario.Producto;

/**
 *
 * @author Regina Sanchez
 */
public class InventarioServiceTest {
    
    public InventarioServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of insertar method, of class InventarioService.
     */
    @Test
    public void testInsertar() throws Exception {
        System.out.println("insertar");
        Producto prod = new Producto("Cremolada", "Cremolada de fresa", "CRM", 
                6.0, 10, 5);
        ProductoService prodServ = new ProductoService();
        prodServ.agregar(prod);
        int idLocal = 1;
        InventarioService instance = new InventarioService();
        instance.insertar(prod, idLocal);
        System.out.println("actualizar");
        prod.setStock(3);
        instance.actualizar(prod, idLocal);
        System.out.println("obtenerStockPorId");
        int idProducto = prod.getIdProducto();
        int result = instance.obtenerStockPorId(idProducto, idLocal);
        System.out.println("Stock actual: "+result);
        System.out.println("eliminar");
        instance.eliminar(idProducto, idLocal);
    }


    /**
     * Test of listar method, of class InventarioService.
     */
    @Test
    public void testListar() throws Exception {
        System.out.println("listar");
        int idLocal = 1;
        InventarioService instance = new InventarioService();
        ArrayList<Producto> result = instance.listar(idLocal);
        for(Producto b : result){
            System.out.println(b);
        }
    }
    
}
