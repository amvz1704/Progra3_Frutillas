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
import pe.edu.pucp.frutilla.models.inventario.Fruta;

/**
 *
 * @author Regina Sanchez
 */
public class FrutaServiceTest {
    
    public FrutaServiceTest() {
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
     * Test of agregar method, of class FrutaService.
     */
    @Test
    public void testCompleto() throws Exception {
        System.out.println("agregar");
        Fruta fru = new Fruta("Piña", "piña en trozos", "FRU", 4.5, 10, 3, 
                true, true, true, true, "taper de plastico");
        FrutaService instance = new FrutaService();
        instance.agregar(fru);
        fru.setRequiereLimpieza(false);
        fru.setEstaLimpio(false);
        instance.actualizar(fru);
        System.out.println("obtenerPorId");
        int idFruta = fru.getIdProducto();
        Fruta result = instance.obtenerPorId(idFruta);
        System.out.println(result);
        System.out.println("eliminar");
        instance.eliminar(idFruta);
    }


    /**
     * Test of listar method, of class FrutaService.
     */
    @Test
    public void testListar() throws Exception {
        System.out.println("listar");
        FrutaService instance = new FrutaService();
        List<Fruta> result = instance.listar();
        for(Fruta b : result){
            System.out.println(b);
        }
        System.out.println("");
    }

    /**
     * Test of listarPorLocal method, of class FrutaService.
     */
    @Test
    public void testListarPorLocal() throws Exception {
        System.out.println("listarPorLocal");
        int idLocal = 1;
        FrutaService instance = new FrutaService();
        ArrayList<Fruta> result = instance.listarPorLocal(idLocal);
        for(Fruta b : result){
            System.out.println(b);
        }
        System.out.println("");
    }
    
}
