/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springforwardfallback;

/**
 *
 * @author nhyat
 */
public class SpringForwardFallBack {
    public static void main(String[] args) {
        System.out.println("It's Spring...!");
        for (int i = 0; i < 10; i++) { // start: 0, stop: 9
            System.out.print(i + ", ");
        }
        
        // new loop
        for (int i = 1; i < 11; i++) { // start: 1, stop: 10
            System.out.print(i + ", ");
        }

        System.out.println("\nOh no, it's fall...");
        for (int i = 10; i > 0; i--) { // Start: 10, stop: 1
            System.out.print(i + ", ");
        }
    }
}
