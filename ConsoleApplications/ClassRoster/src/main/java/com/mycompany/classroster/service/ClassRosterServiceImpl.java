/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.service;

import com.mycompany.classroster.dao.ClassRosterAuditDao;
import com.mycompany.classroster.dao.ClassRosterDao;
import com.mycompany.classroster.dao.ClassRosterPersistenceException;
import com.mycompany.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author nhyat
 */
public class ClassRosterServiceImpl implements ClassRosterService {

    private ClassRosterAuditDao auditDao;

    ClassRosterDao dao;

    public ClassRosterServiceImpl(ClassRosterDao injectedDao, ClassRosterAuditDao injectedAuditDao) {
        this.dao = injectedDao;
        this.auditDao = injectedAuditDao;
    }

    @Override
    public Student addStudent(String studentId, Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException {

        if (dao.getStudent(student.getStudentId()) != null) {
            throw new ClassRosterDuplicateIdException("Error: Could not create student. Student id " + student.getStudentId() + " already exists.");
        }
        validateStudentData(student);
        //auditDao.writeAuditEntry("Student " + student.getStudentId() + " created.");
        return dao.addStudent(studentId, student);
    }

    @Override
    public List<Student> getAllStudents() throws
            ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws
            ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws
            ClassRosterPersistenceException {
        Student removedStudent = dao.removeStudent(studentId);
        if (removedStudent != null) {
           // auditDao.writeAuditEntry("Student " + removedStudent.getStudentId() + " removed.");
        }
        return removedStudent;
    }

    private void validateStudentData(Student student) throws
            ClassRosterDataValidationException {

        if (student.getFirstName() == null
                || student.getFirstName().trim().length() == 0
                || student.getLastName() == null
                || student.getLastName().trim().length() == 0
                || student.getCohort() == null
                || student.getCohort().trim().length() == 0) {

            throw new ClassRosterDataValidationException("Error: All fields [First Name, Last Name, Cohort] are required.");

        }
    }
}
