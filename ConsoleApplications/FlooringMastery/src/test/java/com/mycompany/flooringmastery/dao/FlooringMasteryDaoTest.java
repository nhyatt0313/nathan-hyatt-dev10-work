/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.ProductType;
import com.mycompany.flooringmastery.TaxState;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import com.mycompany.flooringmastery.exception.PersistenceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nhyat
 */
public class FlooringMasteryDaoTest {
    
    LocalDate date = LocalDate.parse("02222222", DateTimeFormatter.ofPattern("MMddyyyy"));
    
    FlooringMasteryDao instance = new FlooringMasteryDaoProdImpl();
    Product p = new Product();
    Tax t = new Tax();
    Order o = new Order(p,t);
    
    ArrayList<Product> pList;
    ArrayList<Tax> tList;
    
    // add list and map
    ArrayList<Order> oList = new ArrayList<>();
    HashMap<LocalDate, ArrayList<Order>> oMap;
    
    public FlooringMasteryDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        o.setOrderNumber(1);
        o.setCustName("Wise");
        t.setState(TaxState.valueOf("OH"));
        t.setTaxRate(new BigDecimal("6.25"));
        o.setTax(t);
        p.setProdType(ProductType.valueOf("WOOD"));
        o.setArea(new BigDecimal("100.00"));
        p.setAreaCostSqft(new BigDecimal("5.15"));
        p.setLaborCostSqft(new BigDecimal("4.75"));
        o.setProduct(p);
        o.setMaterialCost(new BigDecimal("515.00"));
        o.setLaborCost(new BigDecimal("475.00"));
        o.setTotalTax(new BigDecimal("61.88"));
        o.setTotal(new BigDecimal("1051.88"));
        
        oList.add(o);
        oMap = new HashMap<>();
        oMap.put(LocalDate.MIN, oList);
        
        pList = new ArrayList<>();
        tList = new ArrayList<>();
        
        pList.add(p);
        tList.add(t);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetOrder() {
        
        
        // then test that getting the only order works
        int id = 1;
        Order expResult = o;
            Order result = instance.getOrder(date, id);
            //assertEquals(expResult.getOrderNumber(), result.getOrderNumber());
 
        
    }

    /**
     * Test of getOrdersByDate method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetOrdersByDate() {

        // test correct entry
        int expResult = 1;
        try {
            instance.readFile(date);
            ArrayList<Order> result = instance.getOrdersByDate(date);
            assertEquals(expResult, result.size());
        } catch (PersistenceException ex) {
            fail("error in get order by date - PersistenceException");
        }
        
    }

    /**
     * Test of addOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testAddOrder() {
        
        
        // order does not alreaady exist
        Order order = new Order(p,t);
        order.setOrderNumber(1);
        Order expResult = order;
        try {
            instance.readFile(date);
            Order result = instance.addOrder(date, order);
            assertEquals(expResult, result);
            ArrayList<Order> orders = instance.getOrdersByDate(date);
            assertEquals(2, orders.size());
        } catch (PersistenceException ex) {
            fail("error in addOrder - PersistenceException thrown");
        }
        
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testRemoveOrder() {
        
        // order does exist
        Order order = o;
        Order expResult = o;
        Order result;
        try {
            instance.readFile(date);
            result = instance.removeOrder(date, order);
            assertEquals(expResult.getOrderNumber(), result.getOrderNumber());
        } catch (PersistenceException ex) {
            fail("error in removeOrder - PersistenceException thrown");
        }
        
        
        // order doesn't exist
        order = new Order(p,t);
        order.setOrderNumber(0);

            result = instance.removeOrder(date, order);
            //assertNull(result);

    }

    /**
     * Test of getProduct method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetProductsFromFile() {
        
        ArrayList<Product> result;
        try {
            result = instance.getProductsFromFile();
            assertEquals(4, result.size());
        } catch (PersistenceException ex) {
            fail("error in getProductsFromFile - PersistenceError thrown");
        }
        
    }

    /**
     * Test of getTax method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetTaxesFromFile() {
        
        try {
            ArrayList<Tax> result = instance.getTaxesFromFile();
            assertEquals(4, result.size());
        } catch (PersistenceException ex) {
            fail("error in testGetTaxesFromFile - PersistenceException thrown");
        }
        
    }
    
}
