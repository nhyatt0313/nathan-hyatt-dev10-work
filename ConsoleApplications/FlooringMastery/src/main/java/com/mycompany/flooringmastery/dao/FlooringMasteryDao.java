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
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author nhyat
 */
public interface FlooringMasteryDao {
    
    public Order getOrder(LocalDate date, int id);
    
    public ArrayList<Order> getOrdersByDate(LocalDate date);
    
    public Order addOrder(LocalDate date, Order order);
    
    public Order removeOrder(LocalDate date, Order order);
    
    public ArrayList<Product> getProductsFromFile() throws PersistenceException;
    
    public ArrayList<Tax> getTaxesFromFile() throws PersistenceException;
    
    public void readFile(LocalDate date) throws PersistenceException;
    
    public void writeFile(LocalDate date) throws PersistenceException;

    public String getDelim();

    public int getMaxVal(LocalDate date);

    public Product getProduct(ProductType prodType) throws PersistenceException;

    public Tax getTax(TaxState state) throws PersistenceException;

    public void saveToAllFiles() throws PersistenceException;

    public void readFromAllFiles() throws PersistenceException;

    public void setOrdersOnDate(LocalDate date, ArrayList<Order> orders);
    
}
