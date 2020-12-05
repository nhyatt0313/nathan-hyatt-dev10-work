/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.minimadlibs;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class MiniMadLibs {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("noun: ");
        String one = in.nextLine();
        
        System.out.println("adj: ");
        String two = in.nextLine();
        
        System.out.println("noun: ");
        String three = in.nextLine();
        
        System.out.println("number: ");
        String numStr = in.nextLine();
        double four = Double.parseDouble(numStr);
        
        System.out.println("adj: ");
        String five = in.nextLine();
        
        System.out.println("noun: ");
        String six = in.nextLine();
        
        System.out.println("noun: ");
        String seven = in.nextLine();
        
        System.out.println("noun: ");
        String eight = in.nextLine();
        
        System.out.println("verb (present-tense): ");
        String nine = in.nextLine();
        
        System.out.println("same verb (past-tense): ");
        String ten = in.nextLine();
        
        // print out the mad lib
        System.out.println(one+": the "+two+" frontier. These are the voyages of the starship "+three+".");
        System.out.println("Its "+four+" year mission: to explore strange "+five+" "+six+", to seek out ");
        System.out.println(five+" "+seven+" and "+five+" "+eight+", to bodly "+nine+" where no one has "+ten+" before.");
    }
}
