/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery;

import com.mycompany.flooringmastery.controller.FlooringMasteryController;
import com.mycompany.flooringmastery.ui.FlooringMasteryView;
import com.mycompany.flooringmastery.ui.FlooringMasteryViewImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 * @author nhyat
 */
public class App {
    public static void main(String[] args) {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        FlooringMasteryView view = ctx.getBean("view", FlooringMasteryViewImpl.class);
        int choice = view.getDao();
        FlooringMasteryController controller;
        switch (choice){
            case 1:
                controller = ctx.getBean("prodController", FlooringMasteryController.class);
                controller.run();
                break;
            case 2:
                controller = ctx.getBean("trainController", FlooringMasteryController.class);
                controller.run();
                break;
            default:
                break;
        }
        
        
        
    }
    
}
