/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.opinionator;

import java.util.Random;

/**
 *
 * @author nhyat
 */
public class Opinionator {
    public static void main(String[] args) {
        Random randomizer = new Random();
        System.out.println("Decide on an animal: ");
        
        // originally set to nextInt(5) but the 5 is not inclusive, so we need to set it to nextInt(6) in order to have a possible output of 5
        int x = randomizer.nextInt(6);
        
        System.out.println("Number Chosen: "+x);
        
        switch(x){
            case 1:
                System.out.println("animal 1");
                break;
            case 2:
                System.out.println("animal 2");
                break;
            case 3:
                System.out.println("animal 3");
                break;
            case 4:
                System.out.println("animal 4");
                break;
            case 5:
                System.out.println("animal 5");
                break;
            default:
                System.out.println("animal default");
                break;
                
        }
    }
}
