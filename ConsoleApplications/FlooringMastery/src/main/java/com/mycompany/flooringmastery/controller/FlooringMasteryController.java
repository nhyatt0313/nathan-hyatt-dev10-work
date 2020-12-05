/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.controller;

import com.mycompany.flooringmastery.ProductType;
import com.mycompany.flooringmastery.TaxState;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import com.mycompany.flooringmastery.exception.ExclusionEntryException;
import com.mycompany.flooringmastery.exception.PersistenceException;
import com.mycompany.flooringmastery.service.FlooringMasteryService;
import com.mycompany.flooringmastery.ui.FlooringMasteryView;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author nhyat
 */
public class FlooringMasteryController {

    FlooringMasteryView view;
    FlooringMasteryService service;

    private final String DATE_PATTERN = "MM/dd/yyyy";

    /**
     *
     * @param view
     * @param service
     */
    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        try {
            service.readAllFiles();
        } catch (PersistenceException ex) {
            view.displayError("Error loading files.");
        }
        boolean quit = false;
        do {
            int choice = view.displayMenuGetChoice();
            switch (choice) {
                case 1:
                    // Display Orders
                    displayOrders();
                    break;
                case 2:
                    // Add an Order
                    addOrder();
                    break;
                case 3:
                    // Edit and Order
                    editOrder();
                    break;
                case 4:
                    // Remove an order
                    removeOrder();
                    break;
                case 5:
                    // Save Current Work
                    saveWork();
                    break;
                case 6:
                    // Quit
                    quit = true;
                    break;
                default:
                    view.displayError("Error: default statement reached in run()");
            }
        } while (!quit);
        view.displayMessage("Thanks for using Flooring Mastery");
    }

    private void displayOrders() {
        ArrayList<Order> oList = service.getOrdersByDate(view.promptForLocalDate("Enter Date : ", "MM/dd/yyyy", LocalDate.MIN, LocalDate.MAX));
        oList.stream().forEachOrdered(o -> view.displayOrder(o));
    }

    private void addOrder() {
        LocalDate d = view.promptForLocalDate("Enter Order Date (" + DATE_PATTERN + "): ", DATE_PATTERN, LocalDate.now(), LocalDate.MAX); // date needs to be added in the future, not the past
        Order o = null;
        try {
            Product p = new Product();
            Tax t = new Tax();
            o = new Order(p, t);
            o.setCustName(view.promptForString("Enter Customer Name: ", service.getDelim()));
            t.setState(view.promptForState("Enter State: "));
            p.setProdType(view.promptForProductType("Enter Product Type: "));
            o.setArea(view.promptForBigDecimal("Enter Area: ", BigDecimal.ZERO, new BigDecimal(Long.MAX_VALUE)));
            o.setProduct(service.getProduct(o.getProduct().getProdType()));
            o.setTax(service.getTax(o.getTax().getState()));

            view.displayMessage("");
            view.displayMessage("ORDER TO ADD: " + o.toString());

            String entry = view.promptForString("Do you wish to continue? (y/n) ", service.getDelim());

            // the following do while loop allows the user to cancel their choice upon review and not make changes
            boolean valid = true;
            do {
                if (entry.equals("y")) {
                    service.addOrder(d, o);
                } else if (entry.equals("n")) {
                    // do nothing - no changes will be made
                } else {
                    view.displayError("Invalid entry - please enter y or n");
                    entry = view.promptForString("Do you wish to continue? (y/n) ", service.getDelim());
                    valid = false; // invalid input
                }
            } while (valid == false);

        } catch (PersistenceException | ExclusionEntryException ex) {
            view.displayError(ex.getMessage() + " CAUSE: " + ex.getCause());
        }
        if (o != null) {
            view.displayAddSuccessMessaage(o);
        }
    }

    private void editOrder() {

        Order before;
        Order after = new Order(new Product(), new Tax());
        // get date from user
        LocalDate d = view.promptForLocalDate("Enter Order Date (" + DATE_PATTERN + "): ", DATE_PATTERN, LocalDate.now(), LocalDate.MAX);
        // get order to edit - remove from list
        try {

            // display orders on date
            ArrayList<Order> oList = service.getOrdersByDate(d);
            // get id from user
            // check order by id from list
            int maxOrderNumber = service.getMaxOrderNumber(d);
            if (oList.isEmpty()) {
                view.displayMessage("There are currently no orders on " + d.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " \n ");
                return;
            }
            view.displayMessage("The order numbers on " + d.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " are as follows: \n ");
            oList.stream().forEachOrdered(o -> view.displayMessage(Integer.toString(o.getOrderNumber()) + ", "));
            view.displayMessage("\n");

            String entry = view.promptForString("Do you wish to continue? (y/n) ", service.getDelim());

            boolean valid = true;
            do {
                if (entry.equals("y")) {
                    /*
                    The user wishes to proceed with the edit
                    the following functionality had to be implemented since 
                    the read methods in the view do not accept null as valid input
                    in order to get around this I read in each value as a string 
                    and converted the values for ensureing correct input
                    
                    - in the future I may add another parameter to my read methods in the 
                      view so that I have the option to accept null input, but since the majority 
                      of this program needed non-null inputs I implemented a work-around in the edit
                      method alone.
                     */

                    // get order
                    int id;
                    boolean askForId = true;
                    do { // get a valid order
                        id = view.promptForInt("Enter Order Number: ", 1, maxOrderNumber);

                        before = service.getOrder(d, id);
                        after.setOrderNumber(id);
                        if (after == null) {
                            view.displayMessage("\n");
                            view.displayError("Order doesn't exist - try again: ");
                            askForId = true;
                        } else {
                            view.displayMessage("\n");
                            // display the order to be edited
                            view.displayMessage("Order to be Edited: ");
                            view.displayOrder(before);
                            askForId = false;
                        }

                    } while (askForId);

                    boolean askForName = true;
                    do {
                        String custName = view.promptForString("Change Customer Name: ", service.getDelim());
                        if (!custName.isEmpty()) {
                            if (custName.contains(service.getDelim())) {
                                askForName = true;

                            } else {
                                after.setCustName(custName);
                                askForName = false;
                            }

                        } else {
                            after.setCustName(before.getCustName());
                            view.displayMessage("*** Customer name not changed *** \n");
                            askForName = false;
                        }
                    } while (askForName);

                    /*
                    The fllowing do-while loop gets the Tax State from the user
                    if the user does not enter anything no values will be changed.
                     */
                    boolean askForState;
                    do {
                        String state = view.promptForString("Change State: ", service.getDelim());
                        if (!state.isEmpty()) {
                            try {
                                TaxState ts = TaxState.valueOf(state.toUpperCase());
                                //o.getTax().setState(ts);
                                ArrayList<Tax> taxes = service.getAllTaxes();
                                Tax tax = taxes.stream().filter(t -> t.getState() == ts).findFirst().get(); // not sure if this will work or not
                                after.setTax(tax);
                                askForState = false;
                            } catch (PersistenceException | IllegalArgumentException e) {
                                view.displayError("Invalid input - try again!");
                                askForState = true;
                            }

                        } else {
                            after.setTax(before.getTax());
                            view.displayMessage("*** State not changed *** \n");
                            askForState = false;
                        }
                    } while (askForState);

                    /*
                    The fllowing do-while loop gets the Product Type from the user
                    if the user does not enter anything no values will be changed.
                     */
                    boolean askForProduct;
                    do {
                        String prodType = view.promptForString("Change Product Type: ", service.getDelim());
                        if (!prodType.isEmpty()) {
                            try {
                                ProductType pt = ProductType.valueOf(prodType.toUpperCase());
                                ArrayList<Product> products = service.getAllProducts();
                                for (Product p : products){
                                    if (p.getProdType() == pt){
                                        after.setProduct(p);
                                    }
                                }
                                askForProduct = false;
                            } catch (PersistenceException | IllegalArgumentException e) {
                                askForProduct = true;
                            }
                        } else {
                            after.setProduct(before.getProduct());
                            view.displayMessage("*** Product not changed ***\n");
                            askForProduct = false;
                        }
                    } while (askForProduct);

                    /*
                    The fllowing do-while loop gets the Area from the user
                    if the user does not enter anything no values will be changed.
                    it also ensures that the user enters a positive value
                     */
                    boolean askForArea;
                    do {
                        String area = view.promptForString("Change Area: ", service.getDelim());
                        if (!area.isEmpty()) {
                            try {
                                BigDecimal a = new BigDecimal(area);
                                if (a.compareTo(BigDecimal.ZERO) < 0) {
                                    askForArea = true;
                                    view.displayError("Area must be greater than 0!");
                                } else {
                                    askForArea = false;
                                }
                            } catch (NumberFormatException e) {
                                view.displayError("Invalid input - try again!");
                                askForArea = true;
                            }
                        } else {
                            after.setArea(before.getArea());
                            view.displayMessage("*** Area not changed ***\n");
                            askForArea = false;
                        }

                    } while (askForArea);

                    /*
                        The following values are set upon adding:        
                          materialCost
                          laborCost
                          totalTax
                          total;
                     */
                    
                    after = service.editOrder(d, service.getOrder(d, id), after);
                    view.displayEditSuccessMessage(before, after);

                } else if (entry.equals("n")) {
                    // do nothing - no changes will be made
                } else {
                    view.displayError("Invalid entry - please enter y or n");
                    entry = view.promptForString("Do you wish to continue? (y/n) ", service.getDelim());
                    valid = false; // invalid input
                }
            } while (!valid);

        } catch (ExclusionEntryException ex) {
            view.displayError(ex.getMessage());
        }
    }

    private void removeOrder() {
        LocalDate d = view.promptForLocalDate("Enter Order Date (" + DATE_PATTERN + "): ", DATE_PATTERN, LocalDate.now(), LocalDate.MAX); // date needs to be added in the future, not the past

        try {
            ArrayList<Order> oList = service.getOrdersByDate(d);

            if (oList.isEmpty()) {
                view.displayMessage("There are currently no orders on " + d.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " \n\t ");
                return;
            }
            // get id from user
            // check order by id from list
            int maxOrderNumber = service.getMaxOrderNumber(d);
            view.displayMessage("The order numbers on " + d.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "are as follows: \n");
            oList.stream().forEachOrdered(o -> view.displayMessage(Integer.toString(o.getOrderNumber()) + ", "));
            int id = view.promptForInt("Enter Order Number: ", 1, maxOrderNumber);
            Order o = service.getOrder(d, id);
            view.displayMessage("Order to be removed: ");
            view.displayOrder(o);

            String entry = view.promptForString("Do you wish to continue? (y/n) ", service.getDelim());

            // the following do while loop allows the user to cancel their choice upon review and not make changes
            boolean valid = true;
            do {
                if (entry.equals("y")) {
                    service.removeOrder(d, id);
                } else if (entry.equals("n")) {
                    // do nothing - no changes will be made
                } else {
                    view.displayError("Invalid entry - please enter y or n");
                    entry = view.promptForString("Do you wish to continue? (y/n) ", service.getDelim());
                    valid = false; // invalid input
                }
            } while (valid == false);

        } catch (PersistenceException | ExclusionEntryException ex) {

        }
    }

    private void saveWork() {
        try {
            service.saveWork();
            view.displayMessage("CURRENT WORK SUCESSFULLY SAVED!");
        } catch (PersistenceException ex) {
            view.displayError(ex.getMessage());
        }
    }

}
