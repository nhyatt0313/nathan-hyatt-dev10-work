/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.summativesums;

/**
 *
 * @author nhyat
 */
public class SummativeSums {
    public static void main(String[] args) {
        int[] exampleArray1 = { 1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int[] exampleArray2 = { 999, -60, -77, 14, 160, 301 };
        int[] exampleArray3 = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99 };
        
        System.out.print("Result for array 1: ");
        System.out.println(addArray(exampleArray1));
        System.out.print("Result for array 2: ");
        System.out.println(addArray(exampleArray2));
        System.out.print("Result for array 3: ");
        System.out.println(addArray(exampleArray3));
    }
    static int addArray(int[] array){
        int sum = 0;
        for (int i = 0; i < array.length; i++){
            sum += array[i];
        }
        return sum;
    }
}
