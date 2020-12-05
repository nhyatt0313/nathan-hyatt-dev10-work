/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import java.util.List;

import com.sg.spaceshipdealership.dto.Special;

/**
 *
 * @author nhyat
 */
public interface SpecialDao {

    Special read(int id);

    List<Special> readList();

    Special create(Special special);

    void update(Special special);

    void delete(int id);
}
