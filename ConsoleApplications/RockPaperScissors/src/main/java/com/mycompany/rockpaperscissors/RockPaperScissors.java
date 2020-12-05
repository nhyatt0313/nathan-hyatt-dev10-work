/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class RockPaperScissors {

    public void run() {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        boolean terminate = false;
        while (!terminate) {
            System.out.print("Enter the number of rounnds you wish to play: ");
            String roundsStr = scan.nextLine();
            int rounds = Integer.parseInt(roundsStr);

            int userWin = 0;
            int compWin = 0;
            int tie = 0;

            if (rounds < 1 || rounds > 10) {
                System.out.println("Input was not valid.");
                terminate = true;
            } else {
                for (int i = 0; i < rounds; i++) {
                    System.out.println("ROUND "+(i+1));
                    System.out.print("Choose rock paper or scissors: ");
                    String user = scan.nextLine();

                    int compInt = rand.nextInt(3);
                    String comp;
                    switch (compInt) {
                        case 0:
                            comp = "rock";
                            break;
                        case 1:
                            comp = "paper";
                            break;
                        case 2:
                            comp = "scissors";
                            break;
                        default:
                            comp = "ERROR";
                            break;
                    }
                    System.out.println("Computer chose: "+comp);
                    if (user.equalsIgnoreCase(comp)) {
                        tie++;
                        System.out.println("Round "+(i+1)+" was a tie");
                    } else if (user.equalsIgnoreCase("rock")) {
                        if (comp.equalsIgnoreCase("paper")){
                            compWin++;
                            System.out.println("Computer won round "+(i+1));
                        } else {
                            userWin++;
                            System.out.println("You won round "+(i+1));
                        }
                    } else if (user.equalsIgnoreCase("paper")){
                        if (comp.equalsIgnoreCase("scissors")){
                            compWin++;
                            System.out.println("Computer won round "+(i+1));
                        } else {
                            userWin++;
                            System.out.println("You won round "+(i+1));
                        }
                    } else {
                        // user chose scissors
                        if (comp.equalsIgnoreCase("rock")){
                            compWin++;
                            System.out.println("Computer won round "+(i+1));
                        } else {
                            userWin++;
                            System.out.println("You won round "+(i+1));
                        }
                    }
                    System.out.println();

                }
                System.out.println();
                System.out.println("RESULTS:");
                System.out.println("You had "+userWin+" wins!");
                System.out.println("Computer had "+compWin+" wins!");
                System.out.println("There were "+tie+" ties");
                
                System.out.println();
                
                if (userWin > compWin){
                    System.out.println("You were the overall winner!");
                } else if (userWin == compWin){
                    System.out.println("The overall result is a tie");
                } else {
                    System.out.println("The computer was the overall winner");
                }
                
                System.out.println();
                
                System.out.print("Play again (yes/no) ");
                String again = scan.nextLine();
                if (again.equalsIgnoreCase("no")){
                    System.out.println("Thanks for playing!");
                    terminate = true;
                }
                System.out.println();
            }
        }
    }
}
