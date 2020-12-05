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
public class IceCreamFactory {
    private final String flavor;
    private final double milk;
    private final double totalIceCream;
    private final double sugar;
    private final double timeToMake;
    private int numToProduce;

    public IceCreamFactory(String flavor, double milk, double totalIceCream, double sugar, double timeToMake) {
        this.flavor = flavor;
        this.milk = milk;
        this.totalIceCream = totalIceCream;
        this.sugar = sugar;
        this.timeToMake = timeToMake;
    }
    
    public void factoryClose(){
        
    }
    public void increaseProduction(int increase){
        this.numToProduce += increase;
    }

    public int getNumToProduce() {
        return numToProduce;
    }

    public void setNumToProduce(int numToProduce) {
        this.numToProduce = numToProduce;
    }

    public String getFlavor() {
        return flavor;
    }

    public double getMilk() {
        return milk;
    }

    public double getTotalIceCream() {
        return totalIceCream;
    }

    public double getSugar() {
        return sugar;
    }

    public double getTimeToMake() {
        return timeToMake;
    }
    
    
}
