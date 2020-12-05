/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.controller;

import com.sg.spaceshipdealership.dto.Contact;
import com.sg.spaceshipdealership.service.ContactService;
import com.sg.spaceshipdealership.service.InvalidEntryException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nhyat
 */
@RestController
@RequestMapping("/contact/admin")
public class ContactRestController {

    @Autowired
    ContactService service;

    @GetMapping("/allcontact")
    public ResponseEntity<List<Contact>> allContact() {
        return ResponseEntity.ok(service.readAll());
    }

    @PostMapping("/addcontact")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        try {
            contact = service.create(contact);
        } catch (InvalidEntryException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(contact);
    }
}
