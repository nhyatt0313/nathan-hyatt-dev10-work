/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecount;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class TheCount {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("<<< COUNTING >>>");
        System.out.print("   Start at: ");
        int start = s.nextInt();
        System.out.print("    Stop at: ");
        int stop = s.nextInt();
        System.out.print("   Count by: ");
        int count = s.nextInt();
        
        
        int iter=0;
        for (int i = start; i<=stop; i+=count){
            System.out.print(i+" ");
            iter++;
            if (iter == 3){
                System.out.println(" - Ah ah ah!");
                iter = 0;
            }
        }
    }
}
