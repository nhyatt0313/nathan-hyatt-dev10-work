/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author nhyat
 */
public class FlooringMasteryAuditDao {
    
    public void writeAuditEntry(String auditEntry) throws IOException{
        PrintWriter pw;
            pw = new PrintWriter(new FileWriter("audit.txt", true));
            pw.print(LocalDateTime.now() + " | "+auditEntry);
            pw.flush();
            pw.close();
    }
}
