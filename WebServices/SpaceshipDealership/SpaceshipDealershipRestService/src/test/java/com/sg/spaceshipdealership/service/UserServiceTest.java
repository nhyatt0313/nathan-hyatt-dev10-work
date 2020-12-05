/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.spaceshipdealership.dto.User;
import com.sg.spaceshipdealership.service.DoesNotExistException;
import com.sg.spaceshipdealership.service.InvalidEntryException;
import com.sg.spaceshipdealership.service.UserService;

/**
 *
 * @author nhyat
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;

    public UserServiceTest() {

    }

    @Test
    public void testRead() throws DoesNotExistException, InvalidEntryException {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userService.create(newUser);

        // Assert
        User fetchedUser = userService.read(createdUser.getId());
        assertEquals(fetchedUser.getFirstName(), "John");
        assertEquals(fetchedUser.getLastName(), "Hopkins");
        assertEquals(fetchedUser.getEmail(), "john@example.com");
        assertEquals(fetchedUser.getRole(), "Admin");
        assertEquals(fetchedUser.getPassword(), "eeeeeeee");

    }

    @Test
    public void testReadNotThere() throws Exception {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userService.create(newUser);

        // Assert
        try {
            User fetchedUser = userService.read(createdUser.getId() - 1);
            Assert.fail("Did not throw DoesNotExistException");
        } catch (DoesNotExistException ex) {
        }

    }

    @Test
    public void testReadAll() throws InvalidEntryException {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        User user = userService.create(newUser);

        User newUser2 = new User();
        newUser2.setFirstName("Bob");
        newUser2.setLastName("Hopkins");
        newUser2.setEmail("bob@example.com");
        newUser2.setRole("Admin");
        newUser2.setPassword("mmmmmmmm");

        User user2 = userService.create(newUser2);

        User newUser3 = new User();
        newUser3.setFirstName("Carl");
        newUser3.setLastName("Hopkins");
        newUser3.setEmail("carl@example.com");
        newUser3.setRole("Admin");
        newUser3.setPassword("eeeeeeee");

        User user3 = userService.create(newUser3);

        // Act
        List<User> userList = userService.readAll();

        // Assert
        assertEquals(userList.size(), 3);
        assertEquals(userList.get(0).getId(), user.getId());
        assertEquals(userList.get(1).getId(), user2.getId());
        assertEquals(userList.get(2).getId(), user3.getId());

    }

    @Test
    public void testReadAllAlotMore() throws Exception {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        User user = userService.create(newUser);

        User newUser2 = new User();
        newUser2.setFirstName("Bob");
        newUser2.setLastName("Hopkins");
        newUser2.setEmail("bob@example.com");
        newUser2.setRole("Admin");
        newUser2.setPassword("mmmmmmmm");

        User user2 = userService.create(newUser2);

        User newUser3 = new User();
        newUser3.setFirstName("Carl");
        newUser3.setLastName("Hopkins");
        newUser3.setEmail("carl@example.com");
        newUser3.setRole("Sales");
        newUser3.setPassword("eeeeeeee");

        User user3 = userService.create(newUser3);

        User newUser4 = new User();
        newUser4.setFirstName("John");
        newUser4.setLastName("Hopkins");
        newUser4.setEmail("john@example.com");
        newUser4.setRole("Admin");
        newUser4.setPassword("eeeeeeee");

        User user4 = userService.create(newUser4);

        User newUser5 = new User();
        newUser5.setFirstName("Lee");
        newUser5.setLastName("Hopkins");
        newUser5.setEmail("leohn@example.com");
        newUser5.setRole("Admin");
        newUser5.setPassword("eeeeeeee");

        User user5 = userService.create(newUser5);

        User newUser6 = new User();
        newUser6.setFirstName("John");
        newUser6.setLastName("Hopkins");
        newUser6.setEmail("john@example.com");
        newUser6.setRole("Disabled");
        newUser6.setPassword("eeeeeeee");

        User user6 = userService.create(newUser6);

        User newUser7 = new User();
        newUser7.setFirstName("Sue");
        newUser7.setLastName("Hopkins");
        newUser7.setEmail("john@example.com");
        newUser7.setRole("Sales");
        newUser7.setPassword("eeeeeeee");

        User user7 = userService.create(newUser7);

        User newUser8 = new User();
        newUser8.setFirstName("Jake");
        newUser8.setLastName("Hopkins");
        newUser8.setEmail("john@example.com");
        newUser8.setRole("Disabled");
        newUser8.setPassword("eeeeeeee");

        User user8 = userService.create(newUser8);

        User newUser9 = new User();
        newUser9.setFirstName("Mike");
        newUser9.setLastName("Hopkins");
        newUser9.setEmail("john@example.com");
        newUser9.setRole("Admin");
        newUser9.setPassword("eeeeeeee");

        User user9 = userService.create(newUser9);

        User newUser10 = new User();
        newUser10.setFirstName("France");
        newUser10.setLastName("Hopkins");
        newUser10.setEmail("john@example.com");
        newUser10.setRole("Disabled");
        newUser10.setPassword("eeeeeeee");

        User user10 = userService.create(newUser10);
        // Act
        List<User> userList = userService.readAll();

        // Assert
        assertEquals(userList.size(), 10);
        assertEquals(userList.get(0).getId(), user.getId());
        assertEquals(userList.get(1).getId(), user2.getId());
        assertEquals(userList.get(2).getId(), user3.getId());
        assertEquals(userList.get(3).getId(), user4.getId());
        assertEquals(userList.get(4).getId(), user5.getId());
        assertEquals(userList.get(5).getId(), user6.getId());
        assertEquals(userList.get(6).getId(), user7.getId());
        assertEquals(userList.get(7).getId(), user8.getId());
        assertEquals(userList.get(8).getId(), user9.getId());
        assertEquals(userList.get(9).getId(), user10.getId());

    }

    @Test
    public void testGetAllUsersEmpty() throws Exception {
        // Arrange
        //empty list

        // Act
        List<User> userList = userService.readAll();

        // Assert
        assertEquals(userList.size(), 0);

    }

    @Test
    public void testCreate() throws DoesNotExistException, InvalidEntryException {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userService.create(newUser);

        // Assert
        User fetchedUser = userService.read(createdUser.getId());
        assertEquals(fetchedUser.getFirstName(), "John");
        assertEquals(fetchedUser.getLastName(), "Hopkins");
        assertEquals(fetchedUser.getEmail(), "john@example.com");
        assertEquals(fetchedUser.getRole(), "Admin");
        assertEquals(fetchedUser.getPassword(), "eeeeeeee");
    }

    @Test
    public void testCreateInvalidFirstName() throws Exception {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        User newUser2 = new User();
        //newUser2.setFirstName();
        newUser2.setLastName("Hopkins");
        newUser2.setEmail("john@example.com");
        newUser2.setRole("Admin");
        newUser2.setPassword("eeeeeeee");
        // Act

        // Assert
        try {
            User createdUser = userService.create(newUser);
            Assert.fail("Did not throw Empty InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }

        try {
            User createdUser2 = userService.create(newUser2);
            Assert.fail("Did not throw Null InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }
    }

    @Test
    public void testCreateInvalidLastName() throws Exception {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        User newUser2 = new User();
        newUser2.setFirstName("John");
        //newUser2.setLastName("Hopkins");
        newUser2.setEmail("john@example.com");
        newUser2.setRole("Admin");
        newUser2.setPassword("eeeeeeee");
        // Act

        // Assert
        try {
            User createdUser = userService.create(newUser);
            Assert.fail("Did not throw Empty InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }

        try {
            User createdUser2 = userService.create(newUser2);
            Assert.fail("Did not throw Null InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }
    }

    @Test
    public void testCreateInvalidEmailName() throws Exception {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        User newUser2 = new User();
        newUser2.setFirstName("John");
        newUser2.setLastName("Hopkins");
        //newUser2.setEmail("john@example.com");
        newUser2.setRole("Admin");
        newUser2.setPassword("eeeeeeee");
        // Act

        // Assert
        try {
            User createdUser = userService.create(newUser);
            Assert.fail("Did not throw Empty InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }

        try {
            User createdUser2 = userService.create(newUser2);
            Assert.fail("Did not throw Null InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }
    }

    @Test
    public void testCreateInvalidRole() throws Exception {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("");
        newUser.setPassword("eeeeeeee");

        User newUser2 = new User();
        newUser2.setFirstName("John");
        newUser2.setLastName("Hopkins");
        newUser2.setEmail("john@example.com");
        //newUser2.setRole("Admin");
        newUser2.setPassword("eeeeeeee");
        // Act

        // Assert
        try {
            User createdUser = userService.create(newUser);
            Assert.fail("Did not throw Empty InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }

        try {
            User createdUser2 = userService.create(newUser2);
            Assert.fail("Did not throw Null InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }
    }

    @Test
    public void testCreateInvalidPassword() throws Exception {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("");

        User newUser2 = new User();
        newUser2.setFirstName("John");
        newUser2.setLastName("Hopkins");
        newUser2.setEmail("john@example.com");
        newUser2.setRole("Admin");
        //newUser2.setPassword("eeeeeeee");
        // Act

        // Assert
        try {
            User createdUser = userService.create(newUser);
            Assert.fail("Did not throw Empty InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }

        try {
            User createdUser2 = userService.create(newUser2);
            Assert.fail("Did not throw Null InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }
    }

    @Test
    public void testUpdateUser() throws DoesNotExistException, InvalidEntryException {

        // Arrange 
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userService.create(newUser);
        createdUser.setFirstName("Jack");
        createdUser.setLastName("Sparrow");
        createdUser.setEmail("jack@example.com");
        newUser.setRole("Admin");

        userService.update(createdUser);

        // Assert
        User fetchedUser = userService.read(createdUser.getId());
        assertEquals(fetchedUser.getFirstName(), "Jack");
        assertEquals(fetchedUser.getLastName(), "Sparrow");
        assertEquals(fetchedUser.getEmail(), "jack@example.com");
        assertEquals(fetchedUser.getRole(), "Admin");
        assertEquals(fetchedUser.getPassword(), "eeeeeeee");

    }

    @Test
    public void testUpdateUserInvalidFirstName() throws Exception {

        // Arrange 
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userService.create(newUser);
        createdUser.setFirstName("");
        createdUser.setLastName("Sparrow");
        createdUser.setEmail("jack@example.com");
        createdUser.setRole("Admin");
        createdUser.setPassword("Reeeeeee");

        // Assert
        try {
            userService.update(createdUser);
            Assert.fail("Did not throw InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }

    }

    @Test
    public void testUpdateUserInvalidLastName() throws Exception {

        // Arrange 
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userService.create(newUser);
        createdUser.setFirstName("Spin");
        createdUser.setLastName("");
        createdUser.setEmail("jack@example.com");
        createdUser.setRole("Admin");
        createdUser.setPassword("Reeeeeee");

        // Assert
        try {
            userService.update(createdUser);
            Assert.fail("Did not throw InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }

    }

    @Test
    public void testUpdateUserInvalidEmail() throws Exception {

        // Arrange 
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userService.create(newUser);
        createdUser.setFirstName("");
        createdUser.setLastName("Sparrow");
        createdUser.setEmail("");
        createdUser.setRole("Admin");
        createdUser.setPassword("Reeeeeee");

        // Assert
        try {
            userService.update(createdUser);
            Assert.fail("Did not throw InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }

    }

    @Test
    public void testUpdateUserInvalidRole() throws Exception {

        // Arrange 
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userService.create(newUser);
        createdUser.setFirstName("");
        createdUser.setLastName("Sparrow");
        createdUser.setEmail("jack@example.com");
        createdUser.setRole("");
        createdUser.setPassword("Reeeeeee");

        // Assert
        try {
            userService.update(createdUser);
            Assert.fail("Did not throw InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }

    }

    @Test
    public void testUpdateUserInvalidPassword() throws Exception {

        // Arrange 
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userService.create(newUser);
        createdUser.setFirstName("");
        createdUser.setLastName("Sparrow");
        createdUser.setEmail("jack@example.com");
        createdUser.setRole("Admin");
        createdUser.setPassword("");

        // Assert
        try {
            userService.update(createdUser);
            Assert.fail("Did not throw InvalidEntryException");
        } catch (InvalidEntryException ex) {
        }

    }

    @Test
    public void testUpdateUserReplaceNone() throws Exception {

        // Arrange 
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userService.create(newUser);

        userService.update(createdUser);

        // Assert
        User fetchedUser = userService.read(createdUser.getId());
        assertEquals(fetchedUser.getFirstName(), "John");
        assertEquals(fetchedUser.getLastName(), "Hopkins");
        assertEquals(fetchedUser.getEmail(), "john@example.com");
        assertEquals(fetchedUser.getRole(), "Admin");
        assertEquals(fetchedUser.getPassword(), "eeeeeeee");

    }

    @Test
    public void testDeleteUser() throws DoesNotExistException, InvalidEntryException {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userService.create(newUser);
        userService.delete(createdUser.getId());

        // Assert
        try {
            User fetchedUser = userService.read(createdUser.getId());
            fail("Should have thrown an exception - no longer exist");
        } catch (DoesNotExistException e) {
        }

    }

}
