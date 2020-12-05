/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.knockknock;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class KnockKnock {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        
        System.out.print("Knock Knock! Guess who!! ");
        String nameGuess = inputReader.nextLine();
        
        
        // changing the .equals to == would not work, sonce == looks at where the data is in memory, and doesn't compare the date directly
        // If we wanted to ignore the case difference, I would suggest using compareToIgnoreCase() method
        if(nameGuess.equals("Marty McFly")){
            System.out.println("Hey! That's right! I'm back!");
            System.out.println("... from the Future.");
            
        }
        else{
            System.out.println("Dude, do I look like " + nameGuess);
            
        }
    }
}
