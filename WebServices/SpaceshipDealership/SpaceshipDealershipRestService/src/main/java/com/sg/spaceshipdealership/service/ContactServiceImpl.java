/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import com.sg.spaceshipdealership.dao.ContactDao;
import com.sg.spaceshipdealership.dto.Contact;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactDao contactDao;

    @Override
    public Contact read(int id) throws DoesNotExistException {
        Contact contact = contactDao.read(id);
        if (contact == null) {
            throw new DoesNotExistException("Contact "+id+" Not Found");
        }
        return contact;
    }

    @Override
    public List<Contact> readAll() {
        return contactDao.readAll();
    }

    @Override
    public Contact create(Contact contact) throws InvalidEntryException {
        validateContact(contact);
        return contactDao.create(contact);
    }

    @Override
    public void update(Contact contact) throws InvalidEntryException, DoesNotExistException {
        read(contact.getId());
        validateContact(contact);
        contactDao.update(contact);
    }

    @Override
    public void delete(int id) throws DoesNotExistException {
        Contact contact = contactDao.read(id);
        if (contact == null) {
            throw new DoesNotExistException("Contact "+id+" Not Found");
        }
        contactDao.delete(id);
    }

    private void validateContact(Contact contact) throws InvalidEntryException {
        List<String> errors = new ArrayList<>();

        // check properties
        if (contact.getName() == null
                || contact.getName().trim().isEmpty()){
            errors.add("Invalid Name");
        }
        if (contact.getMessage() == null
                || contact.getMessage().trim().isEmpty()) {
            errors.add("Invalid Message");
        }
        if (contact.getPhone() == null
                || contact.getPhone().trim().isEmpty()) {
            errors.add("Invalid Phone");
        }
        if (contact.getEmail() == null
                || contact.getEmail().trim().isEmpty()) {
            errors.add("Invalid Email");
        }

        // throw the errors if there are any
        String allErrors = "";
        int numErrors = errors.size();
        int iter = 0;
        if (numErrors != 0) {
            for (String s : errors) {
                iter++;
                if (iter < numErrors) {
                    allErrors += (s + ", ");
                } else {
                    allErrors += s;
                }
            }
            // could possibly pass the list to exception class instead of the formatted string
            throw new InvalidEntryException(allErrors);
        }
    }
}
