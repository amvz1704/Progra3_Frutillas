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
import pe.edu.pucp.frutilla.models.inventario.Snack;

/**
 *
 * @author Regina Sanchez
 */
public class SnackServiceTest {
    
    public SnackServiceTest() {
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
     * Test of agregar method, of class SnackService.
     */
    @Test
    public void testCompleto() throws Exception {
        System.out.println("agregar");
        Snack sna = new Snack("Triple A", "triple de jamon pollo y queso", 
                "SND", 5.5, 13, 10, "Sandwich", "envoltorio de papel", true, 
                true);
        SnackService instance = new SnackService();
        instance.agregar(sna);
        sna.setPrecioUnitario(3.5);
        instance.actualizar(sna);
        int idSnack = sna.getIdProducto();
        Snack result = instance.obtenerPorId(idSnack);
        System.out.println(result);
        System.out.println("eliminar");
        instance.eliminar(idSnack);
    }

    /**
     * Test of listar method, of class SnackService.
     */
    @Test
    public void testListar() throws Exception {
        System.out.println("listar");
        SnackService instance = new SnackService();
        List<Snack> result = instance.listar();
        for(Snack b : result){
            System.out.println(b);
        }
        System.out.println("");
    }

    /**
     * Test of listarPorLocal method, of class SnackService.
     */
    @Test
    public void testListarPorLocal() throws Exception {
        System.out.println("listarPorLocal");
        int idLocal = 1;
        SnackService instance = new SnackService();
        ArrayList<Snack> result = instance.listarPorLocal(idLocal);
        for(Snack b : result){
            System.out.println(b);
        }
        System.out.println("");
    }
    
}
