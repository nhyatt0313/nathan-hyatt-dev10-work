/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import java.util.List;

import com.sg.spaceshipdealership.dto.Vehicle;

/**
 *
 * @author nhyat
 */
public interface VehicleDao {

    public Vehicle create(Vehicle vehicle);

    public Vehicle read(Integer id);

    public void update(Vehicle vehicle);

    public List<Vehicle> readAll();
    
    public List<Vehicle> readAllSorted(boolean isNew);
    
    public List<Vehicle> search(String string, Boolean isNew);

    public List<Vehicle> findFeatured();

}
