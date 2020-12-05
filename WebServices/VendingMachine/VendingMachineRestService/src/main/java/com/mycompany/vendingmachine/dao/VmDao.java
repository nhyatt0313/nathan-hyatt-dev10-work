/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.Id;
import com.mycompany.vendingmachine.dto.Snack;
import com.mycompany.vendingmachine.exception.VmPersistenceException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author nhyat
 */
public interface VmDao {
    
    public void buySnack(Snack snack) throws SQLException;
    
    public List<Snack> getAllSnacks();
    
    public Snack getSnackById(Id id) throws VmPersistenceException;
    
}
