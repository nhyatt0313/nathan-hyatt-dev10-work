///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.vendingmachine.dao;
//
//import com.mycompany.vendingmachine.Id;
//import com.mycompany.vendingmachine.dto.Snack;
//import com.mycompany.vendingmachine.exception.VmPersistenceException;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.assertEquals;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
///**
// *
// * @author nhyat
// */
//public class VmDaoImplTest {
//    
//
//    VmDao dao = new VmDaoImpl();
//    
//    public VmDaoImplTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getSnack and buySnack method, of class VmDaoImpl.
//     * @throws com.mycompany.vendingmachine.exception.VmPersistenceException
//     */
//    @Test
//    public void testGetBuySnack() throws VmPersistenceException {
//        // get snack
//        Id id = Id.A1;
//        Snack snack = dao.getSnackById(id);
//        int numSnack = snack.getNumInStock();
//        //buy snack
//        dao.buySnack(snack);
//        snack = dao.getSnackById(id);
//        int numBoughtSnack = snack.getNumInStock();
//        
//        int numSnackMinusOne = numSnack - 1;
//        
//        assertEquals(numSnackMinusOne, numBoughtSnack);
//    }
//
//    /**
//     * Test of getAllSnacks method, of class VmDaoImpl.
//     */
//    @Test
//    public void testGetAllSnacks(){
//        // there are 9 snacks
//        int expectedSize = 9;
//        int size = dao.getAllSnacks().size();
//        
//        assertEquals(expectedSize, size);
//    }
//    
//}
