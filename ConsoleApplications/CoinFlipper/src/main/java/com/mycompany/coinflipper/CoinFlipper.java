/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coinflipper;

import java.util.Random;

/**
 *
 * @author nhyat
 */
public class CoinFlipper {
    public static void main(String[] args) {
        System.out.println("___ WELCOME TO COIN FLIPPER!!! ___");
        Random flip = new Random();
        boolean coinFlip = flip.nextBoolean();
        if (coinFlip){
            System.out.println("You got HEADS!");
        }
        else{
        System.out.println("You got TAILS!");
        }
    }
}
