/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.ProductType;
import com.mycompany.flooringmastery.TaxState;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import com.mycompany.flooringmastery.exception.PersistenceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nhyat
 */
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao {

    private final Product p;
    private final Tax t;
    private final Order o;

    private final ArrayList<Order> oList;
    private final ArrayList<Product> pList;
    private final ArrayList<Tax> tList;

    private final LocalDate d;

    private final Map<LocalDate, ArrayList<Order>> oMap;

    public FlooringMasteryDaoStubImpl() {
        // create one order with set values
        this.p = new Product();
        this.t = new Tax();
        this.o = new Order(p, t);
        this.d = LocalDate.parse("02222222", DateTimeFormatter.ofPattern("MMddyyyy"));

        // set values
        o.setOrderNumber(1);
        o.setCustName("Wise");
        t.setState(TaxState.valueOf("OH"));
        t.setTaxRate(BigDecimal.ONE);
        o.setTax(t);
        p.setProdType(ProductType.valueOf("WOOD"));
        o.setArea(new BigDecimal(100));
        p.setAreaCostSqft(new BigDecimal("5.15"));
        p.setLaborCostSqft(new BigDecimal("4.75"));
        o.setProduct(p);
        o.setMaterialCost(new BigDecimal("515.00"));
        o.setLaborCost(new BigDecimal("475.00"));
        o.setTotalTax(new BigDecimal("61.88"));
        o.setTotal(new BigDecimal("1051.88"));
        // create an arrayList of orders containing this single order
        oList = new ArrayList<>();
        oList.add(o);

        oMap = new HashMap<>();
        oMap.put(d, oList);
        
        pList = new ArrayList<>();
        tList = new ArrayList<>();
        
    }

    @Override
    public Order getOrder(LocalDate date, int id) {
        if (id == o.getOrderNumber()) {
            return o;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Order> getOrdersByDate(LocalDate date) {
        if (date.equals(d)) {
            return oList;
        } else {
            return null;
        }
    }

    @Override
    public Order addOrder(LocalDate date, Order order) {
        if (order.getOrderNumber() == o.getOrderNumber()) {
            return null;
        } else {
            return order;
        }
    }

    @Override
    public Order removeOrder(LocalDate date, Order order) {
        if (order.getOrderNumber() == o.getOrderNumber()) {
            return o;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Product> getProductsFromFile() throws PersistenceException{
        pList.add(p);
        return pList;
    }

    @Override
    public ArrayList<Tax> getTaxesFromFile() throws PersistenceException {
        tList.add(t);
        return tList;
    }

    @Override
    public void readFile(LocalDate date) throws PersistenceException {
        
    }

    @Override
    public void writeFile(LocalDate date) throws PersistenceException {
        
    }

    @Override
    public String getDelim() {
        return "::";
    }

    @Override
    public Product getProduct(ProductType prodType) {
        Product product = new Product();
        product.setProdType(prodType);
        product.setAreaCostSqft(BigDecimal.ZERO);
        product.setLaborCostSqft(BigDecimal.ZERO);
        return product;
    }

    @Override
    public Tax getTax(TaxState state) {
        Tax tax = new Tax();
        tax.setState(state);
        tax.setTaxRate(BigDecimal.ZERO);
        return tax;
    }

    @Override
    public void saveToAllFiles() throws PersistenceException {
        
    }

    @Override
    public void readFromAllFiles() throws PersistenceException {
        
    }

    @Override
    public void setOrdersOnDate(LocalDate date, ArrayList<Order> orders) {
        
    }

    @Override
    public int getMaxVal(LocalDate date) {
        if (date.equals(d)){
        return 1;
        } else {
            return -1;
        }
    }

}
