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
public class SimpleController {

    SimpleView view = new SimpleView();
    SimpleService service = new SimpleService();
    UserIO io = new UserIO();

    public void run() {
        boolean playAgain = true;
        while (playAgain) {
            // Get menu selection from user
            int menuSelection = view.getMenuSelection();

            switch (menuSelection) {
                case 1:
                    add();
                    break;
                case 2:
                    subtract();
                    break;
                case 3:
                    multiply();
                    break;
                case 4:
                    divide();
                    break;
                case 5:
                    exit();
                    playAgain = false;
                    break;
                default:
                    io.print("Something went wrong");
                    break;
            }
        }
    }

    private void add() {
        // get input
        int number1 = view.getUserInt();
        int number2 = view.getUserInt();
        // calculate
        int result = service.addNumbers(number1, number2);
        // show result
        view.printAddResult(number1, number2, result);
    }

    private void subtract() {
        // get input
        int number1 = view.getUserInt();
        int number2 = view.getUserInt();
        // calculate
        int result = service.subtractNumbers(number1, number2);
        // show result
        view.printSubtractResult(number1, number2, result);
    }

    private void multiply() {
        // get input
        int number1 = view.getUserInt();
        int number2 = view.getUserInt();
        // calculate
        int result = service.multiplyNumbers(number1, number2);
        // show result
        view.printMultiplyResult(number1, number2, result);
    }

    private void divide() {
        // get input
        int number1 = view.getUserInt();
        int number2 = view.getUserInt();
        // calculate

        try {
            // show result
            int result = service.divideNumbers(number1, number2);
            view.printDivideResult(number1, number2, result);
        } catch (ArithmeticException ae) {
            view.printDivisionError();
        }
    }

    private void exit() {
        view.printExitStatement();
    }
}
