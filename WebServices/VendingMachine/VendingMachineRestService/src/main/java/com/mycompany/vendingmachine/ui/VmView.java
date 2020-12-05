/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.ui;

import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nhyat
 */
@Component
public class VmView {

    @Autowired
    private UserIO io;

//    public VmView(UserIO io) {
//        this.io = io;
//    }
    
    

    public String displayMenuGetString(String prompt, List<Snack> snacks) {
        displayMenu(snacks);
        return getString(prompt);
    }

    public BigDecimal getBigDecimal(String prompt) {
        return io.readBigDecimal(prompt);
    }

    private String getString(String prompt) {
        return io.readString(prompt);
    }

    private void displayMenu(List<Snack> snacks) {

        snacks.stream()
                .filter(s -> (s.getNumInStock() == 0))
                .forEachOrdered(s -> { 
                    //snacks.remove(s);
                    s.setType("--- SOLD OUT ---");
                    s.setPrice(new BigDecimal("0.00"));
        });
        
        snacks.sort(Comparator.comparing(s -> s.getId()));

        snacks.forEach((Snack s) -> {
            io.println("| " + s.getId() + " : " + s.getType() + " : " + s.getPrice() + " |");
        });
    }

    public void displayExit() {
        io.println("GOODBYE!");
    }

    public String getItemChoice(String prompt) {
        return io.readString(prompt);
    }

    public void printChange(Change c) {
        io.println("Here is your change: \n"
                  +"Dollars: "+c.getDollars()+"\n"
                  +"Quarters: "+c.getQuarters()+"\n"
                  +"Dimes: "+c.getDimes()+"\n"
                  +"Nickels: "+c.getNickels()+"\n"
                  +"Pennies: "+c.getPennies());
    }

    public void displaySuccess(String type) {
        io.println("\nEnjoy your "+type.trim()+"!");
    }
    
    public void displayErrorMessage(String message){
        io.println(message);
    }

}
