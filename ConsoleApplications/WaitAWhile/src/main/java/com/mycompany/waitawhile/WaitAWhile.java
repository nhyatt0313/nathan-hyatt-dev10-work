/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.waitawhile;

/**
 *
 * @author nhyat
 */
public class WaitAWhile {
    public static void main(String[] args) {
        int timeNow = 5;
        int bedTime = 10;
        
        while (timeNow < bedTime){
            System.out.println("its only "+timeNow);
            System.out.println("I'll stay up longer.");
            timeNow++;
        }
        
        System.out.println("Oh.. its "+timeNow+" so I better go to bed.");
    }
}
