/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.commentingcode;

/**
 *
 * @author nhyat
 */
public class CommentingCode {
    public static void main(String[] args) {
        // comments are written to explain code in an easily
        // understandable way
        // basically for single lines
        // anything after // is considered a comment
        System.out.println("Normal code is compiled and runs ...");
        System.out.println("Comment however ..."); // do not execute!
        
        // comments can be their own line
        System.out.println("..."); // or they can share like this
        
        // However if you put the // BEFORE a line of code
        // System.out.println("Then is is considered a comment");
        // System.out.println("and won't execute!");
        /*
        
        This is a multi-linesd comment!
        Named because, well, it spans SO many lines!
        
        */
        
    }
}
