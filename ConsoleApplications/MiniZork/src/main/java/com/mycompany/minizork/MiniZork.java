/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.minizork;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class MiniZork {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("You are standing in an open field west of a white house,");
        System.out.println("With a boarded front door.");
        System.out.println("There is a small mailbox here.");
        System.out.print("Go to the house, or open the mailbox? ");

        String action = userInput.nextLine();

        if (action.equals("open the mailbox")) {
            System.out.println("You open the mailbox.");
            System.out.println("It's really dark in there.");
            System.out.print("Look inside or stick your hand in? ");
            action = userInput.nextLine();

            if (action.equals("look inside")) {
                System.out.println("You peer inside the mailbox.");
                System.out.println("It's really very dark. So ... so very dark.");
                System.out.print("Run away or keep looking? ");
                action = userInput.nextLine();

                if (action.equals("keep looking")) {
                    System.out.println("Turns out, hanging out around dark places isn't a good idea.");
                    System.out.println("You've been eaten by a grue. The grue dances in rejoce of a meal");
                } else if (action.equals("run away")) {
                    System.out.println("You run away screaming across the fields, but it soon turns into a dance.");
                }
            } else if (action.equals("stick your hand in")) {
                System.out.println("Theres nothing inside.");
                System.out.println("Dance or beat up the mailbox");
                action = userInput.nextLine();
                if (action.equals("dance")){
                    System.out.println("I mean, you might as well right?!");
                }
                else if (action.equals("beat up the mailbox")){
                    System.out.println("The mailbox thinks you are very rude.");
                    System.out.println("Dance with the mailbox as a form of silent apology or apologize with words?");
                    action = userInput.nextLine();
                    if (action.equals("dance with the mailbox as a form of silent apology")){
                        System.out.println("The mailbox accepts your apology and you become friends.");
                    }
                    else if (action.equals("apologize with words")){
                        System.out.println("The mailbox knows your faking, and tho only resolution is dancing."
                                + "You dance with the mailbox, but you are not friends.");
                    }
                }
            
            }
        } else if (action.equals("go to the house")) {
            System.out.println("You go to the house.");
            System.out.println("The back door is open");
            System.out.print("go in or dance on the porch?");
            action = userInput.nextLine();
            if (action.equals("go in")){
                System.out.println("You go inside and are overwhelmed by a desire to dance");
                System.out.println("Dance or resist the urge?");
                action = userInput.nextLine();
                if (action.equals("dance")){
                    System.out.println("Finally! dancing feels sooooo good.");
                }
                else if (action.equals("resist the urge")){
                    System.out.println("You can't! you're dissapointed in yourself, but at least you're dancing.");
                }
            }
            else if (action.equals("dance on the porch")){
                System.out.println("Oh yeah, thats the stuff!");
            }
        }
    }
}
