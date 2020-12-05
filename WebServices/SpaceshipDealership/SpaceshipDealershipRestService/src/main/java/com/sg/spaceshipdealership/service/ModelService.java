/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import java.util.List;

import com.sg.spaceshipdealership.dto.Model;

/**
 *
 * @author nhyat
 */
public interface ModelService {

    public Model create(Model model) throws InvalidEntryException, DoesNotExistException;

    public Model read(Integer id) throws DoesNotExistException;

    public List<Model> readAll() throws DoesNotExistException;

    public void update(Model model) throws InvalidEntryException, DoesNotExistException;
}
