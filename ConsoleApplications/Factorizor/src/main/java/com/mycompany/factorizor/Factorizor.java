/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.factorizor;

import java.util.Scanner;


/**
 *
 * @author nhyat
 */
public class Factorizor {

    public void factorize(){
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        
        int sum = 0;
        int factorCount = 0;
        
        for(int i=1; i<num; i++){
            
            if (num%i == 0){
                System.out.println(i);
                sum += i;
                factorCount++;
            }    
        }
        if (factorCount==1){
            System.out.println("Prime");
        }else{
            System.out.println("Not Prime");
        }
        if(sum==num){
            System.out.println("Perfect");
        }else{
            System.out.println("Not Perfect");
        }
    }
}
