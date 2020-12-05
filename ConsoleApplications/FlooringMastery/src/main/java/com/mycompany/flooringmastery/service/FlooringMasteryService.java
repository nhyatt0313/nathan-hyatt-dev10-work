/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.ProductType;
import com.mycompany.flooringmastery.TaxState;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import com.mycompany.flooringmastery.exception.PersistenceException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author nhyat
 */
public interface FlooringMasteryService {
    
    public void addOrder(LocalDate date, Order order)throws PersistenceException;
    
    public void removeOrder(LocalDate date, int id)throws PersistenceException;

    public String getDelim();

    public Product getProduct(ProductType prodType) throws PersistenceException;

    public Tax getTax(TaxState state) throws PersistenceException;

    public ArrayList<Order> getOrdersByDate(LocalDate date);
    
    public Order getOrder(LocalDate date, int id);
    
    public int getMaxOrderNumber(LocalDate date);

    public void saveWork() throws PersistenceException;

    public void readAllFiles() throws PersistenceException;

    public ArrayList<Tax> getAllTaxes() throws PersistenceException;

    public ArrayList<Product> getAllProducts() throws PersistenceException;

    public Order editOrder(LocalDate d, Order before, Order after);
    
}
