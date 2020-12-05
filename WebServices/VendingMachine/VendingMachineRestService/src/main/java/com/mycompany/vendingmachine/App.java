/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author nhyat
 */
//@SpringBootApplication
//public class BootAnnotatedApp implements CommandLineRunner {
//
//    @Autowired
//    private VmController controller;
//    
//    public static void main(String args[]) {
//        SpringApplication.run(BootAnnotatedApp.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        controller.run();
//    }
//
//}
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}