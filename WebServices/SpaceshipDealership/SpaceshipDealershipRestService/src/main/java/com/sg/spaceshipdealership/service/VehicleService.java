/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import com.sg.spaceshipdealership.dto.Vehicle;
import com.sg.spaceshipdealership.service.VehicleServiceImpl.InventoryReportItem;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author nhyat
 */
public interface VehicleService {

    public Vehicle create(Vehicle vehicle) throws InvalidEntryException, DoesNotExistException;

    public Vehicle read(Integer id) throws DoesNotExistException;

    public void update(Vehicle vehicle) throws InvalidEntryException, DoesNotExistException;

    public List<Vehicle> readAll() throws DoesNotExistException;

    public List<Vehicle> search(String search, Boolean isNew,
            Integer minYear, Integer maxYear,
            BigDecimal minPrice, BigDecimal maxPrice) throws DoesNotExistException;

    public List<Vehicle> findFeatured() throws DoesNotExistException;
    
    public List<InventoryReportItem> getInventoryReport(boolean newVehicle) throws DoesNotExistException;

}
