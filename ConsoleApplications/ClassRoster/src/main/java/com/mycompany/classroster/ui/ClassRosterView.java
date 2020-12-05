/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.ui;

import com.mycompany.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author nhyat
 */
public class ClassRosterView {

    private final UserIO io;

    public ClassRosterView(UserIO injectedIo) {
        this.io = injectedIo;
    }

    public void print(String message) {
        io.print(message);
    }

    public void println(String message) {
        print(message + "\n");
    }

    public void displayMenu() {
        println("Main Menu: ");
        println("1. List Student ID's");
        println("2. Create New Student");
        println("3. View a Student");
        println("4. Remove a Student");
        println("5. Exit");
    }

    public int readInt(String prompt, int min, int max) {
        return io.readInt(prompt, min, max);

    }

    public Student getNewStudentInfo() {
        String studentId = io.readString("Please enter Student ID: ");
        String firstName = io.readString("Please enter First Name: ");
        String lastName = io.readString("Please enter Last Name: ");
        String cohort = io.readString("Please enter Cohort: ");

        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);

        return currentStudent;

    }

    public void displayCreateStudentBanner() {
        println("=== Create Student ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("Student successfully created.  Please hit enter to continue");
    }

    public void displayStudent(Student student) {
        println("Student Information: ");
        println("Cohort: " + student.getCohort());
        println("Name: " + student.getFirstName() + " " + student.getLastName());
        println("ID: " + student.getStudentId());
    }

    public void displayStudentList(List<Student> studentList) {
        for (Student currentStudent : studentList) {
            println(currentStudent.getStudentId() + " : "
                    + currentStudent.getFirstName() + " : "
                    + currentStudent.getLastName());
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        println("=== Display All Students ===");
    }

    public void displayDisplayStudentBanner() {
        println("=== Display Student ===");
    }

    public String getStudentIdChoice() {
        return io.readString("Please enter the student id: ");
    }

    public void displayRemoveStudentBanner() {
        println("=== Remove Student ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Student succesfully removed. Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        println("Unknown Command!!!");
    }

    public void displayErrorMessage(String message) {
        println("=== ERROR ===");
        println(message);
    }

}
