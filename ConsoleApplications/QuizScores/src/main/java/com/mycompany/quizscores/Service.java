/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quizscores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author nhyat
 */
public class Service {
    private final View view = new View();
    private Map<Integer, Student> students = new HashMap<>();
    private int numIds = 0;

    Set<Integer> keys = students.keySet();
    
    void viewStudentList() {
        view.println("List of students: ");
        // print student names
        
        for (int k : keys){
            Student student = students.get(k);
            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            view.print(k+". "+firstName+" "+lastName);
        }
        view.println("");
    }

    void addStudent() {
        Student stud = new Student();
        stud.setId(numIds);
        numIds++;
        
        stud.setFirstName(view.getInput("\nEnter first name: "));
        stud.setLastName(view.getInput("Enter last name: "));
            
        students.put(stud.getId(), stud);
        view.println(stud.getFirstName()+" "+stud.getLastName()+" was added to the class");
        String prompt = "Would you like to add grades for "+stud.getFirstName()+" "+stud.getLastName()+"? (y/n)";
        if (view.getInput(prompt).equals("y")){
            boolean done = false;
            while(!done){
                addStudentScore(stud);
                if (view.getInput("Add another grade? (y/n)").equals("n")){
                    done = true;
                } else if (view.getInput("Add another grade? (y/n)").equals("y")){
                    
                } else {
                    view.println("Invalid input - try again");
                }
            }
        }
    }

    void addStudentScore() {
        view.print("\nAdd a score: ");
        Integer id = view.getStudentId(students.size());
        Student stud = students.get(id);
        addStudentScore(stud);
    }
    void addStudentScore(Student stud) {
        List<Double> temp = stud.getScores();
        temp.add(view.getScore());
        stud.setScores(temp);
    }

    void removeStudent() {
        if (students.isEmpty()){
            view.println("There are no more students to remove ");
        } else {
            Integer key = view.getStudentId(students.size()-1);
            view.println("\n"+students.get(key).getFirstName()+" "+students.get(key).getLastName()+" was removed from the class ");
            students.remove(key);
            
        }
    }

    void viewStudentScores() {
        
        if (students.isEmpty()){
            view.println("There are no more students to view ");
        } else {
            Integer key = view.getStudentId(students.size()-1);
            view.println(students.get(key).getFirstName()+" "+students.get(key).getLastName()+"'s scores are as follows: ");
            // get student by key
            Student stud = students.get(key);
            for (double i : stud.getScores()){
                view.print(i+", ");
            }
            view.println("");
        }
        
    }

    void viewStudentAvgScore() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void calculateClassAvg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void studentsWithHighestScore() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void studentsWithLowestScore() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
