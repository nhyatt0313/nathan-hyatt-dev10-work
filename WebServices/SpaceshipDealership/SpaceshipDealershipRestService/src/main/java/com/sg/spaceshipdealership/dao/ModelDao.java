/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import java.util.List;

import com.sg.spaceshipdealership.dto.Model;

/**
 *
 * @author nhyat
 */
public interface ModelDao {

    public Model create(Model model);

    public void update(Model model);

    public Model read(Integer id);

    public List<Model> readAll();
}
