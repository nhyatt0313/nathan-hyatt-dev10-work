/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.howmanydaysuntilfriday.controller;

import com.mycompany.howmanydaysuntilfriday.service.Day;
import com.mycompany.howmanydaysuntilfriday.service.Service;
import com.mycompany.howmanydaysuntilfriday.view.View;

/**
 *
 * @author nhyat
 */
public class Controller {
    
    Service service;
    View view;

    public Controller(Service service, View view) {
        this.service = service;
        this.view = view;
    }
    
    public void run(){
        Day input = view.getInput();
        int days = service.calculateDaysUntilFriday(input);
        view.displayResult(days);
    }
}
