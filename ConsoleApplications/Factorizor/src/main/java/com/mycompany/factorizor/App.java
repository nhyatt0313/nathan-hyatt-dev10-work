/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.factorizor;


/**
 *
 * @author nhyat
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Enter a number to be factored: ");
        Factorizor factor = new Factorizor();
        factor.factorize();
        
    }
}
