///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.vendingmachine.controller;
//
//import com.mycompany.vendingmachine.Id;
//import com.mycompany.vendingmachine.exception.VmInsufficientFundsException;
//import com.mycompany.vendingmachine.exception.VmInvalidEntryException;
//import com.mycompany.vendingmachine.exception.VmOutOfStockException;
//import com.mycompany.vendingmachine.exception.VmPersistenceException;
//import com.mycompany.vendingmachine.service.VmService;
//import com.mycompany.vendingmachine.ui.VmView;
//import java.math.BigDecimal;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
///**
// *
// * @author nhyat
// */
//@Controller
//public class VmController {
//
//    @Autowired
//    private VmService service;
//    @Autowired
//    private VmView view;
//
////    public VmController(VmService service, VmView view) {
////        this.service = service;
////        this.view = view;
////    }
//
//    public void run() {
//
//        String entry;
//        try {
//            entry = view.displayMenuGetString("Would you like to buy something? ", service.getAllSnacks());
//        } catch (VmPersistenceException ex) {
//            entry = "no";
//            view.displayErrorMessage(ex.getMessage());
//        }
//        boolean yes = !terminate(entry);
//        if (yes) {
//            do {
//
//                try {
//
//                    BigDecimal money = view.getBigDecimal("Please enter your money: ");
//                    Id id = getItemChoice();
//                    BigDecimal price = service.getPrice(id);
//                    service.checkSufficientFunds(money, price);
//                    service.buyItem(id);
//                    displaySuccess(id);
//                    view.printChange(service.calculateChange(price, money));
//
//                    yes = !terminate(null);
//
//                } catch (VmOutOfStockException | VmInvalidEntryException
//                        | VmPersistenceException | VmInsufficientFundsException ex) {
//                    view.displayErrorMessage(ex.getMessage());
//                }
//            } while (yes);
//            view.displayExit();
//        } 
//    }
//        
//    /*
//    terminate(String input) takes a string and asks for a new value if input is null, 
//    otherwise it uses the given input to calculate valoidity.
//     */
//    private boolean terminate(String input) {
//        if (input == null) {
//            try {
//                input = view.displayMenuGetString("Would you like to buy someting else? ", service.getAllSnacks());
//            } catch (VmPersistenceException ex) {
//                view.displayErrorMessage(ex.getMessage());
//            }
//        }
//        if (input != null) {
//            return !(input.equalsIgnoreCase("y")
//                    || input.toLowerCase().startsWith("yes")
//                    || input.toLowerCase().startsWith("yea")
//                    || input.toLowerCase().startsWith("yep")
//                    || input.toLowerCase().startsWith("sure")
//                    || input.toLowerCase().startsWith("indeed"));
//        } else {
//            return true;
//        }
//
//    }
//
//    private Id getItemChoice() throws VmInvalidEntryException, VmPersistenceException, VmOutOfStockException {
//        
//        String choice = view.getItemChoice("What would you like? ");
//        return service.convertToId(choice);
//
//    }
//    
//    private void displaySuccess(Id id) throws VmInvalidEntryException, VmPersistenceException{
//        view.displaySuccess(service.getSnackById(id).getType());
//    }
//}
