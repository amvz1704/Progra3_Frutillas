/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.inventario;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.frutilla.models.inventario.Producto;

/**
 *
 * @author Regina Sanchez
 */
public class ProductoServiceTest {
    
    public ProductoServiceTest() {
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
     * Test of agregar method, of class ProductoService.
     */
    @Test
    public void testCompleto() throws Exception {
        System.out.println("agregar");
        Producto prod = new Producto("Cremolada", "Cremolada de fresa", "CRM", 
                6.0, 10, 5);
        ProductoService instance = new ProductoService();
        instance.agregar(prod);
        System.out.println("actualizar");
        prod.setStockMinimo(8);
        instance.actualizar(prod);
        System.out.println("obtenerPorId");
        int idProducto = prod.getIdProducto();
        Producto result = instance.obtenerPorId(idProducto);
        System.out.println(result);
        System.out.println("eliminar");
        instance.eliminar(idProducto);
    }

    

    /**
     * Test of listar method, of class ProductoService.
     */
    @Test
    public void testListar() throws Exception {
        System.out.println("listar");
        String nombre = "Jugo";
        ProductoService instance = new ProductoService();
        List<Producto> result = instance.listar(nombre);
        for(Producto b : result){
            System.out.println(b);
        }
    }

    /**
     * Test of listarPorLocal method, of class ProductoService.
     */
    @Test
    public void testListarPorLocal() throws Exception {
        System.out.println("listarPorLocal");
        int idLocal = 1;
        ProductoService instance = new ProductoService();
        ArrayList<Producto> result = instance.listarPorLocal(idLocal);
        for(Producto b : result){
            System.out.println(b);
        }
    }
    
}
