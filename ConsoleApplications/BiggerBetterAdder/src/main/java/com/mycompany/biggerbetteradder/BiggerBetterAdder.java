/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biggerbetteradder;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class BiggerBetterAdder {
    public static void main(String[] args) {
        int num1;
        int num2;
        int num3;
        
        Scanner scan = new Scanner(System.in);
        
        String numStr1;
        String numStr2;
        String numStr3;
        
        System.out.print("Enter number 1: ");
        numStr1 = scan.nextLine();
        System.out.print("Enter number 2: ");
        numStr2 = scan.nextLine();
        System.out.print("Enter number 3: ");
        numStr3 = scan.nextLine();
        
        num1 = Integer.parseInt(numStr1);
        num2 = Integer.parseInt(numStr2);
        num3 = Integer.parseInt(numStr3);
        scan.close();
        
        int sum = num1 + num2 + num3;
        
        System.out.print("The sum of "+num1+", "+num2+", and "+num3+" is: ");
        System.out.println(sum);
    }
}
