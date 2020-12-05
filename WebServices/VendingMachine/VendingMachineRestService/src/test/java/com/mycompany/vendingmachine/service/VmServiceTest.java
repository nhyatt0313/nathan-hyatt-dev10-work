/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.Id;
import com.mycompany.vendingmachine.TestApplicationConfiguration;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nhyat
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class VmServiceTest {
    

    @Autowired
    VmService testService;
    
    public VmServiceTest() {
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
     * Test of calculateChange method, of class VmServiceImpl.
     */
    @Test
    public void testCalculateChange() throws Exception {
        Change c = new Change(BigDecimal.ONE);
        int d = c.getDollars();
        assertEquals(1,d);
        c = new Change(BigDecimal.ONE);
        int q = c.getQuarters();
        assertEquals(4,q);
        c = new Change(BigDecimal.ONE);
        int di = c.getDimes();
        assertEquals(10,di);
        c = new Change(BigDecimal.ONE);
        int n = c.getNickels();
        assertEquals(20,n);
        c = new Change(BigDecimal.ONE);
        int p = c.getPennies();
        assertEquals(100,p);
    }

    /**
     * Test of validSelection method, of class VmServiceImpl.
     */
    @Test
    public void testValidSelection() throws Exception {
        List<Snack> allSnacks = testService.getAllSnacks();
        assertEquals(9, allSnacks.size());
        
    }

    /**
     * Test of getPrice method, of class VmServiceImpl.
     */
    @Test
    public void testGetPrice() throws Exception {
        Snack snack = testService.getSnackById(Id.A1);
        BigDecimal snackPrice = snack.getPrice();
        assertEquals(new BigDecimal("2.50"), snackPrice);
        
    }

    /**
     * Test of getAllSnacks method, of class VmServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllSnacks() throws Exception {
        List<Snack> snacks = testService.getAllSnacks();
        assertEquals(9,snacks.size());
        
    }

    /**
     * Test of buyItem method, of class VmServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    @Transactional
    public void testBuyGetItem() throws Exception {
        
//        Snack snack = testService.getSnackById(Id.A1);
//        int oldStock = snack.getNumInStock();
//        assertEquals(20, oldStock);
//        boolean success = testService.buyItem(Id.A1);
//        assertTrue(success);
//        Snack snack2 = testService.getSnackById(Id.A1);
//        assertEquals(snack, snack2);
//        int newStock = snack2.getNumInStock();
        
                
                
                
        Snack snack = testService.getSnackById(Id.A1);
        if (snack.getNumInStock() > 0){
            int before = snack.getNumInStock();
            boolean success = testService.buyItem(Id.A1);
            
            Snack snack2 = testService.getSnackById(Id.A1);
            int after = snack2.getNumInStock();
            assertEquals(after, before-1);
            assertTrue(success);
        } else {
            fail("Database ran out of snacks - "
                    + "Run VendingMachineDatabaseTestCreation.sql "
                    + "to reinitialize test database");
        }
    }

    /**
     * Test of checkSufficientFunds method, of class VmServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testCheckSufficientFunds() throws Exception {
        BigDecimal money = BigDecimal.ONE;
        BigDecimal price = BigDecimal.TEN;
        assertEquals(-1,money.compareTo(price));
        assertEquals(1,price.compareTo(money));
        assertEquals(0,money.compareTo(money));
        
    }
    
}
