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
public class HouseGPS {
    private final double longitude;
    private final double latitude;
    private final String address;
    private final String city;
    private final String state;
    private final String zip;

    public HouseGPS(double longitude, double latitude, String address, String city, String state, String zip) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    
    public String[] getDirections(String location){
        String[] directions = new String[100];
        // calculate diractions
        return directions;
    }
    public String[] alternativeRoute(String[] directions, String[] modifiers){
        // modify directions based on modifiers
        return directions;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }
    
    
}
