/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.ProductType;
import com.mycompany.flooringmastery.TaxState;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import com.mycompany.flooringmastery.exception.PersistenceException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author nhyat
 */
public class FlooringMasteryDaoProdImpl implements FlooringMasteryDao {

    private final String DELIM = "::";
    private int maxVal;
    
    Map< LocalDate, ArrayList<Order>> orderMap = new HashMap<>();
    ArrayList<Product> productList = new ArrayList<>();
    ArrayList<Tax> taxList = new ArrayList<>();

    @Override
    public Order getOrder(LocalDate date, int id) {

        // get the order with the given id, if it doesnt exist return null
        Order order = null;
        // get the orders on a given date
        ArrayList<Order> ordersOnDate = getOrdersByDate(date);
        for (Order o : ordersOnDate) {
            if (o.getOrderNumber() == id) {
                order = o;
            }
        }
        return order;
    }

    @Override
    public ArrayList<Order> getOrdersByDate(LocalDate date) {
        ArrayList<Order> orders = orderMap.get(date); // already returns null if there is no such key in the map
        if (orders == null) {
            return new ArrayList<>();
        }
        return orders;
    }

    @Override
    public Order addOrder(LocalDate date, Order order) {

        ArrayList<Order> orderList = getOrdersByDate(date); // throws exception
        orderList.add(order);
        orderMap.put(date, orderList);
        return order;

    }

    @Override
    public Order removeOrder(LocalDate date, Order order) {

        ArrayList<Order> orderList = getOrdersByDate(date); // throws exception
        getOrder(date, order.getOrderNumber()); // throws exception
        orderList.remove(order);
        orderMap.put(date, orderList); // replaces the list with the updated list
        return order;
    }

    @Override
    public ArrayList<Product> getProductsFromFile() throws PersistenceException {
        String fileName = "Products.txt";
        productList.clear();
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (sc.hasNextLine()) {
                String[] currentLine = sc.nextLine().split(DELIM);

                Product currentProd = new Product();
                currentProd.setProdType(ProductType.valueOf(currentLine[0].toUpperCase()));
                currentProd.setAreaCostSqft(new BigDecimal(currentLine[1]));
                currentProd.setLaborCostSqft(new BigDecimal(currentLine[2]));

                productList.add(currentProd);
            }
        } catch (FileNotFoundException ex) {
            throw new PersistenceException("Could not add the order.",
                    new Throwable("Could not find order in file " + fileName));
        }
        return productList;
    }

    @Override
    public ArrayList<Tax> getTaxesFromFile() throws PersistenceException {
        String fileName = "Taxes.txt";
        taxList.clear();
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (sc.hasNextLine()) {
                String[] currentLine = sc.nextLine().split(DELIM);

                Tax currentTax = new Tax();
                currentTax.setState(TaxState.valueOf(currentLine[0].toUpperCase()));
                currentTax.setTaxRate(new BigDecimal(currentLine[1]));

                taxList.add(currentTax);

            }
        } catch (FileNotFoundException ex) {

        }
        return taxList;

    }

    @Override
    public void writeFile(LocalDate date) throws PersistenceException {

        try {
            String fileName = "Orders/Orders_" + date.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt";
            File file = new File(fileName);
            file.createNewFile();

            PrintWriter pw;
            pw = new PrintWriter(new FileWriter(fileName, false));

            List<Order> ordersOnDate = getOrdersByDate(date);
            ordersOnDate.stream().forEachOrdered(o -> pw.print(o.toFileString(DELIM)));

            pw.flush();
            pw.close();
        } catch (IOException e) {
            throw new PersistenceException("Could not write to file", e.getCause());
        }
    }

    @Override
    public void readFile(LocalDate date) throws PersistenceException {
        String fileName = "Orders/Orders_" + date.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt";
        // empty the map
        //orderMap.clear();

        String currentLine;
        Order currentOrder;
        Product currentProduct;
        Tax currentTax;

        ArrayList<Order> orders = new ArrayList<>();

        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (sc.hasNextLine()) {
                currentLine = sc.nextLine();
                String[] currentToken = currentLine.split(DELIM);

                currentProduct = new Product();
                currentTax = new Tax();
                currentOrder = new Order(currentProduct, currentTax);

                if (!currentLine.contains(DELIM)) {
                    orderMap.put(date, orders);
                } else {
                    currentOrder.setOrderNumber(Integer.parseInt(currentToken[0]));
                    currentOrder.setCustName(currentToken[1]);
                    currentTax.setState(TaxState.valueOf(currentToken[2].toUpperCase()));
                    currentTax.setTaxRate(new BigDecimal(currentToken[3]));
                    currentOrder.setTax(currentTax); // may be redundant
                    currentProduct.setProdType(ProductType.valueOf(currentToken[4].toUpperCase()));
                    currentOrder.setArea(new BigDecimal(currentToken[5]));
                    currentProduct.setAreaCostSqft(new BigDecimal(currentToken[6]));
                    currentProduct.setLaborCostSqft(new BigDecimal(currentToken[7]));
                    currentOrder.setProduct(currentProduct); // may be redundant
                    currentOrder.setMaterialCost(new BigDecimal(currentToken[8]));
                    currentOrder.setLaborCost(new BigDecimal(currentToken[9]));
                    currentOrder.setTotalTax(new BigDecimal(currentToken[10]));
                    currentOrder.setTotal(new BigDecimal(currentToken[11]));
                }

                // add to the map
                orders.add(currentOrder);
                orderMap.put(date, orders);
            }
        } catch (FileNotFoundException e) {
            throw new PersistenceException("File was not found", e.getCause());
        }
    }

    @Override
    public int getMaxVal(LocalDate date) {
        int max = 0;
        ArrayList<Order> orders;
        try {
            orders = orderMap.get(date);

            for (Order o : orders) {
                if (o.getOrderNumber() > max) {
                    max = o.getOrderNumber();
                }
            }
        } catch (NullPointerException e) {
            // date did not exist in map
            return max;
        }
        return max;
    }

    @Override
    public void saveToAllFiles() throws PersistenceException {
        Set<LocalDate> keyset = orderMap.keySet();
        for (LocalDate d : keyset) {
            writeFile(d);
        }
    }

    @Override
    public String getDelim() {
        return DELIM;
    }

    @Override
    public Product getProduct(ProductType prodType) throws PersistenceException {
        ArrayList<Product> pList = getProductsFromFile();
        for (Product p : pList) {
            if (p.getProdType() == prodType) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Tax getTax(TaxState state) throws PersistenceException {
        ArrayList<Tax> tList = getTaxesFromFile();
        for (Tax t : tList) {
            if (t.getState() == state) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void readFromAllFiles() throws PersistenceException {
        File Orders = new File("Orders");
        File[] dates = Orders.listFiles();

        for (File file : dates) {

            // trim the filename to get the date string
            String fileName = file.getName();
            String[] firstDelim = fileName.split("_");
            String temp = firstDelim[1];
            String[] secondDelim = temp.split(".txt");
            String dateString = secondDelim[0];
            // parse the date string intp a LocalDate
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMddyyyy"));
            // now we can use the normal readFile method that takes in a date
            readFile(date);
        }
    }

    @Override
    public void setOrdersOnDate(LocalDate date, ArrayList<Order> orders) {
        orderMap.put(date, orders);
    }

}
