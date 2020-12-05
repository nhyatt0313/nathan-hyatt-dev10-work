/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import com.sg.spaceshipdealership.dao.MakeDao;
import com.sg.spaceshipdealership.dao.UserDao;
import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.dto.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class MakeDaoTest {

    @Autowired
    MakeDao makeDao;

    @Autowired
    UserDao userDao;

    public MakeDaoTest() {
    }

    @Test
    public void testCreateMake() {

        // Arrange
        User user = new User();
        user.setFirstName("john");
        user.setLastName("wick");
        user.setEmail("wick@example.com");
        user.setPassword("hjhjhjhj");
        user.setRole("Admin");
        user = userDao.create(user);

        // System.out.println("Should stop here");
        Make newMake = new Make();
        newMake.setName("Tesla");
        newMake.setDateAdded(LocalDate.parse("2017-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        newMake.setUser(user);

        // Act
        Make createdMake = makeDao.create(newMake);

        // Assert
        Make fetchedMake = makeDao.read(createdMake.getId());
        assertEquals(fetchedMake.getName(), "Tesla");
        assertEquals(fetchedMake.getDateAdded(), LocalDate.parse("2017-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertEquals(fetchedMake.getUser().getId(), user.getId());

    }

    @Test
    public void testGetMakeById() {

        // Arrange
        User user = new User();
        user.setFirstName("john");
        user.setLastName("wick");
        user.setEmail("wick@example.com");
        user.setPassword("hjhjhjhj");
        user.setRole("Admin");
        user = userDao.create(user);

        Make newMake = new Make();
        newMake.setName("Tesla");
        newMake.setDateAdded(LocalDate.parse("2017-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        newMake.setUser(user);

        // Act
        Make createdMake = makeDao.create(newMake);

        // Assert
        Make fetchedMake = makeDao.read(createdMake.getId());
        assertEquals(fetchedMake.getName(), "Tesla");
        assertEquals(fetchedMake.getDateAdded(), LocalDate.parse("2017-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertEquals(fetchedMake.getUser().getId(), user.getId());

    }

    @Test
    public void testGetAllMakes() {

        // Arrange
        User user1 = new User();
        user1.setFirstName("john");
        user1.setLastName("wick");
        user1.setEmail("wick@example.com");
        user1.setPassword("hjhjhjhj");
        user1.setRole("Admin");
        user1 = userDao.create(user1);

        Make newMake1 = new Make();
        newMake1.setName("Tesla1");
        newMake1.setDateAdded(LocalDate.parse("2017-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        newMake1.setUser(user1);

        userDao.create(user1);
        makeDao.create(newMake1);

        User user2 = new User();
        user2.setFirstName("john");
        user2.setLastName("wick");
        user2.setEmail("wick@example.com");
        user2.setPassword("hjhjhjhj");
        user2.setRole("Admin");
        user2 = userDao.create(user2);

        Make newMake2 = new Make();
        newMake2.setName("Tesla2");
        newMake2.setDateAdded(LocalDate.parse("2017-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        newMake2.setUser(user2);

        userDao.create(user2);
        makeDao.create(newMake2);

        User user3 = new User();
        user3.setFirstName("john");
        user3.setLastName("wick");
        user3.setEmail("wick@example.com");
        user3.setPassword("hjhjhjhj");
        user3.setRole("Admin");
        user3 = userDao.create(user3);

        Make newMake3 = new Make();
        newMake3.setName("Tesla3");
        newMake3.setDateAdded(LocalDate.parse("2017-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        newMake3.setUser(user3);

        userDao.create(user3);
        makeDao.create(newMake3);

        // Act
        List<Make> listMakes = makeDao.readAll();

        // Assert
        assertEquals(listMakes.size(), 3);
        assertEquals(listMakes.get(0).getId(), newMake1.getId());
        assertEquals(listMakes.get(1).getId(), newMake2.getId());
        assertEquals(listMakes.get(2).getId(), newMake3.getId());

    }

    @Test
    public void testUpdateMake() {

        // Arrange
        User user1 = new User();
        user1.setFirstName("john");
        user1.setLastName("wick");
        user1.setEmail("wick@example.com");
        user1.setPassword("hjhjhjhj");
        user1.setRole("Admin");
        user1 = userDao.create(user1);

        Make newMake1 = new Make();
        newMake1.setName("Tesla1");
        newMake1.setDateAdded(LocalDate.parse("2017-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        newMake1.setUser(user1);

        // Act
        Make createdMake = makeDao.create(newMake1);
        createdMake.setName("BMW");
        createdMake.setDateAdded(LocalDate.parse("2017-05-06", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        createdMake.setUser(user1);

        makeDao.update(createdMake);

        // Assert
        Make fetchedMake = makeDao.read(createdMake.getId());
        assertEquals(fetchedMake.getName(), "BMW");
        assertEquals(fetchedMake.getDateAdded(), LocalDate.parse("2017-05-06", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertEquals(fetchedMake.getUser().getId(), user1.getId());

    }

}

