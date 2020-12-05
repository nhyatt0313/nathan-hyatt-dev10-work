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
public class VmInvalidEntryException extends Exception{
    public VmInvalidEntryException(String message){
        super(message);
    }
}
