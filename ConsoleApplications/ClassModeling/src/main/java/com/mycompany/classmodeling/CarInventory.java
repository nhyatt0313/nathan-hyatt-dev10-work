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
public class CarInventory {
    private final String year;
    private final String model;
    private final String vin;
    private String color;
    private double cost;
    private boolean inStock;
    private String parkLocation;

    public CarInventory(String year, String model, String vin) {
        this.year = year;
        this.model = model;
        this.vin = vin;
    }
    
    public void rePark(String newLocation){
        parkLocation = newLocation;
    }
    public double changeCost(double newCost){
        return newCost;
    }
    public void changeColor(String newColor){
        color = newColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getParkLocation() {
        return parkLocation;
    }

    public void setParkLocation(String parkLocation) {
        this.parkLocation = parkLocation;
    }
    
    
}
