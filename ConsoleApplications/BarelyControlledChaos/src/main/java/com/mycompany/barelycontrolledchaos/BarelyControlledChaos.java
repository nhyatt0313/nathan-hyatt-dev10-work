/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.barelycontrolledchaos;

import java.util.Random;

/**
 *
 * @author nhyat
 */
public class BarelyControlledChaos {
    static Random rand = new Random();
    public static void main(String[] args) {
        String color  = color();
        String animal = animal();
        String colorAgain = color();
        int weight = number(5, 200);
        int distance = number(10, 20);
        int number = number(10000, 20000);
        int time = number(2, 6);
        

    System.out.println ("Once, when I was very small...");
    System.out.println ("I was chased by a " + color + ", "+ weight + "lb " + " miniature " + animal  + " for over " + distance + " miles!!");
    System.out.println ("I had to hide in a field of over "  + number + " " + colorAgain + " poppies for nearly " + time + " hours until it left me alone!");
    System.out.println ("\nIt was QUITE the experience, " + "let me tell you!");
    }
    static String color(){
        // random color
        int colorInt = rand.nextInt(3);
        String color;
        switch(colorInt) {
            case 0:
                color = "red";
                break;
            case 1:
                color = "blue";
                break;
            case 2:
                color = "green";
                break;
            default:
                color = "default";
                break;
                
        }
        return color;
    }
    static String animal(){
        // random animal
        int animalInt = rand.nextInt(3);
        String animal;
        switch(animalInt) {
            case 0:
                animal = "dog";
                break;
            case 1:
                animal = "cat";
                break;
            case 2:
                animal = "chicken";
                break;
            default:
                animal = "default";
                break;
                
        }
        return animal;
    }
    static int number(int num1, int num2){
        return rand.nextInt((num2-num1)+1)+num1;
    }
}
        
    

