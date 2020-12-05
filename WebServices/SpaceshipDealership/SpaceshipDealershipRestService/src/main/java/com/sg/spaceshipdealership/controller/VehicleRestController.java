/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.controller;

import com.sg.spaceshipdealership.dto.Vehicle;
import com.sg.spaceshipdealership.service.DoesNotExistException;
import com.sg.spaceshipdealership.service.InvalidEntryException;
import com.sg.spaceshipdealership.service.VehicleService;
import com.sg.spaceshipdealership.service.VehicleServiceImpl.InventoryReportItem;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nhyat
 */
@RestController
@RequestMapping("/vehicle/admin")
public class VehicleRestController {

    @Autowired
    VehicleService service;

    @GetMapping("/allvehicle")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Vehicle>> allVehicle() {
        try {
            return ResponseEntity.ok(service.readAll());
        } catch (DoesNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getvehicle/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable int id) {
        try {
            Vehicle vehicle = service.read(id);
            return ResponseEntity.ok(vehicle);
        } catch (DoesNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addvehicle")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        try {
            vehicle = service.create(vehicle);
        } catch (InvalidEntryException | DoesNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(vehicle);
    }

    @PutMapping("/editvehicle")
    @CrossOrigin(origins = "*")
    public void editVehicle(@RequestBody Vehicle vehicle) throws InvalidEntryException, DoesNotExistException {
            service.update(vehicle);
    }
    
    @GetMapping("/getfeatured")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Vehicle>> findFeatured(){
        try {
            return ResponseEntity.ok(service.findFeatured());
        } catch (DoesNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/search")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Vehicle>> getSearchResults(@RequestBody SearchQuery search) {
        try {
        return ResponseEntity.ok(service.search(search.getSearchString(), 
                search.getNewVehicle(), 
                search.getMinYear(), 
                search.getMaxYear(), 
                search.getMinPrice(), 
                search.getMaxPrice()));
        } catch (DoesNotExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/inventory/{newVehicle}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<InventoryReportItem>> getInventoryReport(@PathVariable boolean newVehicle){
        try {
            return ResponseEntity.ok(service.getInventoryReport(newVehicle));
        } catch (DoesNotExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
   
    public static class SearchQuery {

        private String searchString;
        private BigDecimal minPrice;
        private BigDecimal maxPrice;
        private Integer minYear;
        private Integer maxYear;
        private Boolean newVehicle;

        public String getSearchString() {
            return searchString;
        }

        public void setSearchString(String searchString) {
            this.searchString = searchString;
        }

        public BigDecimal getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(BigDecimal minPrice) {
            this.minPrice = minPrice;
        }

        public BigDecimal getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(BigDecimal maxPrice) {
            this.maxPrice = maxPrice;
        }

        public Integer getMinYear() {
            return minYear;
        }

        public void setMinYear(Integer minYear) {
            this.minYear = minYear;
        }

        public Integer getMaxYear() {
            return maxYear;
        }

        public void setMaxYear(Integer maxYear) {
            this.maxYear = maxYear;
        }

        public Boolean getNewVehicle() {
            return newVehicle;
        }

        public void setNewVehicle(Boolean newVehicle) {
            this.newVehicle = newVehicle;
        }   
    }
}
