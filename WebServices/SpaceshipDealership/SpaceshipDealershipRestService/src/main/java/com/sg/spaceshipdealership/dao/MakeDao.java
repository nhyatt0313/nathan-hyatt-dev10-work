/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import java.util.List;

import com.sg.spaceshipdealership.dto.Make;

/**
 *
 * @author nhyat
 */
public interface MakeDao {

    Make create(Make make);

    Make read(Integer id);

    List<Make> readAll();

    void update(Make make);
}
