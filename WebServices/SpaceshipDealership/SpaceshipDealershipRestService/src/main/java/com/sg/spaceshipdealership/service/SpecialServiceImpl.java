/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import com.sg.spaceshipdealership.dao.SpecialDao;
import com.sg.spaceshipdealership.dto.Special;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialServiceImpl implements SpecialService {

    @Autowired
    SpecialDao specialDao;

    @Override
    public Special read(int id) throws DoesNotExistException {
        Special special = specialDao.read(id);
        if (special == null) {
            throw new DoesNotExistException("Special "+id+" Not Found");
        }
        return special;
    }

    @Override
    public List<Special> readAll() {
        return specialDao.readList();
    }

    @Override
    public Special create(Special special) throws InvalidEntryException {
        validateSpecial(special);
        return specialDao.create(special);
    }

    @Override
    public void update(Special special) throws InvalidEntryException, DoesNotExistException{
        read(special.getId());
        validateSpecial(special);
        specialDao.update(special);
    }

    @Override
    public void delete(int id) throws DoesNotExistException {
        Special special = specialDao.read(id);
        if (special == null) {
            throw new DoesNotExistException("Special "+id+" Not Found");
        }
        specialDao.delete(id);
    }

    private void validateSpecial(Special special) throws InvalidEntryException {
         List<String> errors = new ArrayList<>();
        
        if( special.getTitle() == null
                || special.getTitle().trim().isEmpty()) {
            errors.add("Invalid Title");
        }
        
        if(special.getDescription() == null
                || special.getDescription().trim().isEmpty()) {
            errors.add("Invalid Description");
        }
        
        String allErrors = "";
        int numErrors = errors.size();
        int iter = 0;
        if (numErrors != 0) {
            for (String s : errors) {
                iter++;
                if (iter < numErrors) { 
                    allErrors += (s+", ");
                } else {
                    allErrors += s;
                }
            }
            throw new InvalidEntryException(allErrors);
        }
    }

}
