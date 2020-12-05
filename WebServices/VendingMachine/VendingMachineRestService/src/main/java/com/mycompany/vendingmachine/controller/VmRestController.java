/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.Id;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Snack;
import com.mycompany.vendingmachine.service.VmService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nhyat
 */
@RestController
@RequestMapping("/VendingMachine")
public class VmRestController {
    @Autowired
    VmService service;
    
    @PutMapping("/buy/{id}/{money}")
    @CrossOrigin(origins="*")
    public ResponseEntity buySnack(@PathVariable String id, @PathVariable String money){
        BigDecimal m = new BigDecimal(money);
        Id snackId = Id.valueOf(id.toUpperCase());
        Snack snack = service.getSnackById(snackId);
        if (snack == null){
            return new ResponseEntity("Item not found", HttpStatus.NOT_FOUND);
        }
        if (snack.getNumInStock() > 0){
            
            if (snack.getPrice().compareTo(m) <= 0){
                service.buyItem(snackId);
                Change change = service.calculateChange(snack.getPrice(), m);
                return ResponseEntity.ok(change);
            } else {
                return new ResponseEntity("Not enough money", HttpStatus.FORBIDDEN);
            } 
        } else {
            return new ResponseEntity("Out of stock", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/snacks")
    @CrossOrigin(origins="*")
    public ResponseEntity<List<Snack>> getAllSnacks(){
        
        List<Snack> snacks;
            snacks = service.getAllSnacks();
        if (snacks == null){
            return new ResponseEntity("Error retrieving items", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(snacks);
    }
    
    @GetMapping("/snacks/{id}")
    @CrossOrigin(origins="*")
    public ResponseEntity<Snack> getSnack(@PathVariable String id){
        Id snackId = Id.valueOf(id.toUpperCase());
        Snack snack = service.getSnackById(snackId); 
        if (snack == null){
            return new ResponseEntity("Snack "+snackId+ " was not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(snack);
    }
}
