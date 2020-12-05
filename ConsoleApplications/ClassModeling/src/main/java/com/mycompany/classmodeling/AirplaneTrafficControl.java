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
public class AirplaneTrafficControl {
    
    private final String AirplaneID;
    
    private double velocity;
    private double longiturde;
    private double latitude;
    private double altitude;
    
    private String takeOffLocation;
    private String destination;
    
    private double departureTime;
    private double arrivalTime;

    

    // constructor
    public AirplaneTrafficControl(String AirplaneID) {
        this.AirplaneID = AirplaneID;
    }
    
    // oither methods
    public void delayFlight(double delay){
        arrivalTime += delay;
    }
    public void cancelFlight(){
        departureTime = 0;
        arrivalTime = 0;
        takeOffLocation = "FLIGHT CANCELED";  
        
    }

    // getters/setters
    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getLongiturde() {
        return longiturde;
    }

    public void setLongiturde(double longiturde) {
        this.longiturde = longiturde;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public void setElevation(double elevation) {
        this.altitude = elevation;
    }

    public String getTakeOffLocation() {
        return takeOffLocation;
    }

    public void setTakeOffLocation(String takeOffLocation) {
        this.takeOffLocation = takeOffLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(double departureTime) {
        this.departureTime = departureTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
}
