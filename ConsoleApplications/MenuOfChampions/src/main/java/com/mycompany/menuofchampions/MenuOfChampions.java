/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.menuofchampions;

/**
 *
 * @author nhyat
 */
public class MenuOfChampions {
    public static void main(String[] args) {
        String item1 = "Taco";
        String item2 = "Burrito";
        String item3 = "Potato";
        
        double price1 = 1000;
        double price2 = 176.45;
        double price3 = -12.50;
        
        System.out.println("Welcome to Breakfast of Champions!");
        System.out.println("Todays Menu is...");
        
        System.out.println(item1+" ----- $"+price1);
        System.out.println(item2+" ----- $"+price2);
        System.out.println(item3+" ----- $"+price3);
    }
}
