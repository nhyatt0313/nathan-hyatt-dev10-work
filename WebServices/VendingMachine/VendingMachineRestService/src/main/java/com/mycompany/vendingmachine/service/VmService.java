/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.Id;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author nhyat
 */
public interface VmService {
    
    Change calculateChange(BigDecimal price, BigDecimal entry);
    
    BigDecimal getPrice(Id id);
    
    List<Snack> getAllSnacks();
    
    boolean buyItem(Id choice);
    
    Id convertToId(String choice);
    
    Snack getSnackById(Id id);
    
    boolean checkSufficientFunds(BigDecimal money, BigDecimal price);
    
}
