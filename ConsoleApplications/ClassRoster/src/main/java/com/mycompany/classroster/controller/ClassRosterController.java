/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.controller;

import com.mycompany.classroster.dao.ClassRosterPersistenceException;
import com.mycompany.classroster.dto.Student;
import com.mycompany.classroster.service.ClassRosterDataValidationException;
import com.mycompany.classroster.service.ClassRosterDuplicateIdException;
import com.mycompany.classroster.service.ClassRosterService;
import com.mycompany.classroster.ui.ClassRosterView;
import java.util.List;

/**
 *
 * @author nhyat
 */
public class ClassRosterController {

    ClassRosterView view;
    ClassRosterService service;

    public ClassRosterController(ClassRosterView injectedView, ClassRosterService injectedService) {
        this.view = injectedView;
        this.service = injectedService;
    }

    public void run() {
        boolean terminate = false;
        int menuSelection = 0;

        try {
            while (!terminate) {

                menuSelection = displayMenuAndGetSelection();

                switch (menuSelection) {
                    case 1: // List Student ID's
                        ListStudents();
                        break;
                    case 2: // Create a Student
                        createStudent();
                        break;
                    case 3: // View a Student
                        viewStudent();
                        break;
                    case 4: // Remove a Student
                        removeStudent();
                        break;
                    case 5: // Exit
                        terminate = true;
                        break;
                    default:
                        unknownCommand();
                        break;
                }

            }
            exitMessaage();
        } catch (ClassRosterPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private int displayMenuAndGetSelection() {
        view.displayMenu();
        return view.readInt("Please select a choice: ", 1, 5);
    }

    private void createStudent() throws ClassRosterPersistenceException {

        view.displayCreateStudentBanner();
        Student newStudent = view.getNewStudentInfo();
        try {
            service.addStudent(newStudent.getStudentId(), newStudent);
        } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException ex) {
            
        }
        view.displayCreateSuccessBanner();
    }

    private void ListStudents() throws ClassRosterPersistenceException {
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }

    private void viewStudent() throws ClassRosterPersistenceException {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }

    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        service.removeStudent(studentId);
        view.displayRemoveSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessaage() {
        view.displayExitBanner();
    }
}
