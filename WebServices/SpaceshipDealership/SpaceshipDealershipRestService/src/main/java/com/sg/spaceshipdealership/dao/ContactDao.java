/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import java.util.List;

import com.sg.spaceshipdealership.dto.Contact;

/**
 *
 * @author nhyat
 */
public interface ContactDao {

    Contact read(int id);

    List<Contact> readAll();

    Contact create(Contact contact);

    void update(Contact contact);

    void delete(int id);
}
