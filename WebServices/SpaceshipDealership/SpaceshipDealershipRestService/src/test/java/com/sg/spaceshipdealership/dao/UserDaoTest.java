/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import com.sg.spaceshipdealership.dao.UserDao;
import com.sg.spaceshipdealership.dto.User;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    public UserDaoTest() {
        
    }

    @Test
    public void testGetUserById() {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userDao.create(newUser);

        // Assert
        User fetchedUser = userDao.read(createdUser.getId());
        assertEquals(fetchedUser.getFirstName(), "John");
        assertEquals(fetchedUser.getLastName(), "Hopkins");
        assertEquals(fetchedUser.getEmail(), "john@example.com");
        assertEquals(fetchedUser.getRole(), "Admin");
        assertEquals(fetchedUser.getPassword(), "eeeeeeee");

    }

    @Test
    public void testGetAllUsers() {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        User user = userDao.create(newUser);

        User newUser2 = new User();
        newUser2.setFirstName("Bob");
        newUser2.setLastName("Hopkins");
        newUser2.setEmail("bob@example.com");
        newUser2.setRole("Admin");
        newUser2.setPassword("mmmmmmmm");

        User user2 = userDao.create(newUser2);

        User newUser3 = new User();
        newUser3.setFirstName("Carl");
        newUser3.setLastName("Hopkins");
        newUser3.setEmail("carl@example.com");
        newUser3.setRole("Admin");
        newUser3.setPassword("eeeeeeee");

        User user3 = userDao.create(newUser3);

        // Act
        List<User> userList = userDao.readAll();

        // Assert
        assertEquals(userList.size(), 3);
        assertEquals(userList.get(0).getId(), user.getId());
        assertEquals(userList.get(1).getId(), user2.getId());
        assertEquals(userList.get(2).getId(), user3.getId());

    }

    @Test
    public void testCreateUser() {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userDao.create(newUser);

        // Assert
        User fetchedUser = userDao.read(createdUser.getId());
        assertEquals(fetchedUser.getFirstName(), "John");
        assertEquals(fetchedUser.getLastName(), "Hopkins");
        assertEquals(fetchedUser.getEmail(), "john@example.com");
        assertEquals(fetchedUser.getRole(), "Admin");
        assertEquals(fetchedUser.getPassword(), "eeeeeeee");
    }

    @Test
    public void testUpdateUser() {

        // Arrange 
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");

        // Act
        User createdUser = userDao.create(newUser);
        createdUser.setFirstName("Jack");
        createdUser.setLastName("Sparrow");
        createdUser.setEmail("jack@example.com");
        createdUser.setRole("Admin");
        createdUser.setPassword("Reeeeeee");

        userDao.update(createdUser);

        // Assert
        User fetchedUser = userDao.read(createdUser.getId());
        assertEquals(fetchedUser.getFirstName(), "Jack");
        assertEquals(fetchedUser.getLastName(), "Sparrow");
        assertEquals(fetchedUser.getEmail(), "jack@example.com");
        assertEquals(fetchedUser.getRole(), "Admin");
        assertEquals(fetchedUser.getPassword(), "Reeeeeee");

    }

    @Test
    public void testDeleteUser() {

        // Arrange
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Hopkins");
        newUser.setEmail("john@example.com");
        newUser.setRole("Admin");
        newUser.setPassword("eeeeeeee");
        
        // Act
        User createdUser = userDao.create(newUser);
        userDao.delete(createdUser.getId());
        
        // Assert
        User fetchedUser = userDao.read(createdUser.getId());
        assertNull(fetchedUser);

    }

}
