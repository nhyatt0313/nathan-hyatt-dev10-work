/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

/**
 *
 * @author nhyat
 */
public class DoesNotExistException extends Exception {

    public DoesNotExistException(String message) {
        super(message);
    }
    
}
