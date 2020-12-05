/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.controller;

import com.sg.spaceshipdealership.dto.Model;
import com.sg.spaceshipdealership.service.DoesNotExistException;
import com.sg.spaceshipdealership.service.InvalidEntryException;
import com.sg.spaceshipdealership.service.ModelService;

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
@RequestMapping("/model/admin")
public class ModelRestController {
    
    @Autowired
    ModelService service;
    
    @GetMapping("/allmodel")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Model>> allModel(){
        try {
            return ResponseEntity.ok(service.readAll());
        } catch (DoesNotExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getmodel/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Model> getModel(@PathVariable int id){
        try {
            Model model = service.read(id);
            return ResponseEntity.ok(model);
        } catch (DoesNotExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/addmodel")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Model> addModel(@RequestBody Model model){
        try{
            model = service.create(model);
        } catch (InvalidEntryException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DoesNotExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(model);
    }
}
