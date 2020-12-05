/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.healthyheart;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class HealthyHeart {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("What is your age? ");
        
        String ageStr = input.nextLine();
        int age = Integer.parseInt(ageStr);
        
        input.close();
        // calculate max heart rate
        int maxHeartRate = 220 - age;
        double targetZoneLow = maxHeartRate*.5;
        double targetZoneHigh = maxHeartRate*.85;
        long targetLow = Math.round(targetZoneLow);
        long targetHigh = Math.round(targetZoneHigh);
        
        System.out.println("Your maximum heart rate is "+maxHeartRate+" bpm");
        System.out.println("Your targer heart rate zone is "+targetLow+" - "+targetHigh+" bpm");     
    }
}
