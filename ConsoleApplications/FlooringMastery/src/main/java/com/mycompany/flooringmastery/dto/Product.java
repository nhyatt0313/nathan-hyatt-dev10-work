/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dto;

import com.mycompany.flooringmastery.ProductType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nhyat
 */
public class Product {
    private ProductType prodType;
    private BigDecimal areaCostSqft;
    private BigDecimal laborCostSqft;
    

    public ProductType getProdType() {
        return prodType;
    }

    public void setProdType(ProductType prodType) {
        this.prodType = prodType;
    }

    public BigDecimal getAreaCostSqft() {
        return areaCostSqft;
    }

    public void setAreaCostSqft(BigDecimal areaCostSqft) {
        this.areaCostSqft = areaCostSqft;
    }

    public BigDecimal getLaborCostSqft() {
        return laborCostSqft;
    }

    public void setLaborCostSqft(BigDecimal laborCostSqft) {
        this.laborCostSqft = laborCostSqft;
    }
    
    
    
}
