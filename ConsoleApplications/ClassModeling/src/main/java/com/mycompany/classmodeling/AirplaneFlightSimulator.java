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
public class AirplaneFlightSimulator {
    
    private final String playerID;
    
    private double velocity;
    private double longitude;
    private double latitude;
    private double altitude;

    public AirplaneFlightSimulator(String playerID) {
        this.playerID = playerID;
    }
    
    public void accelerate(double increase){
        velocity += increase;
        
    }
    
    public void changeAltitude(double change){
        altitude += change;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    
    
    
}
