/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.assertEquals;
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
import com.sg.spaceshipdealership.service.DoesNotExistException;
import com.sg.spaceshipdealership.service.InvalidEntryException;
import com.sg.spaceshipdealership.service.MakeService;
import com.sg.spaceshipdealership.service.ModelService;
import com.sg.spaceshipdealership.service.UserService;

/**
 *
 * @author nhyat
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class ModelServiceTest {
    
    @Autowired
    ModelService modelService; 
    
    @Autowired
    MakeService makeService;
    
    @Autowired
    UserService userService;
    
    User user = new User();
    Make make = new Make();
    User user1 = new User();
    Make make1 = new Make();
    
    @Before
    public void setUp() throws InvalidEntryException, DoesNotExistException {
        user.setFirstName("John");
        user.setLastName("Johnson");
        user.setEmail("example@example.com");
        user.setRole("Admin");
        user.setPassword("password");
        user = userService.create(user);
        
        make.setName("Ford");
        make.setDateAdded(LocalDate.parse("2001-05-05"));
        make.setUser(user);
        make = makeService.create(make);
        
        user1.setFirstName("John");
        user1.setLastName("Johnson");
        user1.setEmail("example@example.com");
        user1.setRole("Admin");
        user1.setPassword("password");
        user1 = userService.create(user1);
        
        make1.setName("Ford");
        make1.setUser(user);
        make1 = makeService.create(make1);
    }
    
    @Test
    public void testCreateAndRead() throws DoesNotExistException, InvalidEntryException {      
        
        Model model = new Model();
        model.setName("Focus");
        model.setUser(user);
        model.setMake(make);
       
        model = modelService.create(model);
        
        Model fromService = modelService.read(model.getId());
        assertEquals(fromService.getName(), "Focus");
        assertEquals(fromService.getDateAdded(), LocalDate.now());
        assertEquals(fromService.getUser().getId(), user.getId());
        assertEquals(fromService.getMake().getId(), make.getId());
    }
    
    @Test
    public void testUpdate() throws DoesNotExistException, InvalidEntryException {
        
        Model model = new Model();
        model.setName("Focus");
        model.setUser(user);
        model.setMake(make);
        
        model = modelService.create(model);
        
        model.setName("Ranger");
        model.setDateAdded(LocalDate.parse("2016-06-06"));
        model.setUser(user1);
        model.setMake(make1);
        
        modelService.update(model);
        
        Model fromService = modelService.read(model.getId());
        
        assertEquals(model.getName(), "Ranger");
        assertEquals(model.getDateAdded(), LocalDate.now());
        
        assertEquals(model.getUser().getId(), user1.getId());
        assertEquals(model.getMake().getId(), make1.getId());
    }
   
    @Test
    public void testReadList() throws InvalidEntryException, DoesNotExistException {
        
        Model model = new Model();
        model.setName("Focus");
        model.setUser(user);
        model.setMake(make);
        
        model = modelService.create(model);
        
        Model model1 = new Model();
        model1.setName("Focus");
        model1.setUser(user);
        model1.setMake(make);
        
        model1 = modelService.create(model1);
        
        List<Model> list = modelService.readAll();
        
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), model.getId());
        assertEquals(list.get(1).getId(), model1.getId());
        
    }
    
   
    
    
    
}