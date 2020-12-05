/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bewarethekraken;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class BewareTheKraken {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Lets dive!");
        int depth = 0;
        
        while(depth <36200){
            System.out.println("we have dived "+depth+" feet.");
            System.out.print("Do you want to stop? (y/n)");
            String stop = scan.nextLine();
            if (stop.equals("y")){
                break;
            }
            if(depth >= 20000){
                System.out.println("Uh... i think i see a Kraken");
                break;
            }
            depth += 1000;
        }
        System.out.println("We swam "+depth+" feet down.");
    }
}
