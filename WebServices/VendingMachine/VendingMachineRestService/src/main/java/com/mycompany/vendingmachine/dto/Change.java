/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author nhyat
 */
public class Change {
    
    private BigDecimal total;
    private int dollars, quarters, dimes, nickels, pennies;
    
    public Change(BigDecimal total){
        this.total = total;
        this.dollars = 0;
        this.quarters = 0;
        this.dimes = 0;
        this.nickels = 0;
        this.pennies = 0;
    }    
    
    public int getDollars(){
        BigDecimal newTotal = total.setScale(0, RoundingMode.FLOOR);
        total = total.subtract(newTotal).setScale(2, RoundingMode.FLOOR);
        return newTotal.intValueExact();
    }
    
    public int getQuarters(){
        
        BigDecimal qv = new BigDecimal("0.25");
        
        BigDecimal numQuarters = total.divide(qv, 0, RoundingMode.FLOOR);
        BigDecimal quarterValue = numQuarters.multiply(qv);
        
        total = total.subtract(quarterValue).setScale(2, RoundingMode.FLOOR);
        
        return numQuarters.intValueExact(); 
    }
    
    public int getDimes(){
        BigDecimal dv = new BigDecimal("0.10");
        
        BigDecimal numDimes = total.divide(dv, 0, RoundingMode.FLOOR);
        BigDecimal quarterValue = numDimes.multiply(dv);
        
        total = total.subtract(quarterValue).setScale(2, RoundingMode.FLOOR);
        
        return numDimes.intValueExact(); 
    }
    
    public int getNickels(){
        BigDecimal nv = new BigDecimal("0.05");
        
        BigDecimal numNickels = total.divide(nv, 0, RoundingMode.FLOOR);
        BigDecimal nickelValue = numNickels.multiply(nv);
        
        total = total.subtract(nickelValue).setScale(2, RoundingMode.FLOOR);
        
        return numNickels.intValueExact();
    }
    
    public int getPennies(){
        BigDecimal pv = new BigDecimal("0.01");
        
        BigDecimal numPennies = total.divide(pv, 0, RoundingMode.FLOOR);
        BigDecimal pennieValue = numPennies.multiply(pv);
        
        total = total.subtract(pennieValue).setScale(2, RoundingMode.FLOOR);
        
        return numPennies.intValueExact();
    }
    
    public boolean testForZero(){
        if (total.compareTo(BigDecimal.ZERO) == 0){
            return true;
        } else {
            return false;
        }
    }
}
