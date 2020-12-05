/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quizscores;

/**
 *
 * @author nhyat
 */
public class View {
    final UserIOInterface io = new UserIO();
    
    public void printMenu(){
        println("___________________________________________");
        println("1. View a list of students ");
        println("2. Add a score to a student ");
        println("3. Add a student ");
        println("4. Remove a student ");
        println("5. View a students scores ");
        println("6. View a students average score ");
        println("7. Calculate class average ");
        println("8. Show student(s) with the highest score ");
        println("9. Show student(s) with the lowest score ");
        println("0. quit ");
        println("___________________________________________");  
    }
    public void printWelcome(){
        println("WELCOME TO QUIZ-SCORES: ");
    }
    public int getUserMenuChoice(){
        return io.readInt("Please choose an option (enter 10 display menu): ", 0, 10);
    }

    void print(String string) {
        io.print(string);
    }

    Integer getStudentId(int size) {
        return io.readInt("Enter the students id: ", 0, size);
    }

    String getInput(String prompt) {
        return io.readString(prompt);
    }

    Double getScore() {
        return io.readDouble("Enter score (0 to 100): ", 0, 100);
    }
    
    void println(String message){
        print(message+"\n");
    }
}
