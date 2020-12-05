/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.controller;

import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.service.DoesNotExistException;
import com.sg.spaceshipdealership.service.InvalidEntryException;
import com.sg.spaceshipdealership.service.MakeService;
import com.sg.spaceshipdealership.service.UserService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nhyat
 */
@RestController
@RequestMapping("/make/admin")
public class MakeRestController {
    
    @Autowired
    MakeService service;
    
    @Autowired
    UserService userService;
    
    @GetMapping("/allmake")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Make>> allMake(){
        try {
            return ResponseEntity.ok(service.readAll());
        } catch (DoesNotExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getmake/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Make> getMake(@PathVariable int id){
        try {
            Make make = service.read(id);
            return ResponseEntity.ok(make);
        } catch (DoesNotExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/addmake")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Make> addMake(@RequestBody Make make){
        try{
            userService.read(make.getUser().getId());
            make = service.create(make);
        } catch (InvalidEntryException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DoesNotExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(make);
    }
}
