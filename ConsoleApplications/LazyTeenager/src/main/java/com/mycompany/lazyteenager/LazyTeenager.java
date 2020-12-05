/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lazyteenager;

import java.util.Random;

/**
 *
 * @author nhyat
 */
public class LazyTeenager {

    public static void main(String[] args) {
        Random rand = new Random();
        int chance = 0;
        int iter = 0;
        boolean clean = false;
        while (!clean) {
            System.out.println("Clean your room!");
            iter++;
            int willClean = rand.nextInt(100) + 1;
            chance += 5;
            if (willClean <= chance) {
                System.out.println("___ Okay Fine!");
                clean = true;
            } else if(iter==15){
                System.out.println("FINE!");
            }else {
                System.out.println("___ NO!");
            }
        }
    }
}
