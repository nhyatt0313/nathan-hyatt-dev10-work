/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.forbyfor;

/**
 *
 * @author nhyat
 */
public class ForByFor {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.print("|");

            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (i == 1 && j == 1) {
                        System.out.print("#");
                    } else if (i == 1) {
                        System.out.print("@");
                    } else if (j == 1) {
                        System.out.print("$");
                    } else {
                        System.out.print("*");
                    }
                }
                System.out.print("|");
            }
            System.out.println("");
        }
    }
}
