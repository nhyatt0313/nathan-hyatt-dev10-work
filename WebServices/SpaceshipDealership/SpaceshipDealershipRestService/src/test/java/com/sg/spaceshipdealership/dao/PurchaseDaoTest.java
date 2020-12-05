/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import com.sg.spaceshipdealership.dao.MakeDao;
import com.sg.spaceshipdealership.dao.ModelDao;
import com.sg.spaceshipdealership.dao.PurchaseDao;
import com.sg.spaceshipdealership.dao.UserDao;
import com.sg.spaceshipdealership.dao.VehicleDao;
import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.dto.Model;
import com.sg.spaceshipdealership.dto.Purchase;
import com.sg.spaceshipdealership.dto.User;
import com.sg.spaceshipdealership.dto.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nhyat
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class PurchaseDaoTest {
    
    @Autowired
    PurchaseDao purchaseDao;
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    MakeDao makeDao;
    
    @Autowired
    ModelDao modelDao;
    
    @Autowired
    VehicleDao vehicleDao;
    
    User user = new User();
    User user1 = new User();
    Model model = new Model();
    Make make = new Make();
    Vehicle vehicle = new Vehicle();
    Vehicle vehicle1 = new Vehicle();
    
    public PurchaseDaoTest() {
    }
    @Before
    public void setUp() {
        user.setFirstName("John");
        user.setLastName("Johnson");
        user.setEmail("example@example.com");
        user.setRole("Admin");
        user.setPassword("password");
        user = userDao.create(user);
        
        user1.setFirstName("John");
        user1.setLastName("Johnson");
        user1.setEmail("example@example.com");
        user1.setRole("Admin");
        user1.setPassword("password");
        user1 = userDao.create(user1);
        
        make.setName("Ford");
        make.setDateAdded(LocalDate.parse("2001-05-05"));
        make.setUser(user);
        make = makeDao.create(make);
        
        model.setName("Focus");
        model.setDateAdded(LocalDate.parse("2015-05-05"));
        model.setUser(user);
        model.setMake(make);
        model = modelDao.create(model);
        
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setUser(user);
        vehicle.setYear(2000);
        vehicle.setMileage(1000);
        vehicle.setSalePrice(BigDecimal.ONE);
        vehicle.setMsrp(BigDecimal.ONE);
        vehicle.setStyle("style");
        vehicle.setInterior("interior");
        vehicle.setTrans("trans");
        vehicle.setColor("color");
        vehicle.setVin("12345678901234567");
        vehicle.setDescription("description");
        vehicle.setFileImg("fileImg");
        vehicle.setNewVehicle(true);
        vehicle.setFeatured(true);
        vehicle.setSold(false);
        vehicle = vehicleDao.create(vehicle);
        
        vehicle1.setMake(make);
        vehicle1.setModel(model);
        vehicle1.setUser(user);
        vehicle1.setYear(2000);
        vehicle1.setMileage(1000);
        vehicle1.setSalePrice(BigDecimal.ONE);
        vehicle1.setMsrp(BigDecimal.ONE);
        vehicle1.setStyle("style");
        vehicle1.setInterior("interior");
        vehicle1.setTrans("trans");
        vehicle1.setColor("color");
        vehicle1.setVin("12345678901234567");
        vehicle1.setDescription("description");
        vehicle1.setFileImg("fileImg");
        vehicle1.setNewVehicle(true);
        vehicle1.setFeatured(true);
        vehicle1.setSold(false);
        vehicle1 = vehicleDao.create(vehicle1);
    }
    
    @Test
    public void testReadAndCreate() {
        
        Purchase purchase = new Purchase();
        purchase.setCity("Dallas");
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPhone("9999999999");
        purchase.setPurchaseCost(new BigDecimal("6777"));
        purchase.setPurchaseType("Dealer Finance");
        purchase.setState("TX");
        purchase.setStreet("1113 Frankford");
        purchase.setStreet2("Apt# 1212");
        purchase.setZip(75000);
        purchase.setUser(user);
        purchase.setVehicle(vehicle);
        purchase.setFirstName("John");
        purchase.setLastName("Hopkins");
        purchase.setEmail("john@example.com");
        
        purchase = purchaseDao.create(purchase);
        
        Purchase fromDao = purchaseDao.read(purchase.getId());
        assertEquals(fromDao.getId(), purchase.getId());
        assertEquals(fromDao.getFirstName(), purchase.getFirstName());
        assertEquals(fromDao.getLastName(), purchase.getLastName());
        assertEquals(fromDao.getEmail(), purchase.getEmail());
        assertEquals(fromDao.getPhone(), purchase.getPhone());
        assertEquals(fromDao.getPurchaseDate(), purchase.getPurchaseDate());
        assertEquals(0, fromDao.getPurchaseCost().compareTo(purchase.getPurchaseCost()));
        assertEquals(0, fromDao.getPurchaseType().compareTo(purchase.getPurchaseType()));
        assertEquals(fromDao.getStreet(), purchase.getStreet());
        assertEquals(fromDao.getStreet2(), purchase.getStreet2());
        assertEquals(fromDao.getCity(), purchase.getCity());
        assertEquals(fromDao.getState(), purchase.getState());
        assertEquals(fromDao.getZip(), purchase.getZip());
        assertEquals(fromDao.getUser().getId(), purchase.getUser().getId());
        assertEquals(fromDao.getVehicle().getId(), purchase.getVehicle().getId());
        
    }
    
    @Test
    public void testReadList() {
        
        Purchase purchase = new Purchase();
        purchase.setCity("Dallas");
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPhone("9999999999");
        purchase.setPurchaseCost(new BigDecimal("6777"));
        purchase.setPurchaseType("Dealer Finance");
        purchase.setState("TX");
        purchase.setStreet("1113 Frankford");
        purchase.setStreet2("Apt# 1212");
        purchase.setZip(75000);
        purchase.setUser(user);
        purchase.setVehicle(vehicle);
        purchase.setFirstName("John");
        purchase.setLastName("Hopkins");
        purchase.setEmail("john@example.com");
        
        purchase = purchaseDao.create(purchase);
        
        Purchase purchase1 = new Purchase();
        purchase1.setCity("Dallas");
        purchase1.setPurchaseDate(LocalDate.now());
        purchase1.setPhone("9999999999");
        purchase1.setPurchaseCost(new BigDecimal("6777"));
        purchase1.setPurchaseType("Dealer Finance");
        purchase1.setState("TX");
        purchase1.setStreet("1113 Frankford");
        purchase1.setStreet2("Apt# 1212");
        purchase1.setZip(75000);
        purchase1.setUser(user);
        purchase1.setVehicle(vehicle);
        purchase1.setFirstName("John");
        purchase1.setLastName("Hopkins");
        purchase1.setEmail("john@example.com");
        
        purchase1 = purchaseDao.create(purchase1);
        
        List<Purchase> list = purchaseDao.readAll();
        
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), purchase.getId());
        assertEquals(list.get(1).getId(), purchase1.getId());
        
    }
    
    @Test
    public void testUpdateContact() {
        
        Purchase purchase = new Purchase();
        purchase.setCity("Dallas");
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPhone("9999999999");
        purchase.setPurchaseCost(new BigDecimal("6777"));
        purchase.setPurchaseType("Dealer Finance");
        purchase.setState("TX");
        purchase.setStreet("1113 Frankford");
        purchase.setStreet2("Apt# 1212");
        purchase.setZip(75000);
        purchase.setUser(user);
        purchase.setVehicle(vehicle);
        purchase.setFirstName("John");
        purchase.setLastName("Hopkins");
        purchase.setEmail("john@example.com");
        
        purchase = purchaseDao.create(purchase);
        
        purchase.setCity("Dallas2");
        purchase.setPurchaseDate(LocalDate.parse("2019-12-12"));
        purchase.setPhone("9999999990");
        purchase.setPurchaseCost(new BigDecimal("6770"));
        purchase.setPurchaseType("Dealer Finance2");
        purchase.setState("TX2");
        purchase.setStreet("1113 Frankford2");
        purchase.setStreet2("Apt# 12122");
        purchase.setZip(75002);
        purchase.setUser(user1);
        purchase.setVehicle(vehicle1);
        purchase.setFirstName("John2");
        purchase.setLastName("Hopkins2");
        purchase.setEmail("john@example.com2");
        
        
        purchaseDao.update(purchase);
        
        Purchase fromDao = purchaseDao.read(purchase.getId());
        
        assertEquals(fromDao.getId(), purchase.getId());
        assertEquals(fromDao.getFirstName(), purchase.getFirstName());
        assertEquals(fromDao.getLastName(), purchase.getLastName());
        assertEquals(fromDao.getEmail(), purchase.getEmail());
        assertEquals(fromDao.getPhone(), purchase.getPhone());
        assertEquals(fromDao.getPurchaseDate(), purchase.getPurchaseDate());
        assertEquals(0, fromDao.getPurchaseCost().compareTo(purchase.getPurchaseCost()));
        assertEquals(0, fromDao.getPurchaseType().compareTo(purchase.getPurchaseType()));
        assertEquals(fromDao.getStreet(), purchase.getStreet());
        assertEquals(fromDao.getStreet2(), purchase.getStreet2());
        assertEquals(fromDao.getCity(), purchase.getCity());
        assertEquals(fromDao.getState(), purchase.getState());
        assertEquals(fromDao.getZip(), purchase.getZip());
        assertEquals(fromDao.getUser().getId(), purchase.getUser().getId());
        assertEquals(fromDao.getVehicle().getId(), purchase.getVehicle().getId());
    }

}