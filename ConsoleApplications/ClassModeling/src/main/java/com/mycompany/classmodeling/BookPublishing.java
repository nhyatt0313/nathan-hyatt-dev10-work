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
public class BookPublishing {
    private String author, title;
    private int quantity;
    private final int ISBN, yearWritten;
    private double price, deadline;

    public BookPublishing(int ISBN, int yearWritten) {
        this.ISBN = ISBN;
        this.yearWritten = yearWritten;
    }
    
    public void changePrice(double newPrice){
        this.price = newPrice;
    }
    public void changeDeadline(double newDeadline){
        this.deadline = newDeadline;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
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

    public double getPrice() {
        return price;
    }

    public double getDeadline() {
        return deadline;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDeadline(double deadline) {
        this.deadline = deadline;
    }
    
    
}
