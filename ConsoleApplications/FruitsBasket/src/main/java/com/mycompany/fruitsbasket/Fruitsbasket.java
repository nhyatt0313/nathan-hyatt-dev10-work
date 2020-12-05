/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruitsbasket;

/**
 *
 * @author nhyat
 */
public class Fruitsbasket {

    public static void main(String[] args) {
        String[] fruit = {"Orange", "Apple", "Orange", "Apple", "Orange",
            "Apple", "Orange", "Apple", "Orange", "Orange", "Orange",
            "Apple", "Orange", "Orange", "Apple", "Orange", "Orange",
            "Apple", "Apple", "Orange", "Apple", "Apple", "Orange",
            "Orange", "Apple", "Apple", "Apple", "Apple", "Orange",
            "Orange", "Apple", "Apple", "Orange", "Orange", "Orange",
            "Orange", "Apple", "Apple", "Apple", "Apple", "Orange",
            "Orange", "Apple", "Orange", "Orange", "Apple", "Orange",
            "Orange", "Apple", "Apple", "Orange", "Orange", "Apple",
            "Orange", "Apple", "Orange", "Apple", "Orange", "Apple",
            "Orange", "Orange"};
        int orange = 0;
        int apple = 0;
        for (int i = 0; i < fruit.length; i++){
            if (fruit[i].equals("Orange")){
                orange++;
            }
            if (fruit[i].equals("Apple")){
                apple++;
            }
        }
        System.out.println("Oranges: "+orange);
        System.out.println("Apples: "+apple);
        
        String[] apples = new String[apple];
        String[] oranges = new String[orange];
        
        int orangeCount = 0;
        int appleCount = 0;
        for (int i = 0; i < fruit.length; i++){
            if (fruit[i].equals("Orange")){
                oranges[orangeCount] = fruit[i];
                orangeCount++;
            }
            if (fruit[i].equals("Apple")){
                apples[appleCount] = fruit[i];
                appleCount++;
            }
        }
        System.out.println("Oranges: "+orangeCount);
        System.out.println("Apples: "+appleCount);

    }

}
