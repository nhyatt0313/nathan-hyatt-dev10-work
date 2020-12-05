/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dto;

import com.mycompany.flooringmastery.TaxState;
import java.math.BigDecimal;

/**
 *
 * @author nhyat
 */
public class Tax {
    private TaxState state;
    private BigDecimal taxRate;

    public TaxState getState() {
        return state;
    }

    public void setState(TaxState state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
}
