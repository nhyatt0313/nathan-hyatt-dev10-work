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
public class SimpleService {

    public int addNumbers(int number1, int number2) {
        return number1 + number2;
    }

    public int subtractNumbers(int number1, int number2) {
        return number1 - number2;
    }

    public int multiplyNumbers(int number1, int number2) {
        return number1 * number2;
    }

    public int divideNumbers(int number1, int number2) throws ArithmeticException {
        return number1 / number2;
    }

}
