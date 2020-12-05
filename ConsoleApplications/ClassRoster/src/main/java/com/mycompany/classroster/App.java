/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster;

import com.mycompany.classroster.controller.ClassRosterController;
import com.mycompany.classroster.dao.ClassRosterPersistenceException;
import com.mycompany.classroster.service.ClassRosterDataValidationException;
import com.mycompany.classroster.service.ClassRosterDuplicateIdException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author nhyat
 */
public class App {
    public static void main(String[] args) throws ClassRosterPersistenceException, ClassRosterDuplicateIdException, ClassRosterDataValidationException {
        
//        UserIO io = new UserIOImpl();
//        ClassRosterView view = new ClassRosterView(io);
//        ClassRosterDao dao = new ClassRosterDaoFileImpl();
//        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoImpl();
//        ClassRosterService service = new ClassRosterServiceImpl(dao, auditDao);
//        ClassRosterController controller = new ClassRosterController(view, service);       
//        controller.run();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller = ctx.getBean("controller", ClassRosterController.class);
        controller.run();
    }
}
