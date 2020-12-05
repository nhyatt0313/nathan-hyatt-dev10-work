/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.loopexamples;

import java.util.Random;

/**
 *
 * @author nhyat
 */
public class LoopExamples {
    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            System.out.println(i);
        }
        int i2 = 1;
        while (i2 < 6) {
            System.out.println(i2);
            i2++;
        }
        
        Random rGen = new Random();
        int randomNum = rGen.nextInt(10) + 1;
        
        
        System.out.println("Random number while loop:");
        while (randomNum<8){
            System.out.println(randomNum);
            randomNum = rGen.nextInt(10) + 1;
        }
    }
}
