/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classmodeling;

/**
 *
 * @author nhyat
 */
public class IceCreamGrocery {
    private int quantity;
    private String brand;
    private boolean inStock;
    private final String flavor;
    private String shelfLocation;
    private double shelfLife;
    private double price;

    public IceCreamGrocery(int quantity, String flavor) {
        this.quantity = quantity;
        this.flavor = flavor;
    }
    
    public void expire(int numToExpire){
        this.quantity -= numToExpire;
    }
    
    public void priceChange(double newPrice){
        this.price = newPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    public void setShelfLocation(String shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

    public double getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(double shelfLife) {
        this.shelfLife = shelfLife;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
