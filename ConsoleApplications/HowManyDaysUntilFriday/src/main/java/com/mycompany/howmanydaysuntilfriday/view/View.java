/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.howmanydaysuntilfriday.view;

import com.mycompany.howmanydaysuntilfriday.service.Day;

/**
 *
 * @author nhyat
 */
public interface View {
    
    public Day getInput();
    
    public void displayResult(int days);
    
    
}
