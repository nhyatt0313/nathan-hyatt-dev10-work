/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import com.sg.spaceshipdealership.dao.MakeDao;
import com.sg.spaceshipdealership.dao.ModelDao;
import com.sg.spaceshipdealership.dao.UserDao;
import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.dto.Model;
import com.sg.spaceshipdealership.dto.User;

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

/**
 *
 * @author nhyat
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class ModelDaoTest {
    
    @Autowired
    ModelDao modelDao; 
    
    @Autowired
    MakeDao makeDao;
    
    @Autowired
    UserDao userDao;
    
    User user = new User();
    Make make = new Make();
    User user1 = new User();
    Make make1 = new Make();
    
    @Before
    public void setUp() {
        user.setFirstName("John");
        user.setLastName("Johnson");
        user.setEmail("example@example.com");
        user.setRole("Admin");
        user.setPassword("password");
        user = userDao.create(user);
        
        make.setName("Ford");
        make.setDateAdded(LocalDate.parse("2001-05-05"));
        make.setUser(user);
        make = makeDao.create(make);
        
        user1.setFirstName("John");
        user1.setLastName("Johnson");
        user1.setEmail("example@example.com");
        user1.setRole("Admin");
        user1.setPassword("password");
        user1 = userDao.create(user1);
        
        make1.setName("Ford");
        make1.setDateAdded(LocalDate.parse("2001-05-05"));
        make1.setUser(user);
        make1 = makeDao.create(make1);
    }
    
    @Test
    public void testCreateAndRead() {      
        
        Model model = new Model();
        model.setName("Focus");
        model.setDateAdded(LocalDate.parse("2015-05-05"));
        model.setUser(user);
        model.setMake(make);
       
        model = modelDao.create(model);
        
        Model fromDao = modelDao.read(model.getId());
        assertEquals(fromDao.getName(), "Focus");
        assertEquals(fromDao.getDateAdded(), LocalDate.parse("2015-05-05"));
        assertEquals(fromDao.getUser().getId(), user.getId());
        assertEquals(fromDao.getMake().getId(), make.getId());
    }
    
    @Test
    public void testUpdate() {
        
        Model model = new Model();
        model.setName("Focus");
        model.setDateAdded(LocalDate.parse("2015-05-05"));
        model.setUser(user);
        model.setMake(make);
        
        model = modelDao.create(model);
        
        model.setName("Ranger");
        model.setDateAdded(LocalDate.parse("2016-06-06"));
        model.setUser(user1);
        model.setMake(make1);
        
        modelDao.update(model);
        
        Model fromDao = modelDao.read(model.getId());
        
        assertEquals(model.getName(), "Ranger");
        assertEquals(model.getDateAdded(), LocalDate.parse("2016-06-06"));
        
        assertEquals(model.getUser().getId(), user1.getId());
        assertEquals(model.getMake().getId(), make1.getId());
    }
   
    @Test
    public void testReadList() {
        
        Model model = new Model();
        model.setName("Focus");
        model.setDateAdded(LocalDate.parse("2015-05-05"));
        model.setUser(user);
        model.setMake(make);
        
        model = modelDao.create(model);
        
        Model model1 = new Model();
        model1.setName("Focus");
        model1.setDateAdded(LocalDate.parse("2015-05-05"));
        model1.setUser(user);
        model1.setMake(make);
        
        model1 = modelDao.create(model1);
        
        List<Model> list = modelDao.readAll();
        
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), model.getId());
        assertEquals(list.get(1).getId(), model1.getId());
        
    }
    
}