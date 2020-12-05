/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.TestApplicationConfiguration;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author nhyat
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDatabaseDaoImplTest {

    @Autowired
    JdbcTemplate j;

    @Autowired
    GameDao gameDao;

    public GameDatabaseDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        String sqlPath = "Sql/BullsAndCowsTestDBCreation.sql";
        String fileContent = "";
        Scanner scan;
        try {
            scan = new Scanner(new BufferedReader(new FileReader(sqlPath)));
            while (scan.hasNextLine()) {
                fileContent = fileContent +" "+ scan.nextLine();
            }
            
            String[] fileStatements = fileContent.split(";");
            for (String s : fileStatements){
                j.batchUpdate(s);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error thrown");
        }
        

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getGameById method, of class GameDatabaseDaoImpl.
     */
    @Test
    public void testGetGameById() {
    }

    /**
     * Test of getAllGames method, of class GameDatabaseDaoImpl.
     */
    @Test
    public void testGetAllGames() {
    }

    /**
     * Test of addGame method, of class GameDatabaseDaoImpl.
     */
    @Test
    public void testAddGame() {
    }

    /**
     * Test of finishGame method, of class GameDatabaseDaoImpl.
     */
    @Test
    public void testFinishGame() {
    }

}
