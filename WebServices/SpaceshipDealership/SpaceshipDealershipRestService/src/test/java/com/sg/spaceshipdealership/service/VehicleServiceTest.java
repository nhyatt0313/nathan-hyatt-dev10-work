/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.dto.Model;
import com.sg.spaceshipdealership.dto.User;
import com.sg.spaceshipdealership.dto.Vehicle;
import com.sg.spaceshipdealership.service.DoesNotExistException;
import com.sg.spaceshipdealership.service.InvalidEntryException;
import com.sg.spaceshipdealership.service.MakeService;
import com.sg.spaceshipdealership.service.ModelService;
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
public class VehicleServiceTest {
    
    @Autowired
    private VehicleService vehicleService;
    
    @Autowired
    private MakeService makeService;
    
    @Autowired
    private ModelService modelService;
    
    @Autowired
    private UserService userService;
    
    User user;
    Make make;
    Model model;
    
    @Before
    public void setup() throws Exception{
        user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPassword("password");
        user.setRole("Admin");
        user = userService.create(user);
        
        
        make = new Make();
        make.setName("name");
        make.setDateAdded(LocalDate.now());
        make.setUser(user);
        make = makeService.create(make);
        
        model = new Model();
        model.setName("name");
        model.setDateAdded(LocalDate.now());
        model.setUser(user);
        model.setMake(make);
        model = modelService.create(model);
    }
    
    
    public VehicleServiceTest() {
    }

    @Test
    public void testCreateRead() throws InvalidEntryException, DoesNotExistException {
        // Arrange
        

        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPassword("password");
        user.setRole("Admin");
        User createdUser = userService.create(user);
        
        
        Make make = new Make();
        make.setName("name");
        make.setDateAdded(LocalDate.now());
        make.setUser(createdUser);
        Make createdMake = makeService.create(make);
        
        Model model = new Model();
        model.setName("name");
        model.setDateAdded(LocalDate.now());
        model.setUser(createdUser);
        model.setMake(createdMake);
        Model createdModel = modelService.create(model);

        
        Vehicle vehicle = new Vehicle();
        
        vehicle.setMake(createdMake);
        vehicle.setModel(createdModel);
        vehicle.setUser(createdUser);
        
        vehicle.setYear(2000);
        vehicle.setMileage(1000);
        vehicle.setSalePrice(BigDecimal.ONE);
        vehicle.setMsrp(BigDecimal.ONE);
        vehicle.setStyle("style");
        vehicle.setInterior("green");
        vehicle.setTrans("Automatic");
        vehicle.setColor("red");
        vehicle.setVin("vin");
        vehicle.setDescription("description");
        vehicle.setFileImg("fileImg.jpg");
        vehicle.setNewVehicle(true);
        vehicle.setFeatured(true);
        vehicle.setSold(false);
        
        // Act
        Vehicle v = vehicleService.create(vehicle);
        Vehicle createdVehicle = vehicleService.read(v.getId());
        
        // Assert
        assertNotNull(v);
        assertNotNull(createdVehicle);
        
        assertEquals(createdVehicle.getId(), v.getId());
        assertEquals(createdMake.getId(), v.getMake().getId());
        assertEquals(createdModel.getId(), v.getModel().getId());
        assertEquals(createdUser.getId(), v.getUser().getId());
        assertEquals((Integer)2000, v.getYear());
        assertEquals((Integer)1000, v.getMileage());
        assertEquals(BigDecimal.ONE, v.getSalePrice());
        assertEquals(BigDecimal.ONE, v.getMsrp());
        assertEquals("style", v.getStyle());
        assertEquals("green", v.getInterior());
        assertEquals("Automatic", v.getTrans());
        assertEquals("red", v.getColor());
        assertEquals("vin", v.getVin());
        assertEquals("description", v.getDescription());
        assertEquals("fileImg.jpg", v.getFileImg());
        assertEquals(true, v.isNewVehicle());
        assertEquals(true, v.isFeatured());
        assertEquals(false, v.isSold());
         
    }
    @Test
    public void testUpdate1() throws InvalidEntryException, DoesNotExistException {
        // Arrange
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPassword("password");
        user.setRole("Admin");
        User createdUser = userService.create(user);
        
        
        Make make = new Make();
        make.setName("name");
        make.setDateAdded(LocalDate.now());
        make.setUser(createdUser);
        Make createdMake = makeService.create(make);
        
        Model model = new Model();
        model.setName("name");
        model.setDateAdded(LocalDate.now());
        model.setUser(createdUser);
        model.setMake(createdMake);
        Model createdModel = modelService.create(model);

        
        Vehicle vehicle = new Vehicle();
        
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setUser(user);
        
        vehicle.setYear(2000);
        vehicle.setMileage(1000);
        vehicle.setSalePrice(BigDecimal.ONE);
        vehicle.setMsrp(BigDecimal.ONE);
        vehicle.setStyle("style");
        vehicle.setInterior("green");
        vehicle.setTrans("Automatic");
        vehicle.setColor("red");
        vehicle.setVin("vin");
        vehicle.setDescription("description");
        vehicle.setFileImg("fileImg.jpg");
        vehicle.setNewVehicle(true);
        vehicle.setFeatured(true);
        vehicle.setSold(false);
        
        // Act
        Vehicle v = vehicleService.create(vehicle);
        
        v.setColor("black");
        vehicleService.update(v);
        Vehicle createdVehicle = vehicleService.read(v.getId());
        
        // Assert
        assertNotNull(v);
        assertNotNull(createdVehicle);
        
        assertEquals(createdVehicle.getId(), v.getId());
        assertEquals(createdMake.getId(), v.getMake().getId());
        assertEquals(createdModel.getId(), v.getModel().getId());
        assertEquals(createdUser.getId(), v.getUser().getId());
        assertEquals((Integer)2000, v.getYear());
        assertEquals((Integer)1000, v.getMileage());
        assertEquals(BigDecimal.ONE, v.getSalePrice());
        assertEquals(BigDecimal.ONE, v.getMsrp());
        assertEquals("style", v.getStyle());
        assertEquals("green", v.getInterior());
        assertEquals("Automatic", v.getTrans());
        assertEquals("black", v.getColor());
        assertEquals("vin", v.getVin());
        assertEquals("description", v.getDescription());
        assertEquals("fileImg.jpg", v.getFileImg());
        assertEquals(true, v.isNewVehicle());
        assertEquals(true, v.isFeatured());
        assertEquals(false, v.isSold());     
    }
    @Test
    public void testUpdateMultiple() throws InvalidEntryException, DoesNotExistException {
        // Arrange
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPassword("password");
        user.setRole("Admin");
        User createdUser = userService.create(user);
        
        
        Make make = new Make();
        make.setName("name");
        make.setDateAdded(LocalDate.now());
        make.setUser(createdUser);
        Make createdMake = makeService.create(make);
        
        Model model = new Model();
        model.setName("name");
        model.setDateAdded(LocalDate.now());
        model.setUser(createdUser);
        model.setMake(createdMake);
        Model createdModel = modelService.create(model);

        
        Vehicle vehicle = new Vehicle();
        
        
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setUser(user);
        
        vehicle.setYear(2000);
        vehicle.setMileage(1000);
        vehicle.setSalePrice(BigDecimal.ONE);
        vehicle.setMsrp(BigDecimal.ONE);
        vehicle.setStyle("style");
        vehicle.setInterior("green");
        vehicle.setTrans("Automatic");
        vehicle.setColor("red");
        vehicle.setVin("vin");
        vehicle.setDescription("description");
        vehicle.setFileImg("fileImg.jpg");
        vehicle.setNewVehicle(true);
        vehicle.setFeatured(true);
        vehicle.setSold(false);
        
        // Act
        Vehicle v = vehicleService.create(vehicle);
        
        v.setStyle("newStyle");
        v.setDescription("newDescription");
        v.setSold(true);
        vehicleService.update(v);
        Vehicle createdVehicle = vehicleService.read(v.getId());
        
        // Assert
        assertNotNull(v);
        assertNotNull(createdVehicle);
        
        assertEquals(createdVehicle.getId(), v.getId());
        assertEquals(createdMake.getId(), v.getMake().getId());
        assertEquals(createdModel.getId(), v.getModel().getId());
        assertEquals(createdUser.getId(), v.getUser().getId());
        assertEquals((Integer)2000, v.getYear());
        assertEquals((Integer)1000, v.getMileage());
        assertEquals(BigDecimal.ONE, v.getSalePrice());
        assertEquals(BigDecimal.ONE, v.getMsrp());
        assertEquals("newStyle", v.getStyle());
        assertEquals("green", v.getInterior());
        assertEquals("Automatic", v.getTrans());
        assertEquals("red", v.getColor());
        assertEquals("vin", v.getVin());
        assertEquals("newDescription", v.getDescription());
        assertEquals("fileImg.jpg", v.getFileImg());
        assertEquals(true, v.isNewVehicle());
        assertEquals(true, v.isFeatured());
        assertEquals(true, v.isSold());
    }
    @Test
    public void testReadAll() throws InvalidEntryException, DoesNotExistException {
        // Arrange
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPassword("password");
        user.setRole("Admin");
        User createdUser = userService.create(user);
        
        
        Make make = new Make();
        make.setName("name");
        make.setDateAdded(LocalDate.now());
        make.setUser(createdUser);
        Make createdMake = makeService.create(make);
        
        Model model = new Model();
        model.setName("name");
        model.setDateAdded(LocalDate.now());
        model.setUser(createdUser);
        model.setMake(createdMake);
        Model createdModel = modelService.create(model);
        
        Vehicle vehicle = new Vehicle();

        vehicle.setMake(createdMake);
        vehicle.setModel(createdModel);
        vehicle.setUser(createdUser);
        
        vehicle.setYear(2000);
        vehicle.setMileage(1000);
        vehicle.setSalePrice(BigDecimal.ONE);
        vehicle.setMsrp(BigDecimal.ONE);
        vehicle.setStyle("style");
        vehicle.setInterior("green");
        vehicle.setTrans("Automatic");
        vehicle.setColor("red");
        vehicle.setVin("vin");
        vehicle.setDescription("description");
        vehicle.setFileImg("fileImg.jpg");
        vehicle.setNewVehicle(true);
        vehicle.setFeatured(true);
        vehicle.setSold(false);
        
        
        
        
        User user2 = new User();
        user2.setFirstName("firstName");
        user2.setLastName("lastName");
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setRole("Admin");
        User createdUser2 = userService.create(user);
        
        
        Make make2 = new Make();
        make2.setName("name");
        make2.setDateAdded(LocalDate.now());
        make2.setUser(createdUser);
        Make createdMake2 = makeService.create(make);
        
        Model model2 = new Model();
        model2.setName("name");
        model2.setDateAdded(LocalDate.now());
        model2.setUser(createdUser);
        model2.setMake(createdMake);
        Model createdModel2 = modelService.create(model);
  
        Vehicle vehicle2 = new Vehicle();

        vehicle2.setMake(createdMake2);
        vehicle2.setModel(createdModel2);
        vehicle2.setUser(createdUser2);
        
        vehicle2.setYear(2002);
        vehicle2.setMileage(1002);
        vehicle2.setSalePrice(new BigDecimal("2"));
        vehicle2.setMsrp(new BigDecimal("2"));
        vehicle2.setStyle("style2");
        vehicle2.setInterior("silver");
        vehicle2.setTrans("Manual");
        vehicle2.setColor("blue");
        vehicle2.setVin("vin2");
        vehicle2.setDescription("description2");
        vehicle2.setFileImg("fileImg.jpeg");
        vehicle2.setNewVehicle(false);
        vehicle2.setFeatured(true);
        vehicle2.setSold(false);
        
        // Act
        Vehicle v = vehicleService.create(vehicle);
        Vehicle v2 = vehicleService.create(vehicle2);
        
        Vehicle createdVehicle = vehicleService.read(v.getId());
        Vehicle createdVehicle2 = vehicleService.read(v2.getId());
        
        List<Vehicle> vehicles = vehicleService.readAll();
        
        v = vehicles.get(0);
        v2 = vehicles.get(1);
        // Assert
        
        assertNotNull(v);
        assertNotNull(v2);
        
        assertEquals(2, vehicles.size());
        
        assertEquals(createdVehicle.getId(), v.getId());
        assertEquals(createdMake.getId(), v.getMake().getId());
        assertEquals(createdModel.getId(), v.getModel().getId());
        assertEquals(createdUser.getId(), v.getUser().getId());
        assertEquals((Integer)2000, v.getYear());
        assertEquals((Integer)1000, v.getMileage());
        assertEquals(new BigDecimal("1.00"), v.getSalePrice());
        assertEquals(new BigDecimal("1.00"), v.getMsrp());
        assertEquals("style", v.getStyle());
        assertEquals("green", v.getInterior());
        assertEquals("Automatic", v.getTrans());
        assertEquals("red", v.getColor());
        assertEquals("vin", v.getVin());
        assertEquals("description", v.getDescription());
        assertEquals("fileImg.jpg", v.getFileImg());
        assertEquals(true, v.isNewVehicle());
        assertEquals(true, v.isFeatured());
        assertEquals(false, v.isSold());
        
        
        assertEquals(createdVehicle2.getId(), v2.getId());
        assertEquals(createdMake2.getId(), v2.getMake().getId());
        assertEquals(createdModel2.getId(), v2.getModel().getId());
        assertEquals(createdUser2.getId(), v2.getUser().getId());
        assertEquals((Integer)2002, v2.getYear());
        assertEquals((Integer)1002, v2.getMileage());
        assertEquals(new BigDecimal("2.00"), v2.getSalePrice());
        assertEquals(new BigDecimal("2.00"), v2.getMsrp());
        assertEquals("style2", v2.getStyle());
        assertEquals("silver", v2.getInterior());
        assertEquals("Manual", v2.getTrans());
        assertEquals("blue", v2.getColor());
        assertEquals("vin2", v2.getVin());
        assertEquals("description2", v2.getDescription());
        assertEquals("fileImg.jpeg", v2.getFileImg());
        assertEquals(false, v2.isNewVehicle());
        assertEquals(true, v2.isFeatured());
        assertEquals(false, v2.isSold());
        
    }
    @Test
    public void testSearch() throws InvalidEntryException, DoesNotExistException{
        // Arrange
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPassword("password");
        user.setRole("Admin");
        User createdUser = userService.create(user);
        
        
        Make make = new Make();
        make.setName("make");
        make.setDateAdded(LocalDate.now());
        make.setUser(createdUser);
        Make createdMake = makeService.create(make);
        
        Model model = new Model();
        model.setName("model");
        model.setDateAdded(LocalDate.now());
        model.setUser(createdUser);
        model.setMake(createdMake);
        Model createdModel = modelService.create(model);
        
        Vehicle vehicle = new Vehicle();

        vehicle.setMake(createdMake);
        vehicle.setModel(createdModel);
        vehicle.setUser(createdUser);
        
        vehicle.setYear(2000);
        vehicle.setMileage(1000);
        vehicle.setSalePrice(new BigDecimal("1000"));
        vehicle.setMsrp(new BigDecimal("1000"));
        vehicle.setStyle("style");
        vehicle.setInterior("red");
        vehicle.setTrans("Manual");
        vehicle.setColor("black");
        vehicle.setVin("vin");
        vehicle.setDescription("description");
        vehicle.setFileImg("fileImg.png");
        vehicle.setNewVehicle(true);
        vehicle.setFeatured(true);
        vehicle.setSold(false);
        
        
        
        
        User user2 = new User();
        user2.setFirstName("firstName");
        user2.setLastName("lastName");
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setRole("Admin");
        User createdUser2 = userService.create(user);
        
        
        Make make2 = new Make();
        make2.setName("otherMake");
        make2.setDateAdded(LocalDate.now());
        make2.setUser(createdUser);
        Make createdMake2 = makeService.create(make2);
        
        Model model2 = new Model();
        model2.setName("otherModel");
        model2.setDateAdded(LocalDate.now());
        model2.setUser(createdUser);
        model2.setMake(createdMake);
        Model createdModel2 = modelService.create(model2);
  
        Vehicle vehicle2 = new Vehicle();

        vehicle2.setMake(createdMake2);
        vehicle2.setModel(createdModel2);
        vehicle2.setUser(createdUser2);
        
        vehicle2.setYear(2002);
        vehicle2.setMileage(1002);
        vehicle2.setSalePrice(new BigDecimal("2000"));
        vehicle2.setMsrp(new BigDecimal("2000"));
        vehicle2.setStyle("style2");
        vehicle2.setInterior("blue");
        vehicle2.setTrans("Automatic");
        vehicle2.setColor("silver");
        vehicle2.setVin("vin2");
        vehicle2.setDescription("description2");
        vehicle2.setFileImg("fileImg2.jpg");
        vehicle2.setNewVehicle(false);
        vehicle2.setFeatured(true);
        vehicle2.setSold(false);
        
        
        // Act
        Vehicle v = vehicleService.create(vehicle);
        Vehicle v2 = vehicleService.create(vehicle2);
        
        List<Vehicle> vehicles = vehicleService.readAll();
        List<Vehicle> searchedVehicles = vehicleService.search("2000", true, null, null, null, null);
        
        Vehicle foundVehicle = searchedVehicles.get(0);
        // Assert
        assertEquals(vehicles.size() - 1, searchedVehicles.size()); // one vehicle should not have been found
        assertEquals(1, searchedVehicles.size()); // only one vehicle should have been found
        
        // vehicle 1 should have been found
        assertEquals(v.getId(), foundVehicle.getId());
        assertEquals(createdMake.getId(), foundVehicle.getMake().getId());
        assertEquals(createdModel.getId(), foundVehicle.getModel().getId());
        assertEquals(createdUser.getId(), foundVehicle.getUser().getId());
        assertEquals((Integer)2000, foundVehicle.getYear());
        assertEquals((Integer)1000, foundVehicle.getMileage());
        assertEquals(0, new BigDecimal("1000.00").compareTo(foundVehicle.getSalePrice()));
        assertEquals(0, new BigDecimal("1000.00").compareTo(foundVehicle.getMsrp()));
        assertEquals("style", foundVehicle.getStyle());
        assertEquals("red", foundVehicle.getInterior());
        assertEquals("Manual", foundVehicle.getTrans());
        assertEquals("black", foundVehicle.getColor());
        assertEquals("vin", foundVehicle.getVin());
        assertEquals("description", foundVehicle.getDescription());
        assertEquals("fileImg.png", foundVehicle.getFileImg());
        assertEquals(true, foundVehicle.isNewVehicle());
        assertEquals(true, foundVehicle.isFeatured());
        assertEquals(false, foundVehicle.isSold());
        
        searchedVehicles = vehicleService.search("make", true, null, null, null, null);
        
        foundVehicle = searchedVehicles.get(0);
        // Assert
        assertEquals(vehicles.size() - 1, searchedVehicles.size()); // one vehicle should not have been found
        assertEquals(1, searchedVehicles.size()); // only one vehicle should have been found
        
        // vehicle 1 should have been found
       assertEquals(v.getId(), foundVehicle.getId());
        assertEquals(createdMake.getId(), foundVehicle.getMake().getId());
        assertEquals(createdModel.getId(), foundVehicle.getModel().getId());
        assertEquals(createdUser.getId(), foundVehicle.getUser().getId());
        assertEquals((Integer)2000, foundVehicle.getYear());
        assertEquals((Integer)1000, foundVehicle.getMileage());
        assertEquals(0, new BigDecimal("1000").compareTo(foundVehicle.getSalePrice()));
        assertEquals(0, new BigDecimal("1000").compareTo(foundVehicle.getMsrp()));
        assertEquals("style", foundVehicle.getStyle());
        assertEquals("red", foundVehicle.getInterior());
        assertEquals("Manual", foundVehicle.getTrans());
        assertEquals("black", foundVehicle.getColor());
        assertEquals("vin", foundVehicle.getVin());
        assertEquals("description", foundVehicle.getDescription());
        assertEquals("fileImg.png", foundVehicle.getFileImg());
        assertEquals(true, foundVehicle.isNewVehicle());
        assertEquals(true, foundVehicle.isFeatured());
        assertEquals(false, foundVehicle.isSold());
    }
     /*
        VALIDATION TESTS
    */
    @Test
    public void testCreateValidYear() throws DoesNotExistException{
        // 2000
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        v.setYear(2000);
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Year")){
                fail("year 2000 should have been valid");
            }
        }
        
        // current year + 1
        v.setYear(LocalDate.now().getYear() + 1); 
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Year")){
                fail("year "+v.getYear()+" should have been valid");
            }
        }
    }
    @Test
    public void testCreateInvalidYear() throws DoesNotExistException{
        // invalid year (less than 2000)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        v.setYear(1999);
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Year")){
                fail("year 1999 should not have been valid");
            }
        }
        // invalid year (greater than currentYear + 1)
        v.setYear(LocalDate.now().getYear() + 2); 
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Year")){
                fail("year "+v.getYear()+" should not have been valid");
            }
        }
            
    }
    @Test
    public void testCreateValidTrans() throws DoesNotExistException{
        // Automatic
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        v.setTrans("Automatic");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Transmission")){
                fail("Automatic transmission should have been valid");
            }
        }
        // Manual
        v.setTrans("Manual");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid VehicleTransmission")){
                fail("Manual transmission should have been valid");
            }
        }
    }
    @Test
    public void testCreateInvalidTrans() throws DoesNotExistException{
        // invalid trans (not Automatic or Manual)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        v.setTrans("Potato");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Transmission")){
                fail("Potato transmission should not have been valid");
            }
        }
        // invalid trans (empty)
        Vehicle v1 = new Vehicle();
        v1.setUser(user);
        v1.setMake(make);
        v1.setModel(model);
        
        try {
            vehicleService.create(v1);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Transmission")){
                fail("null transmission should not have been valid");
            }
        }
        // invalid trans (" ")
        v.setTrans(" ");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Transmission")){
                fail("(only spaces) transmission should not have been valid");
            }
        }
    }
    @Test
    public void testCreateValidColor() throws DoesNotExistException{
        // red
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        v.setColor("red");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Color")){
                fail("color "+v.getColor()+" should have been valid");
            }
        }
        // blue
        v.setColor("blue");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Color")){
                fail("color "+v.getColor()+" should have been valid");
            }
        }
        // green
        v.setColor("green");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Color")){
                fail("color "+v.getColor()+" should have been valid");
            }
        }
        // black
        v.setColor("black");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Color")){
                fail("color "+v.getColor()+" should have been valid");
            }
        }
        // silver
        v.setColor("silver");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Color")){
                fail("color "+v.getColor()+" should have been valid");
            }
        }
    }
    @Test
    public void testCreateInvalidColor() throws DoesNotExistException{
        // invalid color (null)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Color")){
                fail("color "+v.getColor()+" should not have been valid");
            }
        }
        // invalid color (" ")
        v.setColor(" ");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Color")){
                fail("color "+v.getColor()+" should not have been valid");
            }
        }
        // invalid color (not red, blue black, silver, or green)
        v.setColor("potato");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Color")){
                fail("color "+v.getColor()+" should not have been valid");
            }
        }
    }
    @Test
    public void testCreateValidInterior() throws DoesNotExistException{
        // red
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        v.setInterior("red");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Interior")){
                fail("interior "+v.getColor()+" should have been valid");
            }
        }
        // blue
        v.setInterior("blue");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Interior")){
                fail("interior "+v.getColor()+" should have been valid");
            }
        }
        // green
        v.setInterior("green");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Interior")){
                fail("interior "+v.getColor()+" should have been valid");
            }
        }
        // black
        v.setInterior("black");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Interior")){
                fail("interior "+v.getColor()+" should have been valid");
            }
        }
        // silver
        v.setInterior("silver");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Interior")){
                fail("interior "+v.getColor()+" should have been valid");
            }
        }
    }
    @Test
    public void testCreateInvalidInterior() throws DoesNotExistException{
        // invalid interior (null)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Interior")){
                fail("null interior should not have been valid");
            }
        }
        // invalid interior (" ")
        v.setInterior(" ");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Interior")){
                fail("interior "+v.getColor()+" should  not have been valid");
            }
        }
        // invalid interior (not red, blue black, silver, or green)
        v.setInterior("batman");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Interior")){
                fail("interior "+v.getColor()+" should  not have been valid");
            }
        }
    }
    @Test
    public void testCreateValidMileage() throws DoesNotExistException{
        // valid mileage (new and mileage < 1000)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        v.setNewVehicle(true);
        v.setMileage(999);
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Mileage")){
                fail("new = "+v.isNewVehicle()+" and miles = "+v.getMileage()+" should have been valid");
            }
        }
        // valid mileage (used and mileage > 1000)
        v.setNewVehicle(false);
        v.setMileage(1001);
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Mileage")){
                fail("new = "+v.isNewVehicle()+" and miles = "+v.getMileage()+" should have been valid");
            }
        }
    }
    @Test
    public void testCreateInvalidMileage() throws DoesNotExistException{
        // invalid mileage (less than 0)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        v.setMileage(-1);
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("User") || errors.contains("Make") || errors.contains("Model")){
                fail("User, Make or Model failed to be successfully added");
            } else if (!errors.contains("Invalid Vehicle Mileage")){
                fail("miles = "+v.getMileage()+" should not have been valid");
            }
        }
        // invalid mileage (new and mileage > 1000)
        v.setNewVehicle(true);
        v.setMileage(1001);
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Mileage")){
                fail("new = "+v.isNewVehicle()+" and miles = "+v.getMileage()+" should not have been valid");
            }
        }
        // invalid mileage (used and mileage < 1000)
        v.setNewVehicle(false);
        v.setMileage(999);
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Mileage")){
                fail("new = "+v.isNewVehicle()+" and miles = "+v.getMileage()+" should not have been valid");
            }
        }
    }
    @Test
    public void testCreateValidVin() throws DoesNotExistException{
        // valid vin (pretty much anything)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        v.setVin("vin");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Vin")){
                fail("vin "+v.getVin()+" should have been valid");
            }
        }
    }
    @Test
    public void testCreateInvalidVin() throws DoesNotExistException{
        // invalid vin (null)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Vin")){
                fail("vin null should not have been valid");
            }
        }
        // invalid vin (" ")
        v.setVin(" ");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Vin")){
                fail("vin "+v.getVin()+" should not have been valid");
            }
        }
    }
    @Test
    public void testCreateValidMsrp() throws DoesNotExistException{
        // valid msrp (not negative)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        v.setMsrp(new BigDecimal("1"));
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Msrp")){
                fail("msrp "+v.getMsrp()+" should have been valid");
            }
        }
    }
    @Test
    public void testCreateInvalidMsrp() throws DoesNotExistException{
        // invlaid msrp (null)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Msrp")) {
                fail("msrp null should not have been valid");
            }
        }
        // invalid msrp (negative msrp)
        v.setMsrp(new BigDecimal("-1"));
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Msrp")){
                fail("msrp "+v.getMsrp()+" should not have been valid");
            }
        }
    }
    @Test
    public void testCreateValidSalePrice() throws DoesNotExistException{
        // valid salePrice (salePrice = MSRP)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        v.setSalePrice(new BigDecimal("1"));
        v.setMsrp(new BigDecimal("1"));
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Sale Price")){
                fail("msrp "+v.getMsrp()+" and salePrice "+v.getSalePrice()+" should have been valid");
            }
        }
        // valid salePrice (salePrice = MSRP -1)
        v.setSalePrice(new BigDecimal("1"));
        v.setMsrp(new BigDecimal("2"));
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Sale Price")){
                fail("msrp "+v.getMsrp()+" and salePrice "+v.getSalePrice()+" should have been valid");
            }
        }
    }
    @Test
    public void testCreateInvalidSalePrice() throws DoesNotExistException{
        // invalid salePrice (null)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Sale Price")){
                fail("null salePrice should not have been valid");
            }
        }
        // invalid salePrice (salePrice > MSRP)
        v.setSalePrice(new BigDecimal("2"));
        v.setMsrp(new BigDecimal("1"));
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Sale Price")){
                fail("msrp "+v.getMsrp()+" and salePrice "+v.getSalePrice()+" should not have been valid");
            }
        }
    }
    @Test
    public void testCreateValidDescription() throws DoesNotExistException{
        // valid description (pretty much anything)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        v.setDescription("description");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Description")){
                fail("description "+v.getDescription()+" should have been valid");
            }
        }
    }
    @Test
    public void testCreateInvalidDescription() throws DoesNotExistException{
        // invalid description (null)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Description")){
                fail("description null should not have been valid");
            }
        }
        // invalid description (" ")
        v.setDescription(" ");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Description")){
                fail("description "+v.getDescription()+" should not have been valid");
            }
        }
    }
    @Test
    public void testCreateValidFileImg() throws DoesNotExistException{
        // .png
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        v.setFileImg("fileImg.png");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Image Type")){
                fail("fileImg "+v.getFileImg()+" should have been valid");
            }
        }
        // .jpg
        v.setFileImg("fileImg.jpg");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Image Type")){
                fail("fileImg "+v.getFileImg()+" should have been valid");
            }
        }
        // .jpeg
        v.setFileImg("fileImg.jpeg");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (errors.contains("Invalid Vehicle Image Type")){
                fail("fileImg "+v.getFileImg()+" should have been valid");
            }
        }
    }
    @Test
    public void testCreateInvalidFileImg() throws DoesNotExistException{
        // invalid fileImg (null)
        Vehicle v = new Vehicle();
        v.setUser(user);
        v.setMake(make);
        v.setModel(model);
        
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Image Type")){
                fail("fileImg null should not have been valid");
            }
        }
        // invalid fileImg (not .png, .jpg, or .jpeg)
        v.setFileImg("fileImg.nope");
        try {
            vehicleService.create(v);
            fail("definitely should have thrown errors");
        } catch (InvalidEntryException e) {
            String errors = e.getMessage();
            if (!errors.contains("Invalid Vehicle Image Type")){
                fail("fileImg "+v.getFileImg()+" should not have been valid");
            }
        }
    }
    
}
