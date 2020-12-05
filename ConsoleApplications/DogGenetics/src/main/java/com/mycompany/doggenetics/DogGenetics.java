/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.doggenetics;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class DogGenetics {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("What is your dogs name? ");
        String dogName = scan.nextLine();
        System.out.println("... calculating genetic history of "+dogName+"...");
        System.out.println("");
        String[] breedNames = { "Shorthair", "Pointer", "Chihuaahua", "pug", "Lab" };
        
        int[] breeds = new int[5];
        int range = 100;
        for (int i = 0; i < 4; i++) {
            breeds[i] = rand.nextInt(range+1);
            range -= breeds[i];
        }
        breeds[4] = range;
        
        System.out.println(dogName+ " is:");
        
        for (int i = 0; i < 5; i++) {
            System.out.println(breeds[i]+"% "+breedNames[i]);
        }
    }
}
