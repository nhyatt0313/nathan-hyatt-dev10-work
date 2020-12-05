/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.howmanydaysuntilfriday;

import com.mycompany.howmanydaysuntilfriday.view.ViewImpl;
import com.mycompany.howmanydaysuntilfriday.controller.Controller;
import com.mycompany.howmanydaysuntilfriday.service.Service;
import com.mycompany.howmanydaysuntilfriday.service.ServiceImpl;
import com.mycompany.howmanydaysuntilfriday.view.View;
import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        View view = new ViewImpl(sc);
        Service service = new ServiceImpl();
        Controller controller = new Controller(service, view);
        
        controller.run();
    }
}
