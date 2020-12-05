/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interestcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class InterestCalculator {

    static Scanner sc = new Scanner(System.in);

    public void calculate() {

        BigDecimal aiRate = prompt("What is the annual interest rate? ");
        BigDecimal currentBalance = prompt("What is the initial amount of principal? ");
        BigDecimal initialBalance = currentBalance;
        int years;
        int period;
        BigDecimal quarterlyInterest;
        try {
            years = promptInt("what is the number of years? ");
            period = promptInt("Do you want quarterly, monthly, or daily interest compound periods? (4, 12, 365)");
            quarterlyInterest = aiRate.divide(new BigDecimal(period)); //, 2, RoundingMode.HALF_UP);
        } catch (NumberFormatException e) {
            System.out.println("Entry incorrectly formatted");
            years = 0;
            period = 0;
            quarterlyInterest = new BigDecimal("0");
        }
        

        for (int i = 0; i < years; i++) {
            for (int j = 1; j <= period; j++) {
                
                /*
                
                QUESTION: When should rounding occur while doing money calculations? After every calculation? or just before printing?
                */
                
                BigDecimal cbMultiplier = quarterlyInterest.divide(new BigDecimal("100")); //, 2, RoundingMode.HALF_UP);
                //cbMultiplier = cbMultiplier.setScale(2, RoundingMode.HALF_UP);
                cbMultiplier = cbMultiplier.add(new BigDecimal("1"));
                //cbMultiplier = cbMultiplier.setScale(2, RoundingMode.HALF_UP);
                currentBalance = currentBalance.multiply(cbMultiplier);
                //currentBalance = currentBalance.setScale(2, RoundingMode.HALF_UP);
                
            }
            currentBalance = currentBalance.setScale(2, RoundingMode.HALF_UP);
            System.out.println("Interest rate: " + aiRate);
            System.out.println("Initial balance: " + initialBalance);
            System.out.println("Number of years left: " + (years - i - 1));
            System.out.println("Compound period: " + period);
            System.out.println("Quarterly interest rate: " + quarterlyInterest);
            System.out.println("");
            System.out.println("Current balance: " + currentBalance);
            System.out.println("");
            initialBalance = currentBalance.setScale(2, RoundingMode.HALF_UP);
        }

    }

    static BigDecimal prompt(String question) {
        System.out.println(question);
        sc = new Scanner(System.in);
        String answer = sc.nextLine();
        BigDecimal bd = new BigDecimal(answer);
        bd.setScale(2, RoundingMode.HALF_UP);
        return bd;
    }

    static int promptInt(String question) throws NumberFormatException {
        System.out.println(question);
        sc = new Scanner(System.in);
        String answer = sc.nextLine();
        return Integer.parseInt(answer);
    }
}
