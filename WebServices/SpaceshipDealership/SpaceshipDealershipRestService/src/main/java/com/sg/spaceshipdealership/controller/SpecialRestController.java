/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.controller;

import com.sg.spaceshipdealership.dto.Special;
import com.sg.spaceshipdealership.service.DoesNotExistException;
import com.sg.spaceshipdealership.service.InvalidEntryException;
import com.sg.spaceshipdealership.service.SpecialService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/special")
public class SpecialRestController {

    @Autowired
    SpecialService service;

    @GetMapping("/allspecial")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Special>> allSpecial() {
            return ResponseEntity.ok(service.readAll());

    }

    @GetMapping("/getspecial/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Special> getSpecial(@PathVariable int id) {
        try {
            Special special = service.read(id);
            return ResponseEntity.ok(special);
        } catch (DoesNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admin/addspecial")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Special> addSpecial(@RequestBody Special special) {
        try {
            special = service.create(special);
        } catch (InvalidEntryException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(special);
    }
    
    @DeleteMapping("/admin/deletespecial/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteSpecial(@PathVariable int id){
        try{
            service.delete(id);
            return ResponseEntity.ok("Delete Successful!");
        } catch (DoesNotExistException e){
            return new ResponseEntity("Special Not Found", HttpStatus.NOT_FOUND);
        }
    }
    
}
