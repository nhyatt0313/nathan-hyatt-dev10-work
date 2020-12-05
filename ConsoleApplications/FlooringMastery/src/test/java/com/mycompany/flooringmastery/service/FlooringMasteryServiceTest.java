/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.ProductType;
import com.mycompany.flooringmastery.TaxState;
import com.mycompany.flooringmastery.dao.FlooringMasteryDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryDaoStubImpl;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nathan
 */
public class FlooringMasteryServiceTest {

    LocalDate date = LocalDate.parse("02222222", DateTimeFormatter.ofPattern("MMddyyyy"));

    FlooringMasteryDao instance = new FlooringMasteryDaoStubImpl();
    
    private Order o;
    private Product p;
    private  Tax t;
   

    public FlooringMasteryServiceTest() {
        this.p = new Product();
        this.t = new Tax();
        this.o = new Order(p, t);
        
        // set values
        o.setOrderNumber(1);
        o.setCustName("Wise");
        t.setState(TaxState.valueOf("OH"));
        t.setTaxRate(BigDecimal.ONE);
        o.setTax(t);
        p.setProdType(ProductType.valueOf("WOOD"));
        o.setArea(new BigDecimal(100));
        p.setAreaCostSqft(new BigDecimal("5.15"));
        p.setLaborCostSqft(new BigDecimal("4.75"));
        o.setProduct(p);
        o.setMaterialCost(new BigDecimal("515.00"));
        o.setLaborCost(new BigDecimal("475.00"));
        o.setTotalTax(new BigDecimal("61.88"));
        o.setTotal(new BigDecimal("1051.88"));
    }

    @BeforeClass
    public static void setUpClass() {
         

        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class FlooringMasteryService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddGetOrder() throws Exception {
        Order order = new Order(p,t);
        order.setOrderNumber(100);
        Order added = instance.addOrder(date, order);
        assertEquals(100, added.getOrderNumber());

    }

    /**
     * Test of removeOrder method, of class FlooringMasteryService.
     */
    @Test
    public void testRemoveOrder() throws Exception {

        Order removed = instance.removeOrder(date, o);
        assertEquals(o.getOrderNumber(), removed.getOrderNumber());

    }


    /**
     * Test of getDelim method, of class FlooringMasteryService.
     */
    @Test
    public void testGetDelim() {
        System.out.println("getDelim");
        String expResult = "::";
        String result = instance.getDelim();
        assertEquals(expResult, result);

    }

    /**
     * Test of getProduct method, of class FlooringMasteryService.
     */
    @Test
    public void testGetProduct() throws Exception {
        Product result = instance.getProduct(o.getProduct().getProdType());
        assertNotNull(result);

    }

    /**
     * Test of getTax method, of class FlooringMasteryService.
     */
    @Test
    public void testGetTax() throws Exception {
        Tax result = instance.getTax(o.getTax().getState());
        assertNotNull(result);

    }

    /**
     * Test of getOrdersByDate method, of class FlooringMasteryService.
     */
    @Test
    public void testGetOrdersByDate() {
        ArrayList<Order> result = instance.getOrdersByDate(date);
        assertNotNull(result);

    }

    /**
     * Test of getOrder method, of class FlooringMasteryService.
     */
    @Test
    public void testGetOrder() {
        Order result = instance.getOrder(date, o.getOrderNumber());
        assertEquals(o.getOrderNumber(), result.getOrderNumber());

    }

    /**
     * Test of getMaxOrderNumber method, of class FlooringMasteryService.
     */
    @Test
    public void testGetMaxOrderNumber() {
        int max = instance.getMaxVal(date);
        assertEquals(1, max);
    }


}
