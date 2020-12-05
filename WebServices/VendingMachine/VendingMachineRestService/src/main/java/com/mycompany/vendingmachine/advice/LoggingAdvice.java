/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.advice;

import com.mycompany.vendingmachine.dao.VmAuditDao;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author nhyat
 */
public class LoggingAdvice {
    VmAuditDao auditDao;

    public LoggingAdvice(VmAuditDao auditDao) {
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp){
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + " | ";
        for (Object currentArg : args){
            auditEntry += currentArg+" | ";
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (Exception e){
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
}
