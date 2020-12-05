/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessmefinally;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class GuessMeFinally {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);

        boolean terminate = false;
        int ans = rand.nextInt(201) - 10;
        boolean firstTime = true;
        while (!terminate) {

            System.out.print("Guess a number between -100 and +100: ");
            String guessStr = scan.nextLine();
            int guess = Integer.parseInt(guessStr);

            if (guess == ans && firstTime) {
                System.out.println("You guessed it right on the first try!");
                terminate = true;
            } 
            else if (guess == ans){
                System.out.println("You guessed it right finally!");
                terminate = true;
            }else {
                System.out.print("That's not it, try again? (y/n) : ");
                String yesNo = scan.nextLine();
                if (yesNo.equals("n")) {
                    terminate = true;
                    System.out.println("Coward!");
                }
                // built in bug for testing purposes.
                if (yesNo.equals("show")){
                    System.out.println("ANSWER: "+ans);
                }
            }
            firstTime = false;
        }
    }
}
