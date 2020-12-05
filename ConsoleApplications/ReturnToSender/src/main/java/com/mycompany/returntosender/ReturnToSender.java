/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.returntosender;

/**
 *
 * @author nhyat
 */
public class ReturnToSender {
    public static void main(String[] args) {

        String aMystery = Character.toString(mystery());
        String totallyUnexpected = unexpected();
        String aSurprise = Double.toString(surprise());
        String itsClassified = Boolean.toString(classified());
        String aSecret = Integer.toString(secret());

        System.out.println("The methods have returned! Their results...\n");
        System.out.println("Mysterious: " + aMystery);
        System.out.println("    Secret: " + aSecret);
        System.out.println("Surprising: " + aSurprise);
        System.out.println("Classified: " + itsClassified);
        System.out.println("Unexpected: " + totallyUnexpected);

    }

    public static int secret(){
        return 42;
    }

    public static double surprise(){
        return 3.14;
    }

    public static char mystery(){
        return 'X';
    }

    public static boolean classified(){
        return true;
    }

    public static String unexpected(){
        return "Spanish Inquisition";
    }
}
