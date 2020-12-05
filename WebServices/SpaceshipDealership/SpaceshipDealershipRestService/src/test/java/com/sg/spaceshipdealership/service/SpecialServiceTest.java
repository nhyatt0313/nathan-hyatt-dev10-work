/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

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

import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.dto.Model;
import com.sg.spaceshipdealership.dto.Special;
import com.sg.spaceshipdealership.dto.User;
import com.sg.spaceshipdealership.dto.Vehicle;
import com.sg.spaceshipdealership.service.DoesNotExistException;
import com.sg.spaceshipdealership.service.InvalidEntryException;
import com.sg.spaceshipdealership.service.MakeService;
import com.sg.spaceshipdealership.service.ModelService;
import com.sg.spaceshipdealership.service.SpecialService;
import com.sg.spaceshipdealership.service.UserService;
import com.sg.spaceshipdealership.service.VehicleService;

/**
 *
 * @author nhyat
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class SpecialServiceTest {
    
    @Autowired
    SpecialService specialService;
    
    @Autowired
    VehicleService vehicleService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    MakeService makeService;
    
    @Autowired
    ModelService modelService;
    
    public SpecialServiceTest() {
    }
    
    User user = new User();
    Make make = new Make();
    Model model = new Model();
    Vehicle vehicle = new Vehicle();
    Vehicle vehicle1 = new Vehicle();
    
    @Before
    public void setUp() throws InvalidEntryException, DoesNotExistException {
        
        user.setFirstName("John");
        user.setLastName("Johnson");
        user.setEmail("example@example.com");
        user.setRole("Admin");
        user.setPassword("password");
        user = userService.create(user);
        
        make.setName("Ford");
        make.setDateAdded(LocalDate.parse("2001-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        make.setUser(user);
        make = makeService.create(make);
        
        model.setName("Focus");
        model.setDateAdded(LocalDate.parse("2015-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.setUser(user);
        model.setMake(make);
        model = modelService.create(model);
        
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setUser(user);
        vehicle.setYear(2000);
        vehicle.setMileage(1000);
        vehicle.setSalePrice(BigDecimal.ONE);
        vehicle.setMsrp(BigDecimal.ONE);
        vehicle.setStyle("style");
        vehicle.setInterior("black");
        vehicle.setTrans("Manual");
        vehicle.setColor("silver");
        vehicle.setVin("vin");
        vehicle.setDescription("description");
        vehicle.setFileImg("fileImg.jpeg");
        vehicle.setNewVehicle(true);
        vehicle.setFeatured(true);
        vehicle.setSold(false);
        vehicle = vehicleService.create(vehicle);
        
        vehicle1.setMake(make);
        vehicle1.setModel(model);
        vehicle1.setUser(user);
        vehicle1.setYear(2000);
        vehicle1.setMileage(1000);
        vehicle1.setSalePrice(BigDecimal.ONE);
        vehicle1.setMsrp(BigDecimal.ONE);
        vehicle1.setStyle("style");
        vehicle1.setInterior("blue");
        vehicle1.setTrans("Automatic");
        vehicle1.setColor("red");
        vehicle1.setVin("vin");
        vehicle1.setDescription("description");
        vehicle1.setFileImg("fileImg.png");
        vehicle1.setNewVehicle(true);
        vehicle1.setFeatured(true);
        vehicle1.setSold(false);
        vehicle1 = vehicleService.create(vehicle1);
    }
    
    @Test
    public void testRead() throws DoesNotExistException, InvalidEntryException {
        
        Special special = new Special();
        special.setTitle("On Sale");
        special.setDescription("new price for this car, get now!");
        special = specialService.create(special);
        
        Special fromService = specialService.read(special.getId());
        
        assertEquals(fromService.getTitle(), "On Sale");
        assertEquals(fromService.getDescription(), "new price for this car, get now!");
        
    }

    
    @Test
    public void testReadList() throws InvalidEntryException {
        
        Special special = new Special();
        special.setTitle("On Sale");
        special.setDescription("new price for this car, get now!");
        special = specialService.create(special);
        
        Special special2 = new Special();
        special2.setTitle("On Sale");
        special2.setDescription("new price for this car, get now!");
        special2 = specialService.create(special2);
        
        List<Special> list = specialService.readAll();
        
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), special.getId());
        assertEquals(list.get(1).getId(), special2.getId());
        
    }

    
    @Test
    public void testCreate() throws DoesNotExistException, InvalidEntryException {
        
        Special special = new Special();
        special.setTitle("On Sale");
        special.setDescription("new price for this car, get now!");
        
        special = specialService.create(special);
        
        Special fromService = specialService.read(special.getId());
        assertEquals(fromService.getTitle(), "On Sale");
        assertEquals(fromService.getDescription(), "new price for this car, get now!");
        
    }

    
    @Test
    public void testUpdate() throws DoesNotExistException, InvalidEntryException {
        
        Special special = new Special();
        special.setTitle("On Sale");
        special.setDescription("new price for this car, get now!");
        special = specialService.create(special);
        
        special.setTitle("Not on sale");
        special.setDescription("car is expensive, dont get");
        
        specialService.update(special);
        
        Special fromService = specialService.read(special.getId());
        assertEquals(fromService.getTitle(), "Not on sale");
        assertEquals(fromService.getDescription(), "car is expensive, dont get");
    }

    
    @Test
    public void testDelete() throws DoesNotExistException, InvalidEntryException {
        
        Special special = new Special();
        special.setTitle("On Sale");
        special.setDescription("new price for this car, get now!");
        
        special = specialService.create(special);
        
        specialService.delete(special.getId());
        
        try {
            Special fromService = specialService.read(special.getId());
            fail("Should have cought exception - no longer exists");
        } catch (DoesNotExistException e){}
    }

}