/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessmemore;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class GuessMeMore {

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);

        boolean terminate = false;
        int ans = rand.nextInt(201) - 10;
        while (!terminate) {

            System.out.print("Guess a number between -100 and +100: ");
            String guessStr = scan.nextLine();
            int guess = Integer.parseInt(guessStr);

            if (guess == ans) {
                System.out.println("You guessed it Right!");
                terminate = true;
            } else {
                System.out.print("That's not it, try again? (y/n) : ");
                if (scan.nextLine().equals("n")) {
                    terminate = true;
                    System.out.println("Coward!");
                }
            }
        }

    }
}
