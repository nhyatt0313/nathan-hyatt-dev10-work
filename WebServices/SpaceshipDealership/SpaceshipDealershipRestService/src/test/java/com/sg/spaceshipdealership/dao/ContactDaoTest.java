/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import com.sg.spaceshipdealership.dao.ContactDao;
import com.sg.spaceshipdealership.dao.MakeDao;
import com.sg.spaceshipdealership.dao.ModelDao;
import com.sg.spaceshipdealership.dao.UserDao;
import com.sg.spaceshipdealership.dao.VehicleDao;
import com.sg.spaceshipdealership.dto.Contact;
import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.dto.Model;
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
public class ContactDaoTest {
    
    @Autowired
    ContactDao contactDao;
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    MakeDao makeDao;
    
    @Autowired
    ModelDao modelDao;
    
    @Autowired
    VehicleDao vehicleDao;
    
    
    public ContactDaoTest() {
    }
    
    User user = new User();
    Model model = new Model();
    Make make = new Make();
    Vehicle vehicle = new Vehicle();
    Vehicle vehicle1 = new Vehicle();
    
   @Test
    public void testCreateAndRead() {
        
        Contact contact = new Contact();
        contact.setName("Travis");
        contact.setName("Morris");
        contact.setPhone("2343332323");
        contact.setMessage("Hello");
        contact.setEmail("test@test.com");
        
        contact = contactDao.create(contact);
        
        Contact fromDao = contactDao.read(contact.getId());
        
        assertEquals(contact.getName(), fromDao.getName());
        assertEquals(contact.getName(), fromDao.getName());
        assertEquals(contact.getName(), fromDao.getName());
        assertEquals(contact.getPhone(), fromDao.getPhone());
        assertEquals(contact.getEmail(), fromDao.getEmail());
        assertEquals(contact.getMessage(), fromDao.getMessage());
    }
    

    @Test
    public void testReadList() {
        
        Contact contact = new Contact();
        contact.setName("Travis");
        contact.setName("Morris");
        contact.setPhone("2343332323");
        contact.setMessage("Hello");
        contact.setEmail("test@test.com");
        
        contact = contactDao.create(contact);
        
        Contact contact1 = new Contact();
        contact1.setName("Travis");
        contact1.setName("Morris");
        contact1.setPhone("2343332323");
        contact1.setMessage("Hello");
        contact1.setEmail("test@test.com");
        
        contact1 = contactDao.create(contact1);
        
        List<Contact> list = contactDao.readAll();
        
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), contact.getId());
        assertEquals(list.get(1).getId(), contact1.getId());
    }

    @Test
    public void testUpdateContact() {
        
        Contact contact = new Contact();
        contact.setName("Travis");
        contact.setName("Morris");
        contact.setPhone("2343332323");
        contact.setMessage("Hello");
        contact.setEmail("test@test.com");
        
        contact = contactDao.create(contact);
        
        contact.setName("Travis2");
        contact.setName("Morris2");
        contact.setPhone("2343332320");
        contact.setMessage("Hello2");
        contact.setEmail("test@test.com2");
        
        
        contactDao.update(contact);
        
        Contact fromDao = contactDao.read(contact.getId());
        
        assertEquals(contact.getName(), fromDao.getName());
        assertEquals(contact.getName(), fromDao.getName());
        assertEquals(contact.getName(), fromDao.getName());
        assertEquals(contact.getPhone(), fromDao.getPhone());
        assertEquals(contact.getEmail(), fromDao.getEmail());
        assertEquals(contact.getMessage(), fromDao.getMessage());
    }
    
    @Test
    public void testDeleteContact() {
        
        Contact contact = new Contact();
        contact.setName("Travis");
        contact.setName("Morris");
        contact.setPhone("2343332323");
        contact.setMessage("Hello");
        contact.setEmail("test@test.com");
        
        contact = contactDao.create(contact);
        
        contactDao.delete(contact.getId());
        
        Contact fromDao = contactDao.read(contact.getId());
        
        assertNull(fromDao);
    }
    
}