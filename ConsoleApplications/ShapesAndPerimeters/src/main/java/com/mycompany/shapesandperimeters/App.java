/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shapesandperimeters;

/**
 *
 * @author nhyat
 */
public class App {
    public static void main(String[] args) {
        Square square = new Square();
        Rectangle rectangle = new Rectangle();
        Triangle triangle = new Triangle();
        Circle circle = new Circle();
        
        double squareArea = square.getArea();
        double rectangeArea = rectangle.getArea();
        double triangleArea = triangle.getArea();
        double circleArea = circle.getArea();
        
        square.setColor("blue");
        rectangle.setColor("red");
        triangle.setColor("green");
        
        System.out.println("square color is "+square.getColor());
        System.out.println("rectangle color is "+rectangle.getColor());
        System.out.println("triangle color is "+triangle.getColor());
        System.out.println("circle color is "+circle.getColor());
        
        System.out.println("square Area is "+squareArea);
        System.out.println("rectangle Area is "+rectangeArea);
        System.out.println("triangle Area is "+triangleArea);
        System.out.println("circle Area is "+circleArea);
        
        System.out.println("non override test: "+rectangle.getPerimeter());
        
    }
}
