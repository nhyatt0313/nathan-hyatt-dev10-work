/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import java.util.List;

import com.sg.spaceshipdealership.dto.User;

/**
 *
 * @author nhyat
 */
public interface UserDao {

    User read(Integer id);

    List<User> readAll();

    User create(User user);

    void update(User user);

    void delete(Integer id);
}
