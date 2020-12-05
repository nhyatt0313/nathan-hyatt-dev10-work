/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.doitbetter;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class DoItBetter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("How many miles can you run?");
        String milesStr = in.nextLine();
        double miles = Double.parseDouble(milesStr);
        miles = miles*2;
        miles = miles+1;
        System.out.println("Pathetic! I can run "+miles);
        System.out.println("How many hot dogs can you eat?");
        String hotDogsStr = in.nextLine();
        double hotDogs = Double.parseDouble(hotDogsStr);
        hotDogs = hotDogs*2;
        hotDogs = hotDogs+1;
        System.out.println("well, I can eat "+hotDogs);
        System.out.println("You're not really good at anything are you?");
    }
}
