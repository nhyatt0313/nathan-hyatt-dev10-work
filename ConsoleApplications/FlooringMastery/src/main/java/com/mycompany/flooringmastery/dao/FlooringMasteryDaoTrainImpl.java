/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;


import com.mycompany.flooringmastery.exception.PersistenceException;
import java.time.LocalDate;


/**
 *
 * @author nhyat
 */
public class FlooringMasteryDaoTrainImpl extends FlooringMasteryDaoProdImpl{

    @Override
    public void writeFile(LocalDate date) throws PersistenceException {
            throw new PersistenceException("Saving work is disabled in training mode.", new Throwable(""));
    }  
}
