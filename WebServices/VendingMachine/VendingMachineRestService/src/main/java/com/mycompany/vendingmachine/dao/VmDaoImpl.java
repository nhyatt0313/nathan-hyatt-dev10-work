///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.vendingmachine.dao;
//
//import com.mycompany.vendingmachine.Id;
//import com.mycompany.vendingmachine.dto.Snack;
//import com.mycompany.vendingmachine.exception.VmInvalidEntryException;
//import com.mycompany.vendingmachine.exception.VmPersistenceException;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import org.springframework.stereotype.Repository;
//
///**
// *
// * @author nhyat
// */
//public class VmDaoImpl implements VmDao {
//
//    private final String ROSTER_FILE = "inventory.txt";
//    private final String DELIMETER = "::";
//    List<Snack> snackList = new ArrayList<>();
//
//    @Override
//    public void buySnack(Id id) throws VmInvalidEntryException, VmPersistenceException {
//
//        readInventory();
//        Snack snack;
//        snack = getSnackById(id);
//        snackList.remove(snack);
//        int num = snack.getNumInStock();
//        snack.setNumInStock(num - 1);
//        snackList.add(snack);
//        writeInventory();
//
//    }
//
//    @Override
//    public List<Snack> getAllSnacks() throws VmPersistenceException {
//
//        readInventory();
//
//        return snackList;
//    }
//
//    @Override
//    public Snack getSnackById(Id id) throws VmInvalidEntryException, VmPersistenceException {
//
//        readInventory();
//
//        for (Snack snack : snackList) {
//            if (snack.getId() == id) {
//                return snack;
//            }
//        }
//        throw new VmInvalidEntryException("Invalid entry");
//    }
//
//    private void writeInventory() {
//        // write to file
//        PrintWriter pw;
//        try {
//            pw = new PrintWriter(new FileWriter(ROSTER_FILE, false));
//            snackList.stream().forEachOrdered((snack) -> {
//                pw.print(snack.getId() + DELIMETER);
//                pw.print(snack.getType() + DELIMETER);
//                pw.print(snack.getPrice() + DELIMETER);
//                pw.print(snack.getNumInStock() + "\n");
//            });
//            pw.flush();
//            pw.close();
//        } catch (IOException ex) {
//
//        }
//
//    }
//
//    private void readInventory() throws VmPersistenceException {
//        // read from file and put contents into a list
//        Scanner sc;
//        String currentLine;
//        Snack currentSnack;
//
//        // empty the list first
//        snackList.removeAll(snackList);
//        // then add all items from the file
//        try {
//            sc = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
//            while (sc.hasNextLine()) {
//
//                currentLine = sc.nextLine();
//                String[] temp = currentLine.split(DELIMETER);
//
//                currentSnack = new Snack();
//                currentSnack.setId(temp[0]);
//                currentSnack.setType(temp[1]);
//                BigDecimal price = new BigDecimal(temp[2]);
//                currentSnack.setPrice(price);
//                int num = Integer.parseInt(temp[3]);
//                currentSnack.setNumInStock(num);
//
//                snackList.add(currentSnack);
//            }
//            sc.close();
//        } catch (FileNotFoundException | NumberFormatException ex) {
//            throw new VmPersistenceException("File could not be read", ex);
//        }
//    }
//   
//
//}
