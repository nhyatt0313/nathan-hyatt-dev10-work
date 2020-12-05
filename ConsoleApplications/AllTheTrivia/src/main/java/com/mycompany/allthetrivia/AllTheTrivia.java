/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.allthetrivia;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class AllTheTrivia {
    public static void main(String[] args) {
        Scanner trivia = new Scanner(System.in);
        System.out.println("1,024 Gigs is equal to what? ");
        String ans1 = trivia.nextLine();
        System.out.println("What is the only planet to rotate clockwise in our solar system? ");
        String ans2 = trivia.nextLine();
        System.out.println("What planet has the largest volcano? ");
        String ans3 = trivia.nextLine();
        System.out.println("What is the most abundant element in earths atmosphere? ");
        String ans4 = trivia.nextLine();
        
        System.out.println("WOW! I did not know that 1,024 Gigs is "+ans3+"! never even heard of that.");
        System.out.println("I feel like I've learned a lot here. I didn'e know any of these. But now i know that");
        System.out.println("the only planet to rotate clockwise is "+ans1+" and that the largest volcano is on ");
        System.out.println(ans4+". I definitely didn't know that the most abundant element in earths atmosphere is "+ans2);
        System.out.println("That's WILD!!");
        
    }
}
