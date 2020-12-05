/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dto;

import com.mycompany.flooringmastery.TaxState;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author nhyat
 */
public class Order {

    private int orderNumber;
    private String custName;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal area;
    private BigDecimal totalTax;
    private BigDecimal total;

    Product product;
    Tax tax;

    public Order(Product product, Tax tax) {
        this.product = product;
        this.tax = tax;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int number) {
        this.orderNumber = number;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        // convert all fields for order to one string value for audit purposes
        return " |Order Number: " + orderNumber
                + " |Customer Name: " + custName
                + " |State: " + tax.getState()
                + " |Tax Rate: " + tax.getTaxRate().setScale(2, BigDecimal.ROUND_HALF_UP)
                + " |Product Type: " + product.getProdType()
                + " |Area: " + area.setScale(2, BigDecimal.ROUND_HALF_UP)
                + " |Area Cost/Sqft: " + product.getAreaCostSqft().setScale(2, BigDecimal.ROUND_HALF_UP)
                + " |Labor Cost/Sqft: " + product.getLaborCostSqft().setScale(2, BigDecimal.ROUND_HALF_UP)
                + " |Material Cost: " + materialCost.setScale(2, BigDecimal.ROUND_HALF_UP)
                + " |Labor Cost: " + laborCost.setScale(2, BigDecimal.ROUND_HALF_UP)
                + " |Tax: " + totalTax.setScale(2, BigDecimal.ROUND_HALF_UP)
                + " |Total: " + total.setScale(2, BigDecimal.ROUND_HALF_UP) + "\n";
    }

    public String toFileString(String DELIM) {
        return orderNumber
                + DELIM + custName
                + DELIM + tax.getState()
                + DELIM + tax.getTaxRate().setScale(2, BigDecimal.ROUND_HALF_UP)
                + DELIM + product.getProdType()
                + DELIM + area.setScale(2, BigDecimal.ROUND_HALF_UP)
                + DELIM + product.getAreaCostSqft().setScale(2, BigDecimal.ROUND_HALF_UP)
                + DELIM + product.getLaborCostSqft().setScale(2, BigDecimal.ROUND_HALF_UP)
                + DELIM + materialCost.setScale(2, BigDecimal.ROUND_HALF_UP)
                + DELIM + laborCost.setScale(2, BigDecimal.ROUND_HALF_UP)
                + DELIM + totalTax.setScale(2, BigDecimal.ROUND_HALF_UP)
                + DELIM + total.setScale(2, BigDecimal.ROUND_HALF_UP) + "\n";
    }

}
