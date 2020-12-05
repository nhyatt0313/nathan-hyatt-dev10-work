/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.howmanydaysuntilfriday.service;

/**
 *
 * @author nhyat
 */
public class ServiceImpl implements Service{

    @Override
    public int calculateDaysUntilFriday(Day day) {
        switch (day){
            case MONDAY:
                return 4;
            case TUESDAY:
                return 3;
            case WEDNESDAY:
                return 2;
            case THURSDAY:
                return 1;
            case FRIDAY:
                return 0;
            case SATURDAY:
                return 6;
            case SUNDAY:
                return 5;
            default:
                return 1000;
        }
    }
    
}
