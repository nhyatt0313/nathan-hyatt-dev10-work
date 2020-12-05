/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maybeitlovesme;

import java.util.Random;

/**
 *
 * @author nhyat
 */
public class MaybeItLovesMe {

    public static void main(String[] args) {

        Random rand = new Random();
        int numPetals = rand.nextInt((89 - 13) + 1) + 13; // between 13 and 89
        int counter = 0;
        
        while (counter < numPetals) {
            if (counter % 2 == 0) {
                System.out.println("It Loves me!"); // even case
            } else {
                System.out.println("It Loves me NOT!"); // odd case
            }
            counter++;
        }
        if (numPetals%2 == 0) {
            System.out.println("Aw :(");
        }
        else {
            System.out.println("Woo! :)");
        }
    }
}
