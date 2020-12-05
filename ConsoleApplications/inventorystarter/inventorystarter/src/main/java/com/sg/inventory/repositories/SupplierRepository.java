/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.inventory.repositories;

import com.sg.inventory.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nhyat
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    
}
