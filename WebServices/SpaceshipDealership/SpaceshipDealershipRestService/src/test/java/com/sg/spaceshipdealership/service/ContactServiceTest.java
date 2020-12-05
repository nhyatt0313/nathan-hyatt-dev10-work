/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

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

import com.sg.spaceshipdealership.dto.Contact;
import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.dto.Model;
import com.sg.spaceshipdealership.dto.User;
import com.sg.spaceshipdealership.dto.Vehicle;
import com.sg.spaceshipdealership.service.ContactService;
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
public class ContactServiceTest {
    
    @Autowired
    ContactService contactService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    MakeService makeService;
    
    @Autowired
    ModelService modelService;
    
    @Autowired
    VehicleService vehicleService;
    
    
    public ContactServiceTest() {
    }
    
    User user = new User();
    Model model = new Model();
    Make make = new Make();
    Vehicle vehicle = new Vehicle();
    Vehicle vehicle1 = new Vehicle();
    

   @Test
    public void testCreateAndRead() throws DoesNotExistException, InvalidEntryException {
        
        Contact contact = new Contact();
        contact.setName("Travis");
        contact.setName("Morris");
        contact.setPhone("2343332323");
        contact.setMessage("Hello");
        contact.setEmail("test@test.com");
        
        contact = contactService.create(contact);
        
        Contact fromService = contactService.read(contact.getId());
        
        assertEquals(contact.getName(), fromService.getName());
        assertEquals(contact.getName(), fromService.getName());
        assertEquals(contact.getName(), fromService.getName());
        assertEquals(contact.getPhone(), fromService.getPhone());
        assertEquals(contact.getEmail(), fromService.getEmail());
        assertEquals(contact.getMessage(), fromService.getMessage());
        
    }
    

    @Test
    public void testReadList() throws InvalidEntryException {
        
        Contact contact = new Contact();
        contact.setName("Travis");
        contact.setName("Morris");
        contact.setPhone("2343332323");
        contact.setMessage("Hello");
        contact.setEmail("test@test.com");
        
        contact = contactService.create(contact);
        
        Contact contact1 = new Contact();
        contact1.setName("Travis");
        contact1.setName("Morris");
        contact1.setPhone("2343332323");
        contact1.setMessage("Hello");
        contact1.setEmail("test@test.com");
        
        contact1 = contactService.create(contact1);
        
        List<Contact> list = contactService.readAll();
        
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), contact.getId());
        assertEquals(list.get(1).getId(), contact1.getId());
    }

    @Test
    public void testUpdateContact() throws DoesNotExistException, InvalidEntryException {
        
        Contact contact = new Contact();
        contact.setName("Travis");
        contact.setName("Morris");
        contact.setPhone("2343332323");
        contact.setMessage("Hello");
        contact.setEmail("test@test.com");
        
        contact = contactService.create(contact);
        
        contact.setName("Travis2");
        contact.setName("Morris2");
        contact.setPhone("2343332320");
        contact.setMessage("Hello2");
        contact.setEmail("test@test.com2");
        
        
        contactService.update(contact);
        
        Contact fromService = contactService.read(contact.getId());
        
        assertEquals(contact.getName(), fromService.getName());
        assertEquals(contact.getName(), fromService.getName());
        assertEquals(contact.getName(), fromService.getName());
        assertEquals(contact.getPhone(), fromService.getPhone());
        assertEquals(contact.getEmail(), fromService.getEmail());
        assertEquals(contact.getMessage(), fromService.getMessage());
    }
    
    @Test
    public void testDeleteContact() throws DoesNotExistException, InvalidEntryException {
        
        Contact contact = new Contact();
        contact.setName("Travis");
        contact.setName("Morris");
        contact.setPhone("2343332323");
        contact.setMessage("Hello");
        contact.setEmail("test@test.com");
        
        contact = contactService.create(contact);
        
        contactService.delete(contact.getId());
        
        try {
            Contact fromService = contactService.read(contact.getId());
            fail("Should have thrown error - Doesn't exist anymore");
        } catch (DoesNotExistException e){
            
        }
        
    }
    
}