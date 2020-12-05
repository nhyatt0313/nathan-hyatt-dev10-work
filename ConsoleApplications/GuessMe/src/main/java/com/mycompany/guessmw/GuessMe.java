/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessmw;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class GuessMe {
    static final int VALUE = 7; 
    public static void main(String[] args) {
        System.out.print("Guess a number between 1 and 10: ");
        Scanner in = new Scanner(System.in);
        int input = in.nextInt();
        if (input == VALUE){
            System.out.println("WOW! nice guess! Thats the number I was thinking of too.");
        }
        else if (input > VALUE){
            System.out.println("Too High! I was thinking "+VALUE);
        }
        else if(input < VALUE){
            System.out.println("Too Low! I was thinking "+VALUE);
        }
    }
}
