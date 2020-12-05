/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.Id;
import com.mycompany.vendingmachine.TestApplicationConfiguration;
import com.mycompany.vendingmachine.dto.Snack;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author nhyat
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class VmDaoDatabaseImplTest {
    
    @Autowired
    VmDao dao;
    
    public VmDaoDatabaseImplTest() {
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
     * Test of buySnack method, of class VmDaoDatabaseImpl.
     */
    @Test
    public void testGetBuySnack() throws Exception {
        Snack snack = dao.getSnackById(Id.A1);
        if (snack.getNumInStock() > 0){
            int before = snack.getNumInStock();
            dao.buySnack(snack);
            snack = dao.getSnackById(Id.A1);
            int after = snack.getNumInStock();
            assertEquals(after, before);
        } else {
            fail("Database ran out of snacks - "
                    + "Run VendingMachineDatabaseTestCreation.sql "
                    + "to reinitialize test database");
        }
    }

    /**
     * Test of getAllSnacks method, of class VmDaoDatabaseImpl.
     */
    @Test
    public void testGetAllSnacks() throws Exception {
        List<Snack> snacks = dao.getAllSnacks();
        assertEquals(9, snacks.size());
    }

    /**
     * Test of getSnackById method, of class VmDaoDatabaseImpl.
     */
    @Test
    public void testGetSnackById() throws Exception {
        // snack ('a1', 'Taco', 2.50, 20) is in test file
        Snack snack = dao.getSnackById(Id.A1);
        assertEquals(Id.A1, snack.getId());
    }
    
}
