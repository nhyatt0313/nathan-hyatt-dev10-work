/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import com.sg.spaceshipdealership.dao.UserDao;
import com.sg.spaceshipdealership.dto.User;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User create(User user) throws InvalidEntryException {
        validateUser(user);
        return userDao.create(user);
    }

    @Override
    public User read(Integer id) throws DoesNotExistException {
        User user = userDao.read(id);
        if (user == null) {
            throw new DoesNotExistException("User " + id + " Not Found");
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        return userDao.readAll();
    }

    @Override
    public void update(User user) throws InvalidEntryException, DoesNotExistException {
        read(user.getId());
        validateUser(user);
        userDao.update(user);
    }

    @Override
    public void delete(Integer id) throws DoesNotExistException {
        User user = userDao.read(id);
        if (user == null) {
            throw new DoesNotExistException("User " + id + " Not Found");
        }
        userDao.delete(id);
    }

    private void validateUser(User user) throws InvalidEntryException {
        List<String> errors = new ArrayList<>();

        // check properties
        if (user.getFirstName() == null
                || user.getFirstName().trim().isEmpty()) {
            errors.add("Invalid User First Name");
        }
        if (user.getLastName() == null
                || user.getLastName().trim().isEmpty()) {
            errors.add("Invalid User Last Name");
        }
        if (user.getEmail() == null
                || user.getEmail().trim().isEmpty()) {
            errors.add("Invalid User Email");
        }
        if (user.getPassword() == null
                || user.getPassword().trim().isEmpty()) {
            errors.add("Invalid User Password");
        }
        if ((user.getRole() == null
                || user.getRole().trim().isEmpty())
                || !(user.getRole().equalsIgnoreCase("Admin")
                || user.getRole().equalsIgnoreCase("Sales")
                || user.getRole().equalsIgnoreCase("Disabled"))) {
            errors.add("Invalid User Role");
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

    @Override
    public User login(User user) throws InvalidLoginException {
        List<User> validUsers = userDao.readAll();
        if (user.getEmail() != null && user.getPassword() != null) {
            for (User u : validUsers) {
                if (user.getEmail().equals(u.getEmail())
                        && user.getPassword().equals(u.getPassword())) {
                    return u;
                }
            }
        }
        throw new InvalidLoginException("Invalid User Name: "+user.getEmail()
                +" or Password: "+user.getPassword());
    }
}
