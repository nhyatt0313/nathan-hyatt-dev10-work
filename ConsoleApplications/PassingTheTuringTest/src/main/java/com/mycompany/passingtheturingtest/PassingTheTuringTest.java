/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.passingtheturingtest;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class PassingTheTuringTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("What is your name?");
        String name = input.nextLine();
        
        System.out.println("Hello, "+name+"! My name is Irrelevant.");
        System.out.println("What is your favorite color, "+name+"?");
        String color = input.nextLine();
        
        System.out.println("Ooh, "+color+"? thats gross! I like light-"+color+" much better.");
        System.out.println("What is your favorite fruit, "+name+"?");
        String fruit = input.nextLine();
        
        System.out.println(fruit+" is okay I guess.");
        System.out.println("What's your favorite number?");
        String numberStr = input.nextLine();
        double number = Double.parseDouble(numberStr);
        
        System.out.println("Nice! mine is 3.1415. Did you know that a circle with a radius of "+number+" has an area of "+3.1415*number*number+"?");
        System.out.println("Pretty neat right?!");
        System.out.println("Well...? That wasn't rhetorical, "+name+". Describe how neat that is to me.");
        String neatness = input.nextLine();
        
        System.out.println("No, you're "+neatness+", "+name+"! bye.");
        
        input.close();
        
    }
}
