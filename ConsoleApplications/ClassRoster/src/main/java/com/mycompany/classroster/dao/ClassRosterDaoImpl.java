/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.dao;

import com.mycompany.classroster.dto.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author nhyat
 */
public class ClassRosterDaoImpl implements ClassRosterDao {

    private Map<String, Student> students = new HashMap();

    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException{
        Student newStudent = students.put(studentId, student);
        return newStudent;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        List<Student> studentList = new ArrayList<Student>();
        if (students.isEmpty()) {
            studentList = null;
        } else {
            Set<String> keyset = students.keySet();
            for (String id : keyset) {
                Student currentStudent = students.get(id);
                studentList.add(currentStudent);
            }
        }
        return studentList;

    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException{
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException{
        return students.remove(studentId);
    }

}
