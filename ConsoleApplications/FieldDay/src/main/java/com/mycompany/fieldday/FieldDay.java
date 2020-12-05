/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fieldday;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class FieldDay {
    public static void main(String[] args) {
//    If a person's name falls before Baggins then they are on the team "Red Dragons"
//    If it falls after Baggins, but before Dresden, they are on the team "Dark Wizards"
//    If it falls after Dresden, but before Howl, they are on the team "Moving Castles"
//    If it falls after Howl, but before Potter, they are on the team "Golden Snitches"
//    If it falls after Potter, but before Vimes, they are on the team "Night Guards"
//    If it falls after Vimes, they are on the team “Black Holes”
        System.out.print("Enter name: ");
        Scanner nameInput = new Scanner(System.in);
        String name = nameInput.nextLine();
        nameInput.close();
        
        String team;
        if (name.compareTo("Baggins") <= 0){
            team = "Red Dragons";
            System.out.println("NAME: "+name);
            System.out.println("Comes before Baggins");
        }
        else if (name.compareTo("Dresden") <= 0){
            team = "Dark Wizards";
            System.out.println("NAME: "+name);
            System.out.println("Comes after Baggins");
            System.out.println("Comes before Dresden");
        }
        else if (name.compareTo("Howl") <= 0){
            team = "Moving Castles";
            System.out.println("NAME: "+name);
            System.out.println("Comes after Dresden");
            System.out.println("Comes before Howl");
        }
        else if (name.compareTo("Potter") <= 0){
            team = "Golden Snitches";
            System.out.println("NAME: "+name);
            System.out.println("Comes after Howl");
            System.out.println("Comes before Potter");
        }
        else if (name.compareTo("Vimes") <= 0){
            team = "Night Guards";
            System.out.println("NAME: "+name);
            System.out.println("Comes after Potter");
            System.out.println("Comes before Vimes");
        }
        else {
            team = "Black Holes";
            System.out.println("Comes after Vimes");
        }
        
        System.out.println("TEAM: "+team);
    }
}
