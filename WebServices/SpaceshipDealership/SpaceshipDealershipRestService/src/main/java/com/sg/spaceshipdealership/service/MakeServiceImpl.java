/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import com.sg.spaceshipdealership.dao.MakeDao;
import com.sg.spaceshipdealership.dto.Make;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MakeServiceImpl implements MakeService {

    @Autowired
    MakeDao makeDao;

    @Autowired
    UserService userService;

    @Override
    public Make create(Make make) throws InvalidEntryException, DoesNotExistException {
        validateMake(make);
        make = makeDao.create(make);
        make.setUser(userService.read(make.getUser().getId()));
        return make;
    }

    @Override
    public Make read(Integer id) throws DoesNotExistException {
        Make make = makeDao.read(id);
        if (make == null) {
            throw new DoesNotExistException("Make " + id + " Not Found");
        }
        make.setUser(userService.read(make.getUser().getId()));
        return make;
    }

    @Override
    public List<Make> readAll() throws DoesNotExistException {
        List<Make> makes = makeDao.readAll();
        for (Make m : makes) {
            m.setUser(userService.read(m.getUser().getId()));

        }
        return makes;
    }

    @Override
    public void update(Make make) throws InvalidEntryException, DoesNotExistException {
        read(make.getId());
        validateMake(make);
        makeDao.update(make);
    }

    private void validateMake(Make make) throws InvalidEntryException {

        make.setDateAdded(LocalDate.now());
        List<String> errors = new ArrayList<>();
        // check properties
        if (make.getName() == null
                || make.getName().trim().isEmpty()) {
            errors.add("Invalid Make Name");
        }
        if (make.getDateAdded() == null) {
            errors.add("Invalid Make Date Added");
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
