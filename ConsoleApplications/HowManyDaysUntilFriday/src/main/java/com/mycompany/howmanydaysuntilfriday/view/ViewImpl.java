/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.howmanydaysuntilfriday.view;

import com.mycompany.howmanydaysuntilfriday.service.Day;
import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class ViewImpl implements View {

    Scanner sc;
    public ViewImpl(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public Day getInput() {
        System.out.println(" Please enter the Day of the week: ");
        String day = sc.nextLine().toUpperCase();
        Day enumDay = Day.valueOf(day);
        return enumDay;
    }

    @Override
    public void displayResult(int days) {
        //String stringDay = day.toString().toLowerCase();
        System.out.println(days+" DAYS UNTIL FRIDAY!!!");
    }
    
}
