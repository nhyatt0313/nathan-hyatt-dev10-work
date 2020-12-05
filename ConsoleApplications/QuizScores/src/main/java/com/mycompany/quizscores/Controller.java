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
public class Controller {

    View view = new View();
    Service service = new Service();

    void run() {

        view.printWelcome();
        view.printMenu();

        int choice = view.getUserMenuChoice();
        boolean proceed = true;
        while (proceed) {
            switch (choice) {
                case 0:
                    proceed = false;
                    break;
                case 1:
                    service.viewStudentList();
                    choice = view.getUserMenuChoice();
                    break;
                case 2:
                    service.addStudentScore();
                    choice = view.getUserMenuChoice();
                    break;
                case 3:
                    service.addStudent();
                    choice = view.getUserMenuChoice();
                    break;
                case 4:
                    service.removeStudent();
                    choice = view.getUserMenuChoice();
                    break;
                case 5:
                    service.viewStudentScores();
                    choice = view.getUserMenuChoice();
                    break;
                case 6:
                    service.viewStudentAvgScore();
                    choice = view.getUserMenuChoice();
                    break;
                case 7:
                    service.calculateClassAvg();
                    choice = view.getUserMenuChoice();
                    break;
                case 8:
                    service.studentsWithHighestScore();
                    choice = view.getUserMenuChoice();
                    break;
                case 9:
                    service.studentsWithLowestScore();
                    choice = view.getUserMenuChoice();
                    break;
                case 10:
                    view.printMenu();
                    choice = view.getUserMenuChoice();
                    break;
                default:
                    System.out.println("Something went wonky");
                    break;
            }
        }

    }

}

