/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trivianight;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class TriviaNight {
    public static void main(String[] args) {
        System.out.println("Welcome to Trivia Night!");
        Scanner answer = new Scanner(System.in);
        
        System.out.println("QUESTION 1: ");
        System.out.println("What is the Lowest Level Programming Language?");
        System.out.println("1) Source Code");
        System.out.println("2) Assembly Language");
        System.out.println("3) C#");
        System.out.println("4) Machine Code");
        
        System.out.println("");
        System.out.print("ANSWER: ");
        String answer1Str = answer.nextLine();
        
        System.out.println("QUESTION 2: ");
        System.out.println("Website Security CAPTCHA Forms are Descended From the Work of?");
        System.out.println("1) Grace Hopper");
        System.out.println("2) Alan Turing");
        System.out.println("3) Charles Babbage");
        System.out.println("4) Larry Page");
        
        System.out.println("");
        System.out.print("ANSWER: ");
        String answer2Str = answer.nextLine();
        
        System.out.println("QUESTIN 3: ");
        System.out.println("Which of these Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?");
        System.out.println("1) Serenity");
        System.out.println("2) The Battlestar Gallactica");
        System.out.println("3) The USS Enterprise");
        System.out.println("4) The Millenium Falcon");
        
        System.out.println("");
        System.out.print("ANSWER: ");
        String answer3Str = answer.nextLine();
    
        System.out.println("");
        int correct1 = 4;
        int correct2 = 2;
        int correct3 = 3;
        
        int answer1 = 0;
        int answer2 = 0;
        int answer3 = 0;
        
        try{
            answer1 = Integer.parseInt(answer1Str);
            answer2 = Integer.parseInt(answer2Str);
            answer3 = Integer.parseInt(answer3Str);
        }
        catch(NumberFormatException e){
            System.out.println("It appears one of your answers was not formatted correctly."
                    + "please type the number you wish to select then hit enter.");
        }
        
        boolean grade1 = false;
        boolean grade2 = false;
        boolean grade3 = false;
        
        int countCorrect = 0;
        
        if (answer1 == correct1){
            grade1 = true;
            countCorrect++;
        }
        if (answer2 == correct2){
            grade2 = true;
            countCorrect++;
        }
        if (answer3 == correct3){
            grade3 = true;
            countCorrect++;
        }
        
        if (grade1 && grade2 && grade3){ // all right
            System.out.println("Congrats! you got them all right!");
        }
        else if (!(grade1 && grade2 && grade3)){ // all wrong
            System.out.println("Sombody needs to do their homework!");
        }
        else{
            System.out.println("You got "+countCorrect+" of the questions right.");
        }
        
        System.out.println("RESULTS:");
        System.out.println("QUESTION 1:");
        System.out.println("Your answer: "+answer1);
        System.out.println("Correct answer: "+correct1);
        
        System.out.println("QUESTION 2:");
        System.out.println("Your answer: "+answer2);
        System.out.println("Correct answer: "+correct2);
        
        System.out.println("QUESTION 3:");
        System.out.println("Your answer: "+answer3);
        System.out.println("Correct answer: "+correct3);
    }
}
