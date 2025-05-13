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
import pe.edu.pucp.frutilla.models.inventario.Bebida;
import pe.edu.pucp.frutilla.models.inventario.FrutasBebida;
import pe.edu.pucp.frutilla.models.inventario.TipoLeche;

/**
 *
 * @author Regina Sanchez
 */
public class BebidaServiceTest {
    
    public BebidaServiceTest() {
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
     * Test of agregar method, of class BebidaService.
     */
    @Test
    public void testCompleto() throws Exception {
        System.out.println("agregar");
        ArrayList<FrutasBebida> frutasBebidas=new ArrayList<>();
        frutasBebidas.add(FrutasBebida.PERA);
        frutasBebidas.add(FrutasBebida.MANGO);
        frutasBebidas.add(FrutasBebida.PAPAYA);
        Bebida beb = new Bebida("Surtido", "Surtido de frutas de "
                + "temporada", "BEB", 1.5, 4, 3, 7, "jugo", "stevia", 
                TipoLeche.ENTERA, frutasBebidas);
        BebidaService instance = new BebidaService();
        instance.agregar(beb);
        System.out.println("actualizar");
        beb.setEndulzante("miel");
        instance.actualizar(beb);
        System.out.println("obtenerPorId");
        int idBebida = beb.getIdProducto();
        Bebida result = instance.obtenerPorId(idBebida);
        System.out.println(result);
        System.out.println("eliminar");
        instance.eliminar(idBebida);
    }

    
    /**
     * Test of listar method, of class BebidaService.
     */
    @Test
    public void testListar() throws Exception {
        System.out.println("listar");
        BebidaService instance = new BebidaService();
        List<Bebida> result = instance.listar();
        for(Bebida b : result){
            System.out.println(b);
        }
        System.out.println("");

    }

    /**
     * Test of listarPorLocal method, of class BebidaService.
     */
    @Test
    public void testListarPorLocal() throws Exception {
        System.out.println("listarPorLocal");
        int idLocal = 1;
        BebidaService instance = new BebidaService();
        ArrayList<Bebida> result = instance.listarPorLocal(idLocal);
        for(Bebida b : result){
            System.out.println(b);
        }
    }
    
}
