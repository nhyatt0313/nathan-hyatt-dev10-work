/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lovesme;

/**
 *
 * @author nhyat
 */
public class LovesMe {
    public static void main(String[] args) {
        
        // both while or do while loop could have worked, but since I already initialized my counter variable
        // above the loop I know for sure that a while loop will execute at least once, so I do not need the 
        // functionality of a do-while loop. if i didn't initialize my counter, i would use do-while.
        
        int counter = 0;
        while (counter < 34){
            System.out.println("It Loves me!");
            System.out.println("It Loves me NOT!");
            counter++;
        }
        System.out.println("Aw :(");
    }
}
