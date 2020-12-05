/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortimes;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class ForTimes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int num = sc.nextInt();
        sc.nextLine(); // consume
        int answer;
        int points = 0;
        for (int i=1; i<=15; i++){
            System.out.print(num+" * "+i+" = ");
            answer = sc.nextInt();
            if (answer == num*i){
                points++;
            }
        }
        System.out.println("you got "+points+" right");
        if (points < (15*.5)){
            System.out.println("you should study more");
        }
    }
}
