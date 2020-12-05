/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import java.util.List;

import com.sg.spaceshipdealership.dto.Purchase;

/**
 *
 * @author nhyat
 */
public interface PurchaseDao {

    Purchase read(Integer id);

    List<Purchase> readAll();

    Purchase create(Purchase purchase);
    
    public void update(Purchase purchase);
}
