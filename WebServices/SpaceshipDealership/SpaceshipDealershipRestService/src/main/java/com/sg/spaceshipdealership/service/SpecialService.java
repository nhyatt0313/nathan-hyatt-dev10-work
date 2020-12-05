/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import java.util.List;

import com.sg.spaceshipdealership.dto.Special;

/**
 *
 * @author nhyat
 */
public interface SpecialService {

    Special read(int id) throws DoesNotExistException;

    List<Special> readAll();

    Special create(Special special) throws InvalidEntryException;

    void update(Special special) throws InvalidEntryException, DoesNotExistException;

    void delete(int id) throws DoesNotExistException;
}
