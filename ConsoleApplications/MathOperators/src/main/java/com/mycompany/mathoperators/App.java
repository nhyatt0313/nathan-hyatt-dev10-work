/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mathoperators;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Number 1: ");
        String input1 = sc.nextLine();
        System.out.println("Number 2: ");
        String input2 = sc.nextLine();

        int op1 = Integer.parseInt(input1);
        int op2 = Integer.parseInt(input2);
        showResults(op1, op2);
    }

    public static enum Operator {
        PLUS, MINUS, MULTIPLY, DIVIDE
    }

    

    public static void showResults(int op1, int op2) {
        for (Operator op : Operator.values()) {
            System.out.println(op + "RESULT: " + calculate(op, op1, op2));
        }
    }

    public static int calculate(Operator op, int operand1, int operand2) {
        switch (op) {
            case PLUS:
                return operand1 + operand2;
            case MINUS:
                return operand1 - operand2;
            case MULTIPLY:
                return operand1 * operand2;
            case DIVIDE:
                return operand1 / operand2;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
