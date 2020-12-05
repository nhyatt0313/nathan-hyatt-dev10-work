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
public class House3D {
    private final double squareFootage;
    private final int numBedrooms;
    private final int numBathrooms;
    
    private String[] features;
    private String houseStyle;

    public House3D(double squareFootage, int numBedrooms, int numBathrooms) {
        this.squareFootage = squareFootage;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
    }
    
    public void renovate(String[] newFeatures){
        features = newFeatures;
    }

    public void setFeatures(String[] features) {
        this.features = features;
    }

    public void setHouseStyle(String houseStyle) {
        this.houseStyle = houseStyle;
    }

    public double getSquareFootage() {
        return squareFootage;
    }

    public int getNumBedrooms() {
        return numBedrooms;
    }

    public int getNumBathrooms() {
        return numBathrooms;
    }

    public String[] getFeatures() {
        return features;
    }

    public String getHouseStyle() {
        return houseStyle;
    }
    
    
    
    
    
}
