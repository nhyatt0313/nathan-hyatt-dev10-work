/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luckysevens;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class LuckySevens {

    public void run() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your bet: ");
        int bet;
        boolean valid = false;
        do {
            bet = scan.nextInt();
            if (bet > 0) {
                valid = true;
            }

        } while (!valid);

        int initialBet = bet;
        int numRolls = 0;
        int numRollsAtMax = 0;
        int maxBet = bet;

        while (bet > 0) {
            int roll = rollDice();
            
            if (roll == 7) {
                bet += 4;
            } else {
                bet--;
                
            }
            if (maxBet < bet){
                maxBet = bet;
                numRollsAtMax = numRolls;
            }
            numRolls++;
        }
        System.out.println("initial bet: " + initialBet);
        System.out.println("numer of rolls: " + numRolls);
        System.out.println("max money held: "+maxBet);
        System.out.println("number of rolls at max: "+numRollsAtMax);
    }

    static int rollDice() {
        Random rand = new Random();
        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        return die1 + die2;
    }

}
