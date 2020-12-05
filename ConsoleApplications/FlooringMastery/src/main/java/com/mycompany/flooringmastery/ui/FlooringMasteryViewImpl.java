/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.ui;

import com.mycompany.flooringmastery.ProductType;
import com.mycompany.flooringmastery.TaxState;
import com.mycompany.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author nhyat
 */
public class FlooringMasteryViewImpl implements FlooringMasteryView {

    private final UserIO io;
    
    private final String L_MARGIN = "\t ";

    public FlooringMasteryViewImpl(UserIO io) {
        this.io = io;
    }

    @Override
    public void displayOrder(Order order) {
        io.println("");
        io.println("");
        io.println("ORDER " + order.getOrderNumber() + ":");
        io.println(order.toString());
    }

    @Override
    public void displayOrdersOnDate(LocalDate date, ArrayList<Order> orderList) {
        io.println("");
        io.println("");
        io.println("ORDERS ON DATE: " + date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        // order the list by the order numbers -- the numbers could be out of order at this point
        orderList.sort(Comparator.comparing(o -> o.getOrderNumber()));
        orderList.stream().forEachOrdered(o -> io.println(o.toString()));
    }

    @Override
    public void displayError(String message) {
        io.println(message);
    }

    @Override
    public int displayMenuGetChoice() {
        io.println("\n"
                + "\t___________________________\n"
                + "\t Flooring Program          \n"
                + "\t___________________________\n"
                + "\t 1. Display Orders         \n"
                + "\t 2. Add an Order           \n"
                + "\t 3. Edit an Order          \n"
                + "\t 4. Remove an Order        \n"
                + "\t 5. Save Current Work      \n"
                + "\t 6. Quit                   \n"
                + "\t___________________________\n");
        return io.readInt( "Enter Choice: ", 1, 6);

    }

    @Override
    public void displayAddSuccessMessaage(Order order) {
        io.println("ORDER ADDED: " + order.toString());
        io.println("ORDER " + order.getOrderNumber() + " SUCCESSFULLY ADDED");

    }

    @Override
    public void displayRemoveSuccessMessage(Order order) {
        io.println("ORDER REMOVED: " + order.toString());
        io.println("ORDER " + order.getOrderNumber() + " SUCCESSFULLY REMOVED");
    }

    @Override
    public void displayEditSuccessMessage(Order before, Order after) {
        io.println("ORDER " + before.getOrderNumber() + " SUCCESSFULLY CHANGED");
        io.println("BEFORE CHANGES: " + before.toString());
        io.println(" AFTER CHANGES: " + after.toString());
    }

    @Override
    public BigDecimal promptForBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        return io.readBigDecimal(prompt, min, max);
    }

    @Override
    public LocalDate promptForLocalDate(String prompt, String pattern, LocalDate min, LocalDate max) {
        return io.readLocalDate(prompt, pattern, min, max);
    }

    @Override
    public int promptForInt(String prompt, int min, int max) {
        return io.readInt(prompt, min, max);
    }

    @Override
    public String promptForString(String prompt, String exclude) {
        boolean valid = true;
        String s;
        do {
            s = io.readString(prompt);
            if (s.contains(exclude)) {
                displayMessage("Delimeter value :: cannot be used - try again");
                valid = false;
            } else {
                valid = true;
            }
        } while (!valid);
        return s;
    }

    @Override
    public int getDao() {
        return io.readInt("___________________________\n"
                + "\t Flooring Mastery Startup  \n"
                + "\t___________________________\n"
                + "\t 1. Run in Production Mode \n"
                + "\t 2. Run in Training Mode   \n"
                + "\t___________________________\n"
                + "\t Please select an option:", 1, 2);
    }

    @Override
    public void displayMessage(String message) {
        io.print(message);
    }

    @Override
    public TaxState promptForState(String prompt) {
        return TaxState.valueOf(io.readStateString(prompt));
    }

    @Override
    public ProductType promptForProductType(String prompt) {
        return ProductType.valueOf(io.readProdTypeString(prompt));
    }

}
