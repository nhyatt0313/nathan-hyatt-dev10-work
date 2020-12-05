/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.Id;
import com.mycompany.vendingmachine.exception.VmPersistenceException;
import com.mycompany.vendingmachine.dao.VmDao;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nhyat
 */
@Service
public class VmServiceImpl implements VmService{

    @Autowired
    private VmDao dao;

//    public VmServiceImpl(VmDao dao) {
//        this.dao = dao;
//    }

    // calculate change - also checks for valid entry
    @Override
    public Change calculateChange(BigDecimal price, BigDecimal entry){

        if (price.compareTo(entry) <= 0) {
            BigDecimal diff = entry.subtract(price);

            Change change = new Change(diff);
            return change;
        } else {
            return null;
        }
    }

    // get price
    @Override
    public BigDecimal getPrice(Id id){
        Snack snack;
        try {
            snack = dao.getSnackById(id);
            return snack.getPrice();
        } catch (VmPersistenceException ex) {
            return null;
        }
        

    }

    @Override
    public List<Snack> getAllSnacks() {
        return dao.getAllSnacks();
    }

    @Override
    public boolean buyItem(Id choice) {
        
        try {
            Snack snack = dao.getSnackById(choice);
            
            int newStock = snack.getNumInStock() - 1;
            snack.setNumInStock(newStock);
            dao.buySnack(snack);
            return true;
        } catch (SQLException | VmPersistenceException  ex) {
            return false;
        }
    }

    @Override
    public Id convertToId(String choice) {
        try {
            Id id = Id.valueOf(choice.toUpperCase());
            if (getSnackById(id).getNumInStock() == 0){
                return null;
            }
            return id;

        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public Snack getSnackById(Id id) {
        try {
            return dao.getSnackById(id);
        } catch (VmPersistenceException ex) {
            return null;
        }
    }

    @Override
    public boolean checkSufficientFunds(BigDecimal money, BigDecimal price) {
        if (money.compareTo(price) < 0) {
            return false;
        }
        return true;
    }
}
