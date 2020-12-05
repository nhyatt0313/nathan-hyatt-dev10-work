/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.staypositive;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class StayPositive {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scan.nextInt();
        
        System.out.println("Counting down from "+num);
        int iterator = 1;
        for (int i=num; i>=0; i--){
            if (iterator == 10){
                System.out.println(i);
                iterator = 1;
            }
            else{
                System.out.print(i+" ");
                iterator++;
            }
        }
    }
}
