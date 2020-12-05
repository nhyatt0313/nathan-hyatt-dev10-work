/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spacerustlers;

/**
 *
 * @author nhyat
 */
public class SpaceRustlers {
    public static void main(String[] args) {
        int spaceships = 10;
        int aliens = 25;
        int cows = 100;
        
        
        if(aliens>spaceships){ // prints the statement if there are more aliens then spaceships
            System.out.println("Vroom vroom lets get going!");
        }
        else {  // prints the foollowing statement if there are not more aliens than spaceships 
                // if this statement is reached the 'vroom vroom' statement will not be printed
            System.out.println("There arent enough green guys to drive there ships!");
        }
        
        if (cows == spaceships){    // prints the following statement if the number of cows and spaceships is equal
                                    // none of the following two staements will print if cows equal spaceships
            System.out.println("WOW! way to plan ahead, just enough room for all these hamburgers.");
        }
        else if (cows > spaceships){ // if the number of cows is greater than the spaceships then only this statement will print
            System.out.println("dang! I dont know how were gonna fit all theses cows!");
        }
        else{   // if the number of cows is not equal to the number of spaceships and the number of cows is not greater than the number of spaceships
                // this statement will be reaches and only the following line will be printed
            System.out.println("too many ships! not enough cows.");
        }
        
        if (aliens > cows){
            System.out.println("Hurrah, weve got the grub");
        }
        else{
            System.out.println("Uh oh, looks like the herd got restless. guess were hamburger now.");
        }
    }
}
