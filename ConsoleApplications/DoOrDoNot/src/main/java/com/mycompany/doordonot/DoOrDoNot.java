/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.doordonot;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class DoOrDoNot {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Should I do it? (y/n) ");
        boolean doIt;

        if (input.next().equals("y")) {
            doIt = true; // DO IT!
        } else {
            doIt = false;
        }

        boolean iDidIt = false;

//        do {
//            iDidIt = true;
//            break;
//        } while (doIt);

        while(doIt){
            iDidIt = true;
            break;
        }
        // not sure what they are asking us to do. the break statement makes this a 
        // block of code that only gets run once no matter what. its logically equivallent
        // to writing iDidIt = true; without the while or do-while loop...
        // ... without the break statement, both of these are infinite loops...

        if (doIt && iDidIt) {
            System.out.println("I did it!");
        } else if (!doIt && iDidIt) {
            System.out.println("I know you said not to ... but I totally did anyways.");
        } else {
            System.out.println("Don't look at me, I didn't do anything!");
        }
    }
}
