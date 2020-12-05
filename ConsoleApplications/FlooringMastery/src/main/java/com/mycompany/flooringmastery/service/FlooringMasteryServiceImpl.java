/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.ProductType;
import com.mycompany.flooringmastery.TaxState;
import com.mycompany.flooringmastery.dao.FlooringMasteryDao;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import com.mycompany.flooringmastery.exception.PersistenceException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author nhyat
 */
public class FlooringMasteryServiceImpl implements FlooringMasteryService {

    FlooringMasteryDao dao;

    public FlooringMasteryServiceImpl(FlooringMasteryDao dao) {
        this.dao = dao;
    }
    
    @Override
    public void addOrder(LocalDate date, Order order) throws PersistenceException {
        order.setOrderNumber(dao.getMaxVal(date) + 1);

        // set material cost
        BigDecimal matCost = (order.getArea()).multiply(order.getProduct().getAreaCostSqft());
        order.setMaterialCost(matCost);
        // set labor cost
        BigDecimal labCost = (order.getArea()).multiply(order.getProduct().getLaborCostSqft());
        order.setLaborCost(labCost);
        // set totalTax
        BigDecimal totBeforeTax = (matCost).add(labCost);
        BigDecimal totTax = (totBeforeTax).multiply(order.getTax().getTaxRate().movePointLeft(2));
        totTax.setScale(2, RoundingMode.HALF_UP);
        order.setTotalTax(totTax);
        // set total
        BigDecimal tot = (totBeforeTax).add(totTax);
        tot.setScale(2, RoundingMode.HALF_UP);
        order.setTotal(tot);

        dao.addOrder(date, order);
    }

    @Override
    public void removeOrder(LocalDate date, int id) throws PersistenceException {
        Order o = dao.getOrder(date, id);
        dao.removeOrder(date, o);
    }

    @Override
    public String getDelim() {
        return dao.getDelim();
    }

    @Override
    public Product getProduct(ProductType prodType) throws PersistenceException {
        return dao.getProduct(prodType);
    }

    @Override
    public Tax getTax(TaxState state) throws PersistenceException {
        return dao.getTax(state);
    }

    @Override
    public ArrayList<Order> getOrdersByDate(LocalDate date) {
        return dao.getOrdersByDate(date);
    }

    @Override
    public Order getOrder(LocalDate date, int id) {
        return dao.getOrder(date, id);
    }

    @Override
    public int getMaxOrderNumber(LocalDate date) {
        return dao.getMaxVal(date);
    }

    @Override
    public void saveWork() throws PersistenceException {
        dao.saveToAllFiles();
    }

    @Override
    public void readAllFiles() throws PersistenceException {
        dao.readFromAllFiles();
    }

    @Override
    public ArrayList<Tax> getAllTaxes() throws PersistenceException {
        return dao.getTaxesFromFile();
    }

    @Override
    public ArrayList<Product> getAllProducts() throws PersistenceException {
        return dao.getProductsFromFile();
    }
    
    @Override
    public Order editOrder(LocalDate d, Order before, Order after) {
        dao.removeOrder(d, before);
        
        // set material cost
        BigDecimal matCost = (after.getArea()).multiply(after.getProduct().getAreaCostSqft());
        after.setMaterialCost(matCost);
        // set labor cost
        BigDecimal labCost = (after.getArea()).multiply(after.getProduct().getLaborCostSqft());
        after.setLaborCost(labCost);
        // set totalTax
        BigDecimal totBeforeTax = (matCost).add(labCost);
        BigDecimal totTax = (totBeforeTax).multiply(after.getTax().getTaxRate().movePointLeft(2));
        totTax.setScale(2, RoundingMode.HALF_UP);
        after.setTotalTax(totTax);
        // set total
        BigDecimal tot = (totBeforeTax).add(totTax);
        tot.setScale(2, RoundingMode.HALF_UP);
        after.setTotal(tot);
        
        return dao.addOrder(d, after);
    }

}
