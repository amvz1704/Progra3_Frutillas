/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.mysql.local;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.local.Local;
import pe.edu.pucp.frutilla.models.rrhh.Empleado;
import pe.edu.pucp.frutilla.models.venta.OrdenVenta;

import pe.edu.pucp.frutilla.config.DBManager; 

import pe.edu.pucp.frutilla.crud.dao.local.LocalDAO;
/**
 *
 * @author NAMS_
 */
public class LocalMySQLTest {
    private LocalDAO dao;
    public LocalMySQLTest() {
    }

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.junit.Before
    public void setUp() throws Exception {
        
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getInsertQuery method, of class LocalMySQL.
     */
    @org.junit.Test
    public void testGetInsertQuery() {
        System.out.println("getInsertQuery");
        LocalMySQL instance = new LocalMySQL();
        String expResult = "";
        String result = instance.getInsertQuery();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUpdateQuery method, of class LocalMySQL.
     */
    @org.junit.Test
    public void testGetUpdateQuery() {
        System.out.println("getUpdateQuery");
        LocalMySQL instance = new LocalMySQL();
        String expResult = "";
        String result = instance.getUpdateQuery();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDeleteQuery method, of class LocalMySQL.
     */
    @org.junit.Test
    public void testGetDeleteQuery() {
        System.out.println("getDeleteQuery");
        LocalMySQL instance = new LocalMySQL();
        String expResult = "";
        String result = instance.getDeleteQuery();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectByIdQuery method, of class LocalMySQL.
     */
    @org.junit.Test
    public void testGetSelectByIdQuery() {
        System.out.println("getSelectByIdQuery");
        LocalMySQL instance = new LocalMySQL();
        String expResult = "";
        String result = instance.getSelectByIdQuery();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectAllQuery method, of class LocalMySQL.
     */
    @org.junit.Test
    public void testGetSelectAllQuery() {
        System.out.println("getSelectAllQuery");
        LocalMySQL instance = new LocalMySQL();
        String expResult = "";
        String result = instance.getSelectAllQuery();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInsertParameters method, of class LocalMySQL.
     */
    @org.junit.Test
    public void testSetInsertParameters() throws Exception {
        System.out.println("setInsertParameters");
        PreparedStatement ps = null;
        Local entity = null;
        LocalMySQL instance = new LocalMySQL();
        instance.setInsertParameters(ps, entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFromResultSet method, of class LocalMySQL.
     */
    @org.junit.Test
    public void testCreateFromResultSet() throws Exception {
        System.out.println("createFromResultSet");
        ResultSet rs = null;
        LocalMySQL instance = new LocalMySQL();
        Local expResult = null;
        Local result = instance.createFromResultSet(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUpdateParameters method, of class LocalMySQL.
     */
    @org.junit.Test
    public void testSetUpdateParameters() throws Exception {
        System.out.println("setUpdateParameters");
        PreparedStatement ps = null;
        Local entity = null;
        LocalMySQL instance = new LocalMySQL();
        instance.setUpdateParameters(ps, entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class LocalMySQL.
     */
    @org.junit.Test
    public void testSetId() {
        System.out.println("setId");
        Local entity = null;
        Integer id = null;
        LocalMySQL instance = new LocalMySQL();
        instance.setId(entity, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encontrarEmpleados method, of class LocalMySQL.
     */
    @org.junit.Test
    public void testEncontrarEmpleados() throws Exception {
        System.out.println("encontrarEmpleados");
        int idLocal = 0;
        LocalMySQL instance = new LocalMySQL();
        ArrayList<Empleado> expResult = null;
        ArrayList<Empleado> result = instance.encontrarEmpleados(idLocal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encontrarProductos method, of class LocalMySQL.
     */
    @org.junit.Test
    public void testEncontrarProductos() throws Exception {
        System.out.println("encontrarProductos");
        int idLocal = 0;
        LocalMySQL instance = new LocalMySQL();
        ArrayList<Producto> expResult = null;
        ArrayList<Producto> result = instance.encontrarProductos(idLocal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encontrarVentas method, of class LocalMySQL.
     */
    @org.junit.Test
    public void testEncontrarVentas() throws Exception {
        System.out.println("encontrarVentas");
        int idLocal = 0;
        LocalMySQL instance = new LocalMySQL();
        ArrayList<OrdenVenta> expResult = null;
        ArrayList<OrdenVenta> result = instance.encontrarVentas(idLocal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
