/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.sg.spaceshipdealership.dto.Purchase;

/**
 *
 * @author nhyat
 */
public interface PurchaseService {

    Purchase read(Integer id) throws DoesNotExistException;

    List<Purchase> readAll() throws DoesNotExistException;

    Purchase create(Purchase purchase) throws DoesNotExistException, InvalidEntryException;

    public void update(Purchase purchase) throws InvalidEntryException, DoesNotExistException;
    
     public BigDecimal getUserSales(int userId, LocalDate startDate, LocalDate endDate) throws DoesNotExistException;
     
     public int getUserNumSold(int userId, LocalDate startDate, LocalDate endDate) throws DoesNotExistException;
}
