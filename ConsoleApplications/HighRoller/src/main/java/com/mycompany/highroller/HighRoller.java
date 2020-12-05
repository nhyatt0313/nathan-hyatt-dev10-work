/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.highroller;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class HighRoller {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("--- Welcome to DiceRoller ---");
        System.out.println("Enter number of sides on die: ");
        int sides = scan.nextInt();
        
        Random rand = new Random();
        
        int roll = rand.nextInt(sides) + 1;
        
        System.out.println("Lets Roll...");
        System.out.println("You Rolled: "+roll);
        
        if (roll == 0){
            System.out.println("you rolled the min :(");
        }
        if (roll == sides){
            System.out.println("You rolled the max!");
        }
    }
}
