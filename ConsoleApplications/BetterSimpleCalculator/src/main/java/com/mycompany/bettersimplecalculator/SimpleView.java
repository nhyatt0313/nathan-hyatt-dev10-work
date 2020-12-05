/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bettersimplecalculator;


/**
 *
 * @author nhyat
 */
public class SimpleView {

    final UserIOInterface io = new UserIO();

    public int getMenuSelection() {
        io.print("Calculator Menu:");
        io.print("1. add");
        io.print("2. subrtact");
        io.print("3. multiply");
        io.print("4. divide");
        io.print("5. quit");

        int selection = io.readInt("choose selection: ", 1, 5);

        return selection;
    }

    public int getUserInt() {
        return io.readInt("Please enter a number: ");
    }

    public void printAddResult(int number1, int number2, int result) {
        String message = number1 + " plus " + number2 + " is " + result;
        io.print(message);
    }

    public void printSubtractResult(int number1, int number2, int result) {
        String message = number1 + " minus " + number2 + " is " + result;
        io.print(message);
    }

    public void printMultiplyResult(int number1, int number2, int result) {
        String message = number1 + " times " + number2 + " is " + result;
        io.print(message);
    }

    public void printDivideResult(int number1, int number2, int result) {
        String message = number1 + " divided by " + number2 + " is " + result;
        io.print(message);
    }

    void printDivisionError() {
        io.print("Cannot divide by zero, because of math.");
    }

    void printExitStatement() {
        io.print("Thanks for mathing, have a nice day!");
    }
}
