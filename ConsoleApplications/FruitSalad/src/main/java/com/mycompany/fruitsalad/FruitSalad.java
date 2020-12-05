/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruitsalad;

/**
 *
 * @author nhyat
 */
public class FruitSalad {

    public static void main(String[] args) {
        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry",
            "Beefsteak Tomato", "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",
            "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple",
            "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};

        String[] fruitSalad = new String[12];
        // Rules :
        // as many berries
        // no more than 3 kinds of apple
        // no mopre than 2 kinds of orange
        // no tomatoes
        // no more than 12 kinds
        int saladIndex = 0;

        int berryKinds = 0;
        int appleKinds = 0;
        int orangeKinds = 0;

        for (int i = 0; i < fruit.length; i++) {
            if (saladIndex < 12) {
                if (fruit[i].contains("berries")) {
                    fruitSalad[saladIndex] = fruit[i];
                    saladIndex++;
                    berryKinds++;

                } else if (fruit[i].contains("Apple") && appleKinds < 3) {
                    fruitSalad[saladIndex] = fruit[i];
                    saladIndex++;
                    appleKinds++;
                } else if (fruit[i].contains("Orange") && orangeKinds < 2) {
                    fruitSalad[saladIndex] = fruit[i];
                    saladIndex++;
                    orangeKinds++;
                } else if (fruit[i].contains("Tomato")) {
                } else if (fruit[i].contains("Apple")) {
                } else if (fruit[i].contains("Orange")) {
                } else {
                    fruitSalad[saladIndex] = fruit[i];
                    saladIndex++;
                }
            }
        }

        System.out.print(
                "FRUIT SALAD: ");
        for (int j = 0;
                j < fruitSalad.length;
                j++) {
            System.out.print(fruitSalad[j] + ", ");
        }
    }
}
