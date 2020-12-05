/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author nhyat
 */
public interface UserIO {

    void print(String message);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);
    
    BigDecimal readBigDecimal(String prompt);
    
    BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max);
    
    LocalDate readLocalDate(String prompt, String pattern);
    
    LocalDate readLocalDate(String prompt, String pattern, LocalDate min, LocalDate max);

    public void println(String message);

    public String readStateString(String prompt);

    public String readProdTypeString(String prompt);
    
}
