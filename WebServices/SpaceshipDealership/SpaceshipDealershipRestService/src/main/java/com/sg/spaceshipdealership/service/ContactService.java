/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import java.util.List;

import com.sg.spaceshipdealership.dto.Contact;

/**
 *
 * @author nhyat
 */
public interface ContactService {

    Contact read(int id) throws DoesNotExistException;

    List<Contact> readAll();

    Contact create(Contact contact) throws InvalidEntryException;

    void update(Contact contact) throws InvalidEntryException, DoesNotExistException;

    void delete(int id) throws DoesNotExistException;
}
