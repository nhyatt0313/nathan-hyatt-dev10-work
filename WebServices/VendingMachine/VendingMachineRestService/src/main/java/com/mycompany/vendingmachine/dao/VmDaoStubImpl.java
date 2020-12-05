///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.vendingmachine.dao;
//
//import com.mycompany.vendingmachine.Id;
//import com.mycompany.vendingmachine.dto.Snack;
//import com.mycompany.vendingmachine.exception.VmInsufficientFundsException;
//import com.mycompany.vendingmachine.exception.VmInvalidEntryException;
//import com.mycompany.vendingmachine.exception.VmPersistenceException;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.stereotype.Repository;
//
///**
// *
// * @author nhyat
// */
//public class VmDaoStubImpl implements VmDao {
//
//    Snack onlySnack;
//    List<Snack> snackList;
//    public VmDaoStubImpl() {
//        onlySnack = new Snack();
//        onlySnack.setId("TEST");
//        onlySnack.setType("snack");
//        onlySnack.setPrice(BigDecimal.ONE);
//        onlySnack.setNumInStock(1);
//        
//        snackList = new ArrayList<>();
//        snackList.add(onlySnack);
//    }
//
//    @Override
//    public void buySnack(Id id) throws VmInvalidEntryException, VmPersistenceException, VmInsufficientFundsException{
//        int numInStock = onlySnack.getNumInStock();
//        
//        if (onlySnack.getId() == id){
//            onlySnack.setNumInStock(--numInStock);
//        } else {
//            // do nothing
//        }  
//    }
//
//    @Override
//    public List<Snack> getAllSnacks() {
//        return snackList;
//    }
//
//    @Override
//    public Snack getSnackById(Id id) {
//        if (onlySnack.getId() == id){
//            return onlySnack;
//        } else {
//            return null;
//        }
//        
//    }
//
//}
