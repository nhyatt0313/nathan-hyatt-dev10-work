/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import com.sg.spaceshipdealership.dao.MakeDao;
import com.sg.spaceshipdealership.dao.ModelDao;
import com.sg.spaceshipdealership.dao.UserDao;
import com.sg.spaceshipdealership.dao.VehicleDao;
import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.dto.Model;
import com.sg.spaceshipdealership.dto.User;
import com.sg.spaceshipdealership.dto.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
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
public class VehicleDaoTest {
    
    @Autowired
    private VehicleDao vehicleDao;
    
    @Autowired
    private MakeDao makeDao;
    
    @Autowired
    private ModelDao modelDao;
    
    @Autowired
    private UserDao userDao;
    
    
    public VehicleDaoTest() {
    }

    @Test
    public void testCreateRead() {
        // Arrange
        

        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPassword("password");
        user.setRole("Admin");
        User createdUser = userDao.create(user);
        
        
        Make make = new Make();
        make.setName("name");
        make.setDateAdded(LocalDate.now());
        make.setUser(createdUser);
        Make createdMake = makeDao.create(make);
        
        Model model = new Model();
        model.setName("name");
        model.setDateAdded(LocalDate.now());
        model.setUser(createdUser);
        model.setMake(createdMake);
        Model createdModel = modelDao.create(model);

        
        Vehicle vehicle = new Vehicle();
        
        vehicle.setMake(createdMake);
        vehicle.setModel(createdModel);
        vehicle.setUser(createdUser);
        
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
        
        // Act
        Vehicle v = vehicleDao.create(vehicle);
        Vehicle createdVehicle = vehicleDao.read(v.getId());
        
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
        assertEquals("interior", v.getInterior());
        assertEquals("trans", v.getTrans());
        assertEquals("color", v.getColor());
        assertEquals("vin", v.getVin());
        assertEquals("description", v.getDescription());
        assertEquals("fileImg", v.getFileImg());
        assertEquals(true, v.isNewVehicle());
        assertEquals(true, v.isFeatured());
        assertEquals(false, v.isSold());
         
    }
    @Test
    public void testUpdate1() {
        // Arrange
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPassword("password");
        user.setRole("Admin");
        User createdUser = userDao.create(user);
        
        
        Make make = new Make();
        make.setName("name");
        make.setDateAdded(LocalDate.now());
        make.setUser(createdUser);
        Make createdMake = makeDao.create(make);
        
        Model model = new Model();
        model.setName("name");
        model.setDateAdded(LocalDate.now());
        model.setUser(createdUser);
        model.setMake(createdMake);
        Model createdModel = modelDao.create(model);

        
        Vehicle vehicle = new Vehicle();
        
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
        
        // Act
        Vehicle v = vehicleDao.create(vehicle);
        
        v.setColor("newColor");
        vehicleDao.update(v);
        Vehicle createdVehicle = vehicleDao.read(v.getId());
        
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
        assertEquals("interior", v.getInterior());
        assertEquals("trans", v.getTrans());
        assertEquals("newColor", v.getColor());
        assertEquals("vin", v.getVin());
        assertEquals("description", v.getDescription());
        assertEquals("fileImg", v.getFileImg());
        assertEquals(true, v.isNewVehicle());
        assertEquals(true, v.isFeatured());
        assertEquals(false, v.isSold());     
    }
    @Test
    public void testUpdateMultiple() {
        // Arrange
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPassword("password");
        user.setRole("Admin");
        User createdUser = userDao.create(user);
        
        
        Make make = new Make();
        make.setName("name");
        make.setDateAdded(LocalDate.now());
        make.setUser(createdUser);
        Make createdMake = makeDao.create(make);
        
        Model model = new Model();
        model.setName("name");
        model.setDateAdded(LocalDate.now());
        model.setUser(createdUser);
        model.setMake(createdMake);
        Model createdModel = modelDao.create(model);

        
        Vehicle vehicle = new Vehicle();
        
        
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
        
        // Act
        Vehicle v = vehicleDao.create(vehicle);
        
        v.setStyle("newStyle");
        v.setDescription("newDescription");
        v.setSold(true);
        vehicleDao.update(v);
        Vehicle createdVehicle = vehicleDao.read(v.getId());
        
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
        assertEquals("interior", v.getInterior());
        assertEquals("trans", v.getTrans());
        assertEquals("color", v.getColor());
        assertEquals("vin", v.getVin());
        assertEquals("newDescription", v.getDescription());
        assertEquals("fileImg", v.getFileImg());
        assertEquals(true, v.isNewVehicle());
        assertEquals(true, v.isFeatured());
        assertEquals(true, v.isSold());
    }
    @Test
    public void testReadAll() {
        // Arrange
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPassword("password");
        user.setRole("Admin");
        User createdUser = userDao.create(user);
        
        
        Make make = new Make();
        make.setName("name");
        make.setDateAdded(LocalDate.now());
        make.setUser(createdUser);
        Make createdMake = makeDao.create(make);
        
        Model model = new Model();
        model.setName("name");
        model.setDateAdded(LocalDate.now());
        model.setUser(createdUser);
        model.setMake(createdMake);
        Model createdModel = modelDao.create(model);
        
        Vehicle vehicle = new Vehicle();

        vehicle.setMake(createdMake);
        vehicle.setModel(createdModel);
        vehicle.setUser(createdUser);
        
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
        
        
        
        
        User user2 = new User();
        user2.setFirstName("firstName");
        user2.setLastName("lastName");
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setRole("");
        User createdUser2 = userDao.create(user);
        
        
        Make make2 = new Make();
        make2.setName("name");
        make2.setDateAdded(LocalDate.now());
        make2.setUser(createdUser);
        Make createdMake2 = makeDao.create(make);
        
        Model model2 = new Model();
        model2.setName("name");
        model2.setDateAdded(LocalDate.now());
        model2.setUser(createdUser);
        model2.setMake(createdMake);
        Model createdModel2 = modelDao.create(model);
  
        Vehicle vehicle2 = new Vehicle();

        vehicle2.setMake(createdMake2);
        vehicle2.setModel(createdModel2);
        vehicle2.setUser(createdUser2);
        
        vehicle2.setYear(2002);
        vehicle2.setMileage(1002);
        vehicle2.setSalePrice(new BigDecimal("2"));
        vehicle2.setMsrp(new BigDecimal("2"));
        vehicle2.setStyle("style2");
        vehicle2.setInterior("interior2");
        vehicle2.setTrans("trans2");
        vehicle2.setColor("color2");
        vehicle2.setVin("vin2");
        vehicle2.setDescription("description2");
        vehicle2.setFileImg("fileImg2");
        vehicle2.setNewVehicle(true);
        vehicle2.setFeatured(true);
        vehicle2.setSold(false);
        
        // Act
        Vehicle v = vehicleDao.create(vehicle);
        Vehicle v2 = vehicleDao.create(vehicle2);
        
        Vehicle createdVehicle = vehicleDao.read(v.getId());
        Vehicle createdVehicle2 = vehicleDao.read(v2.getId());
        
        List<Vehicle> vehicles = vehicleDao.readAll();
        
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
        assertEquals(0, BigDecimal.ONE.compareTo(v.getSalePrice()));
        assertEquals(0, BigDecimal.ONE.compareTo(v.getMsrp()));
        assertEquals("style", v.getStyle());
        assertEquals("interior", v.getInterior());
        assertEquals("trans", v.getTrans());
        assertEquals("color", v.getColor());
        assertEquals("vin", v.getVin());
        assertEquals("description", v.getDescription());
        assertEquals("fileImg", v.getFileImg());
        assertEquals(true, v.isNewVehicle());
        assertEquals(true, v.isFeatured());
        assertEquals(false, v.isSold());
        
        
        assertEquals(createdVehicle2.getId(), v2.getId());
        assertEquals(createdMake2.getId(), v2.getMake().getId());
        assertEquals(createdModel2.getId(), v2.getModel().getId());
        assertEquals(createdUser2.getId(), v2.getUser().getId());
        assertEquals((Integer)2002, v2.getYear());
        assertEquals((Integer)1002, v2.getMileage());
        assertEquals(0, new BigDecimal("2").compareTo(v2.getSalePrice()));
        assertEquals(0, new BigDecimal("2").compareTo(v2.getMsrp()));
        assertEquals("style2", v2.getStyle());
        assertEquals("interior2", v2.getInterior());
        assertEquals("trans2", v2.getTrans());
        assertEquals("color2", v2.getColor());
        assertEquals("vin2", v2.getVin());
        assertEquals("description2", v2.getDescription());
        assertEquals("fileImg2", v2.getFileImg());
        assertEquals(true, v2.isNewVehicle());
        assertEquals(true, v2.isFeatured());
        assertEquals(false, v2.isSold());
        
    }
    @Test
    public void testSearch(){
        // Arrange
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPassword("password");
        user.setRole("Admin");
        User createdUser = userDao.create(user);
        
        
        Make make = new Make();
        make.setName("make");
        make.setDateAdded(LocalDate.now());
        make.setUser(createdUser);
        Make createdMake = makeDao.create(make);
        
        Model model = new Model();
        model.setName("model");
        model.setDateAdded(LocalDate.now());
        model.setUser(createdUser);
        model.setMake(createdMake);
        Model createdModel = modelDao.create(model);
        
        Vehicle vehicle = new Vehicle();

        vehicle.setMake(createdMake);
        vehicle.setModel(createdModel);
        vehicle.setUser(createdUser);
        
        vehicle.setYear(2000);
        vehicle.setMileage(1000);
        vehicle.setSalePrice(new BigDecimal("1000"));
        vehicle.setMsrp(new BigDecimal("1000"));
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
        
        
        
        
        User user2 = new User();
        user2.setFirstName("firstName");
        user2.setLastName("lastName");
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setRole("Admin");
        User createdUser2 = userDao.create(user);
        
        
        Make make2 = new Make();
        make2.setName("otherMake");
        make2.setDateAdded(LocalDate.now());
        make2.setUser(createdUser);
        Make createdMake2 = makeDao.create(make2);
        
        Model model2 = new Model();
        model2.setName("otherModel");
        model2.setDateAdded(LocalDate.now());
        model2.setUser(createdUser);
        model2.setMake(createdMake);
        Model createdModel2 = modelDao.create(model2);
  
        Vehicle vehicle2 = new Vehicle();

        vehicle2.setMake(createdMake2);
        vehicle2.setModel(createdModel2);
        vehicle2.setUser(createdUser2);
        
        vehicle2.setYear(2002);
        vehicle2.setMileage(1002);
        vehicle2.setSalePrice(new BigDecimal("2000"));
        vehicle2.setMsrp(new BigDecimal("2000"));
        vehicle2.setStyle("style2");
        vehicle2.setInterior("interior2");
        vehicle2.setTrans("trans2");
        vehicle2.setColor("color2");
        vehicle2.setVin("vin2");
        vehicle2.setDescription("description2");
        vehicle2.setFileImg("fileImg2");
        vehicle2.setNewVehicle(true);
        vehicle2.setFeatured(true);
        vehicle2.setSold(false);
        
        
        // Act
        Vehicle v = vehicleDao.create(vehicle);
        Vehicle v2 = vehicleDao.create(vehicle2);
        
        List<Vehicle> vehicles = vehicleDao.readAll();
        List<Vehicle> searchedVehicles = vehicleDao.search("2000", true);
        
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
        assertEquals(0, new BigDecimal("1000").compareTo(foundVehicle.getSalePrice()));
        assertEquals(0, new BigDecimal("1000").compareTo(foundVehicle.getMsrp()));
        assertEquals("style", foundVehicle.getStyle());
        assertEquals("interior", foundVehicle.getInterior());
        assertEquals("trans", foundVehicle.getTrans());
        assertEquals("color", foundVehicle.getColor());
        assertEquals("vin", foundVehicle.getVin());
        assertEquals("description", foundVehicle.getDescription());
        assertEquals("fileImg", foundVehicle.getFileImg());
        assertEquals(true, foundVehicle.isNewVehicle());
        assertEquals(true, foundVehicle.isFeatured());
        assertEquals(false, foundVehicle.isSold());
        
        searchedVehicles = vehicleDao.search("make", true);
        
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
        assertEquals("interior", foundVehicle.getInterior());
        assertEquals("trans", foundVehicle.getTrans());
        assertEquals("color", foundVehicle.getColor());
        assertEquals("vin", foundVehicle.getVin());
        assertEquals("description", foundVehicle.getDescription());
        assertEquals("fileImg", foundVehicle.getFileImg());
        assertEquals(true, foundVehicle.isNewVehicle());
        assertEquals(true, foundVehicle.isFeatured());
        assertEquals(false, foundVehicle.isSold());
    }
}
