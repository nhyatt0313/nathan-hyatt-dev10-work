/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import com.sg.spaceshipdealership.dao.PurchaseDao;
import com.sg.spaceshipdealership.dto.Purchase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseDao purchaseDao;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    UserService userService;

    @Override
    public Purchase read(Integer id) throws DoesNotExistException {
        Purchase purchase = purchaseDao.read(id);
        if (purchase == null) {
            throw new DoesNotExistException("Purchase  "+id+" Not Found");
        }
        purchase.setUser(userService.read(purchase.getUser().getId()));
        purchase.setVehicle(vehicleService.read(purchase.getVehicle().getId()));

        return purchase;
    }

    @Override
    public List<Purchase> readAll() throws DoesNotExistException {
        List<Purchase> list = purchaseDao.readAll();
        for (Purchase purchase : list) {
            purchase.setUser(userService.read(purchase.getUser().getId()));
            purchase.setVehicle(vehicleService.read(purchase.getVehicle().getId()));
        }
        return purchaseDao.readAll();
    }

    @Override
    public Purchase create(Purchase purchase) throws DoesNotExistException, InvalidEntryException {
        purchase.setPurchaseDate(LocalDate.now());
        validatePurchase(purchase);
        purchase.getVehicle().setSold(true);
        vehicleService.update(purchase.getVehicle());

        return purchaseDao.create(purchase);
    }

    @Override
    public void update(Purchase purchase) throws DoesNotExistException, InvalidEntryException {
        read(purchase.getId());
        validatePurchase(purchase);
        purchaseDao.update(purchase);
    }

    @Override
    public BigDecimal getUserSales(int userId, LocalDate startDate, LocalDate endDate) throws DoesNotExistException {
        userService.read(userId);
        List<Purchase> list = readAll();

        if (startDate != null) {
            list = list.stream()
                    .filter(p -> p.getPurchaseDate().compareTo(startDate) > 0)
                    .collect(Collectors.toList());
        }
        if (endDate != null) {
            list = list.stream()
                    .filter(p -> p.getPurchaseDate().compareTo(endDate) < 0)
                    .collect(Collectors.toList());
        }

        BigDecimal earned = new BigDecimal("0.00");

        for (Purchase s : list) {
            if (s.getUser().getId() == userId) {
                earned = earned.add(s.getPurchaseCost());
            }
        }

        return earned;
    }
    
    @Override
    public int getUserNumSold(int userId, LocalDate startDate, LocalDate endDate) throws DoesNotExistException {
        userService.read(userId);
        List<Purchase> list = readAll();
        
        if (startDate != null) {
            list = list.stream()
                    .filter(p -> p.getPurchaseDate().compareTo(startDate) > 0)
                    .collect(Collectors.toList());
        }
        if (endDate != null) {
            list = list.stream()
                    .filter(p -> p.getPurchaseDate().compareTo(endDate) < 0)
                    .collect(Collectors.toList());
        }

        int sold = 0;

        for (Purchase s : list) {
            if (s.getUser().getId() == userId) {
                sold++;
            }
        }

        return sold;
    }

    private void validatePurchase(Purchase purchase) throws InvalidEntryException, DoesNotExistException {

        purchase.setVehicle(vehicleService.read(purchase.getVehicle().getId()));
        purchase.setUser(userService.read(purchase.getUser().getId()));

        List<String> errors = new ArrayList<>();

        // check fields
        if (purchase.getCity() == null
                || purchase.getCity().trim().isEmpty()) {
            errors.add("Invalid City");
        }

        BigDecimal msrp = purchase.getVehicle().getMsrp();
        BigDecimal cost = purchase.getPurchaseCost();
        BigDecimal min = msrp.multiply(new BigDecimal(".95")).setScale(2, RoundingMode.HALF_UP);
        if ((cost == null) || (!((cost.compareTo(msrp) <= 0) && (cost.compareTo(min) >= 0)))) {
            errors.add("invalid Cost");
        }

        String type = purchase.getPurchaseType();
        if ((type == null) || (!(type.equalsIgnoreCase("bank finance") || type.equalsIgnoreCase("dealer finance") || type.equalsIgnoreCase("cash")))) {
            errors.add("Invalid Purchase Type");
        }

        if (purchase.getState() == null || purchase.getState().isEmpty()) {
            errors.add("Invalid State");
        }

        if (purchase.getStreet() == null || purchase.getStreet().isEmpty()) {
            errors.add("Invalid Street");
        }

        if (purchase.getZip() == null || !((purchase.getZip() >= 10000) && (purchase.getZip() <= 99999))) {
            errors.add("Invalid Zip");
        }

        if (purchase.getFirstName() == null || purchase.getFirstName().isEmpty()) {
            errors.add("Invalid First Name");
        }

        if (purchase.getLastName() == null || purchase.getLastName().isEmpty()) {
            errors.add("Invalid Last Name");
        }

        if (purchase.getPhone() == null && purchase.getEmail() == null) {
            errors.add("Invalid Contact Info (Phone or Email Required)");
        }
        if (purchase.getPhone() != null) {
            if (purchase.getPhone().length() != 10) {
                errors.add("Invalid Phone");
            }
        }
        if (purchase.getEmail() != null) {
            if (purchase.getEmail().isEmpty()) {
                errors.add("Invalid Email");
            }
        }

        // throw the errors if there are any
        String allErrors = "";
        int numErrors = errors.size();
        int iter = 0;
        if (numErrors != 0) {
            for (String s : errors) {
                iter++;
                if (iter < numErrors) {
                    allErrors += (s + ", ");
                } else {
                    allErrors += s;
                }
            }
            // could possibly pass the list to exception class instead of the formatted string
            throw new InvalidEntryException(allErrors);
        }
    }
}
