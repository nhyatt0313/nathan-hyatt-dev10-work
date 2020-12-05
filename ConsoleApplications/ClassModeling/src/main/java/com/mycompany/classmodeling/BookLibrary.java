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
public class BookLibrary {

    private final String author;
    private final String title;
    private final String category;
    private String location;
    private int quantity;
    private final int ISBN, yearWritten;
    private boolean checkedOut;


    public BookLibrary(String author, String title, String category, int ISBN, int yearWritten) {
        this.author = author;
        this.title = title;
        this.category = category;
        this.ISBN = ISBN;
        this.yearWritten = yearWritten;
    }
    
    public void checkInOut(boolean in){
        this.checkedOut = in;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getISBN() {
        return ISBN;
    }

    public int getYearWritten() {
        return yearWritten;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
    
    
    
}
