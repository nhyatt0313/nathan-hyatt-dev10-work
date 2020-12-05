/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.controller;

import com.sg.spaceshipdealership.dto.Purchase;
import com.sg.spaceshipdealership.service.DoesNotExistException;
import com.sg.spaceshipdealership.service.InvalidEntryException;
import com.sg.spaceshipdealership.service.PurchaseService;
import com.sg.spaceshipdealership.service.VehicleServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/purchase/admin")
public class PurchaseRestController {

    @Autowired
    PurchaseService service;

    @GetMapping("/allpurchase")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Purchase>> allPurchase() {
        try {
            return ResponseEntity.ok(service.readAll());
        } catch (DoesNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getpurchase/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Purchase> getPurchase(@PathVariable int id) {
        try {
            Purchase purchase = service.read(id);
            return ResponseEntity.ok(purchase);
        } catch (DoesNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addpurchase")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Purchase> addPurchase(@RequestBody Purchase purchase) {
        try {
            purchase = service.create(purchase);
        } catch (InvalidEntryException | DoesNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(purchase);
    }

    @PutMapping("/editpurchase")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> editPurchase(@RequestBody Purchase purchase) {
        try {
            service.update(purchase);
        } catch (InvalidEntryException | DoesNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Update Success!");
    }

    
    @PostMapping("/salesreport")
    @CrossOrigin(origins = "*")
    public ResponseEntity<SalesReportForUser> rowOfSalesReport(@RequestBody UserDateRange userDates) {
        SalesReportForUser salesReportForUser = new SalesReportForUser();
        salesReportForUser.setUserId(userDates.getUserId());
        BigDecimal totalMoney;
        int totalVehicles;
        try {
            totalMoney = service.getUserSales(userDates.getUserId(), userDates.getStartDate(), userDates.getEndDate());
            salesReportForUser.setSales(totalMoney);

        } catch (DoesNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
        try {
            totalVehicles = service.getUserNumSold(userDates.getUserId(), userDates.getStartDate(), userDates.getEndDate());
            salesReportForUser.setVehiclesSold(totalVehicles);
        } catch (DoesNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }

        return ResponseEntity.ok(salesReportForUser);
    }

    public static class SalesReportForUser {

        int userId;
        BigDecimal sales;
        int vehiclesSold;

        public SalesReportForUser() {
        }

        public BigDecimal getSales() {
            return sales;
        }

        public void setSales(BigDecimal sales) {
            this.sales = sales;
        }

        public int getVehiclesSold() {
            return vehiclesSold;
        }

        public void setVehiclesSold(int vehiclesSold) {
            this.vehiclesSold = vehiclesSold;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }

    public static class UserDateRange {

        LocalDate startDate;
        LocalDate endDate;
        int userId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public UserDateRange() {
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

    }

}
