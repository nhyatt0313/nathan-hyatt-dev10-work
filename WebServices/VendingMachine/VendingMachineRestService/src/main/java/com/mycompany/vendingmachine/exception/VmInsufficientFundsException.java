/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.exception;

/**
 *
 * @author nhyat
 */
public class VmInsufficientFundsException extends Exception{
    public VmInsufficientFundsException(String message){
        super(message);
    }
}
