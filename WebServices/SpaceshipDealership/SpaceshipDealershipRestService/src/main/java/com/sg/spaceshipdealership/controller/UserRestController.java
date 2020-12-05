/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.controller;

import com.sg.spaceshipdealership.dto.User;
import com.sg.spaceshipdealership.service.DoesNotExistException;
import com.sg.spaceshipdealership.service.InvalidEntryException;
import com.sg.spaceshipdealership.service.InvalidLoginException;
import com.sg.spaceshipdealership.service.UserService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nhyat
 */
@RestController
@RequestMapping("/user/admin")
public class UserRestController {

    @Autowired
    UserService service;

    @GetMapping("/alluser")
    @CrossOrigin(origins = "*")
    public List<User> allUser() {
        return service.readAll();
    }

    @GetMapping("/getuser/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        try {
            User user = service.read(id);
            return ResponseEntity.ok(user);
        } catch (DoesNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/adduser")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            user = service.create(user);
        } catch (InvalidEntryException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/edituser")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        try {
            service.update(user);
        } catch (InvalidEntryException | DoesNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> login(@RequestBody User user) {

        try {
            return ResponseEntity.ok(service.login(user));
        } catch (InvalidLoginException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
