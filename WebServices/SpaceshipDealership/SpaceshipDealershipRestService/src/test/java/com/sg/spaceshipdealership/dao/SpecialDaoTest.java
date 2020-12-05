/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import com.sg.spaceshipdealership.dao.MakeDao;
import com.sg.spaceshipdealership.dao.ModelDao;
import com.sg.spaceshipdealership.dao.SpecialDao;
import com.sg.spaceshipdealership.dao.UserDao;
import com.sg.spaceshipdealership.dao.VehicleDao;
import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.dto.Model;
import com.sg.spaceshipdealership.dto.Special;
import com.sg.spaceshipdealership.dto.User;
import com.sg.spaceshipdealership.dto.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SpecialDaoTest {
    
    @Autowired
    SpecialDao specialDao;
    
    @Autowired
    VehicleDao vehicleDao;
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    MakeDao makeDao;
    
    @Autowired
    ModelDao modelDao;
    
    public SpecialDaoTest() {
    }
    
    User user = new User();
    Make make = new Make();
    Model model = new Model();
    Vehicle vehicle = new Vehicle();
    Vehicle vehicle1 = new Vehicle();
    
    @Before
    public void setUp() {
        
        user.setFirstName("John");
        user.setLastName("Johnson");
        user.setEmail("example@example.com");
        user.setRole("Admin");
        user.setPassword("password");
        user = userDao.create(user);
        
        make.setName("Ford");
        make.setDateAdded(LocalDate.parse("2001-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        make.setUser(user);
        make = makeDao.create(make);
        
        model.setName("Focus");
        model.setDateAdded(LocalDate.parse("2015-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
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
        vehicle.setVin("vin");
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
        vehicle1.setVin("vin");
        vehicle1.setDescription("description");
        vehicle1.setFileImg("fileImg");
        vehicle1.setNewVehicle(true);
        vehicle1.setFeatured(true);
        vehicle1.setSold(false);
        vehicle1 = vehicleDao.create(vehicle1);
    }
    
    @Test
    public void testRead() {
        
        Special special = new Special();
        special.setTitle("On Sale");
        special.setDescription("new price for this car, get now!");
        special = specialDao.create(special);
        
        Special fromDao = specialDao.read(special.getId());
        
        assertEquals(fromDao.getTitle(), "On Sale");
        assertEquals(fromDao.getDescription(), "new price for this car, get now!");
        
    }

    
    @Test
    public void testReadList() {
        
        Special special = new Special();
        special.setTitle("On Sale");
        special.setDescription("new price for this car, get now!");
        special = specialDao.create(special);
        
        Special special2 = new Special();
        special2.setTitle("On Sale");
        special2.setDescription("new price for this car, get now!");
        special2 = specialDao.create(special2);
        
        List<Special> list = specialDao.readList();
        
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), special.getId());
        assertEquals(list.get(1).getId(), special2.getId());
        
    }

    
    @Test
    public void testCreate() {
        
        Special special = new Special();
        special.setTitle("On Sale");
        special.setDescription("new price for this car, get now!");
        
        special = specialDao.create(special);
        
        Special fromDao = specialDao.read(special.getId());
        assertEquals(fromDao.getTitle(), "On Sale");
        assertEquals(fromDao.getDescription(), "new price for this car, get now!");
        
    }

    
    @Test
    public void testUpdate() {
        
        Special special = new Special();
        special.setTitle("On Sale");
        special.setDescription("new price for this car, get now!");
        special = specialDao.create(special);
        
        special.setTitle("Not on sale");
        special.setDescription("car is expensive, dont get");
        
        specialDao.update(special);
        
        Special fromDao = specialDao.read(special.getId());
        assertEquals(fromDao.getTitle(), "Not on sale");
        assertEquals(fromDao.getDescription(), "car is expensive, dont get");
    }

    
    @Test
    public void testDelete() {
        
        Special special = new Special();
        special.setTitle("On Sale");
        special.setDescription("new price for this car, get now!");
        
        special = specialDao.create(special);
        
        specialDao.delete(special.getId());
        
        Special fromDao = specialDao.read(special.getId());
        assertNull(fromDao);
    }

}