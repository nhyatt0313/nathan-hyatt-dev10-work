/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplecalculator;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class App {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("|*************************************|");
        System.out.println("|  * * *   SIMPLE CALCULATOR   * * *  |");
        System.out.println("|*************************************|");

        boolean terminate = false;
        while (!terminate) {
            System.out.println("|*************************************|");
            System.out.println("|    ...Please choose an option...    |");
            System.out.println("| add | subtract | multiply | divide  |");
            System.out.println("|    ...or type 'quit' to quit...     |");
            System.out.println("|*************************************|");
            System.out.print("| OPERATION: ");
            boolean valid;
            String operation;
            boolean firstTime = true;
            do {
                if (!firstTime) {    // doesnt run on the first iteration
                    System.out.println("| Incorrect input, try again...");
                    System.out.print("| OPERATION: ");

                }
                operation = scan.nextLine();
                valid = operation.equalsIgnoreCase("add") || operation.equalsIgnoreCase("subtract")
                        || operation.equalsIgnoreCase("multiply") || operation.equalsIgnoreCase("divide")
                        || operation.equalsIgnoreCase("quit");

                firstTime = false;
            } while (!valid);

            double[] operands = new double[2];

            // only happens if the input is valid and not quit
            if (!operation.equalsIgnoreCase("quit")) {
                System.out.println("|*************************************|");
                System.out.println("| Enter the numbers you wish to " + operation);
                if (operation.equalsIgnoreCase("divide")) {
                    System.out.print("| Numerator: ");
                    operands[0] = Double.parseDouble(scan.nextLine());
                    System.out.print("| Denominator: ");
                    operands[1] = Double.parseDouble(scan.nextLine());
                } else {
                    System.out.print("| Number 1: ");
                    operands[0] = Double.parseDouble(scan.nextLine());
                    System.out.print("| Number 2: ");
                    operands[1] = Double.parseDouble(scan.nextLine());
                }
            }

            SimpleCalculator calc = new SimpleCalculator();

            double result = 0;
            if (operation.equalsIgnoreCase("add")) {
                result = calc.add(operands);

            } else if (operation.equalsIgnoreCase("subtract")) {
                result = calc.subtract(operands);

            } else if (operation.equalsIgnoreCase("multiply")) {
                result = calc.multiply(operands);

            } else if (operation.equalsIgnoreCase("divide")) {
                result = calc.divide(operands);

            } else if (operation.equalsIgnoreCase("quit")) {
                System.out.println("| Thank you!");
                terminate = true;
            } else {
                System.out.println("Something unexpected happened...");
            }
            if (!operation.equalsIgnoreCase("quit")) {
                System.out.println("| RESULT: " + result);
            }
        }
    }
}
