/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplecalculator;

/**
 *
 * @author nhyat
 */
public class SimpleCalculator {
    
    public double add(double[] operands){
        return operands[0] + operands[1];
    }
    public double subtract(double[] operands){
        return operands[0] - operands[1];
    }
    public double multiply(double[] operands){
        return operands[0] * operands[1];
    }
    public double divide(double[] operands){
        return operands[0] / operands[1];
    }
}
