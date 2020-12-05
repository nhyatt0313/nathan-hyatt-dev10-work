/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplesort;

/**
 *
 * @author nhyat
 */
public class SimpleSort {

    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 55, 67, 88, 99};
        int[] secondHalf = {1, 4, 8, 11, 15, 18, 21, 44, 54, 79, 89, 100};

        int[] wholeNumbers = new int[24];

        int iter = 0;
        for (int i = 0; i < firstHalf.length; i++) {
            int low = 10000;
            int secondLow = low;
            if (firstHalf[i] < low) {
                low = firstHalf[i];
                secondLow = secondHalf[i];
            }
            if (secondHalf[i] < low) {
                low = secondHalf[i];
                secondLow = firstHalf[i];
            }
            wholeNumbers[iter] = low;
            iter++;
            wholeNumbers[iter] = secondLow;
            iter++;

        }
        for (int j = 0; j < wholeNumbers.length; j++) {
            System.out.print(wholeNumbers[j] + " ");
        }
    }
}
