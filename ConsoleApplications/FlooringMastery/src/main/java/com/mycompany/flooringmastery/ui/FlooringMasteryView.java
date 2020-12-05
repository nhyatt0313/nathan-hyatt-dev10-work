/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.ui;

import com.mycompany.flooringmastery.ProductType;
import com.mycompany.flooringmastery.TaxState;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.exception.ExclusionEntryException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author nhyat
 */
public interface FlooringMasteryView {
    
    public void displayOrder(Order order);
    
    public void displayOrdersOnDate(LocalDate date, ArrayList<Order> orderList);
    
    public void displayError(String message);
    
    public int displayMenuGetChoice();
    
    public void displayAddSuccessMessaage(Order order);
    
    public void displayRemoveSuccessMessage(Order order);
    
    public void displayEditSuccessMessage(Order before, Order after);
    
    public BigDecimal promptForBigDecimal(String prompt, BigDecimal min, BigDecimal max);
    
    public LocalDate promptForLocalDate(String prompt, String pattern, LocalDate min, LocalDate max);
    
    public int promptForInt(String prompt, int min, int max);
    
    public String promptForString(String prompt, String exclude) throws ExclusionEntryException;

    public int getDao();

    public void displayMessage(String message);

    public TaxState promptForState(String prompt);

    public ProductType promptForProductType(String prompt);
    
}
