/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sectionthreeunittests;

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
public class SayHiTest {
    
    private SayHi sayhi = new SayHi();
    
    public SayHiTest() {
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

    // sayHi("Bob") -> "Hello Bob!"
    // sayHi("Alice") -> "Hello Alice!"
    // sayHi("X") -> "Hello X!"
    
    
    /**
     * Test of sayHi method, of class SayHi.
     */
    @Test
    public void testBob() {
        String expected = "Hello Bob!";
        assertEquals(expected, sayhi.sayHi("Bob"));
    }
    
    @Test
    public void testAlice() {
        String expected = "Hello Alice!";
        assertEquals(expected, sayhi.sayHi("Alice"));
    }
    
    @Test
    public void testX() {
        String expected = "Hello X!";
        assertEquals(expected, sayhi.sayHi("X"));
    }
    
}
