/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.yourlifeinmovies;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class YourLifeInMovies {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("What year were you born? ");
        int year = in.nextInt();
        if (year < 2005){
            System.out.println("2005 statement");
        }
        if (year < 1995){
            System.out.println("1995 statement");
        }
        if (year < 1985){
            System.out.println("1985 statement");
        }
        if (year < 1975){
            System.out.println("1975 statement");
        }
        if (year < 1965){
            System.out.println("1965 statement");
        }
        in.close();
    }
}
