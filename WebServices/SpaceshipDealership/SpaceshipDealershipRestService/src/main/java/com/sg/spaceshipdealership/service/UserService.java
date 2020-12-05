/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import java.util.List;

import com.sg.spaceshipdealership.dto.User;

/**
 *
 * @author nhyat
 */
public interface UserService {

    User create(User user) throws InvalidEntryException;

    User read(Integer id) throws DoesNotExistException;

    List<User> readAll();

    void update(User user) throws InvalidEntryException, DoesNotExistException;

    void delete(Integer id) throws DoesNotExistException;

    public User login(User user) throws InvalidLoginException;
}
