/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.windowcostcalculator;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class WindowCostCalculator {
    public static void main(String[] args) {
        float height;
        float width;
        float area;
        float perimeter;
        float cost;
        
        // take input dimensions of windows as strings
        
        height = readValue("Enter Height: ");
        width = readValue("Enter Width: ");
        
        // calculate area
        area = width*height;
        // calculate perimeter
        perimeter = 2*(width + height);
        
        // calculate cost
        cost = 3.5f*area + 2.25f*perimeter;
        
        System.out.println("Height: "+height);
        System.out.println("Width: "+width);
        System.out.println("Area: "+area);
        System.out.println("Perimeter: "+perimeter);
        System.out.println("Cost: "+cost);
    }
    
    public static float readValue(String prompt){
        Scanner scan = new Scanner(System.in);
        
        System.out.println(prompt);
        String input = scan.nextLine();
        
        float inputFloat = Float.parseFloat(input);
        return inputFloat;
    }
}
