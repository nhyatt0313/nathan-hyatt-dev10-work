/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import java.util.List;

import com.sg.spaceshipdealership.dto.Make;

/**
 *
 * @author nhyat
 */
public interface MakeService {

    Make create(Make make) throws InvalidEntryException, DoesNotExistException;

    Make read(Integer id) throws DoesNotExistException;

    List<Make> readAll() throws DoesNotExistException;

    void update(Make make) throws InvalidEntryException, DoesNotExistException;

}
