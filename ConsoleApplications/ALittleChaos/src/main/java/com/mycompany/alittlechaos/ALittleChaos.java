/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alittlechaos;

import java.util.Random;

/**
 *
 * @author nhyat
 */
public class ALittleChaos {
    public static void main(String[] args) {
        Random randomizer = new Random();
        
        System.out.println("Random can make integers: "+randomizer.nextInt());
        System.out.println("Or a double: " + randomizer.nextDouble());
        System.out.println("Or even a boolean: " + randomizer.nextBoolean());
        
        int num = randomizer.nextInt(100);
        
        // values from 0 to 100
        System.out.println(randomizer.nextInt(101));
        System.out.println(randomizer.nextInt(101));
        System.out.println(randomizer.nextInt(101));
        System.out.println(randomizer.nextInt(101));
        System.out.println(randomizer.nextInt(101));
        System.out.println(randomizer.nextInt(101));
        System.out.println(randomizer.nextInt(101));
        System.out.println(randomizer.nextInt(101));
        System.out.println(randomizer.nextInt(101));
        System.out.println(randomizer.nextInt(101));
        System.out.println(randomizer.nextInt(101));
        System.out.println(randomizer.nextInt(101));
    }
}
