/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;


import com.sg.spaceshipdealership.dao.ModelDao;
import com.sg.spaceshipdealership.dto.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ModelDao modelDao;
    
    @Autowired
    UserService userService;
    
    @Autowired
    MakeService makeService;

    @Override
    public Model create(Model model) throws InvalidEntryException, DoesNotExistException{
        validateModel(model);
        model = modelDao.create(model);
        model.setUser(userService.read(model.getUser().getId()));
        model.setMake(makeService.read(model.getMake().getId()));
        return model;
    }

    @Override
    public Model read(Integer id) throws DoesNotExistException {
        Model model = modelDao.read(id);
        if (model == null) {
            throw new DoesNotExistException("Model "+id+" Not Found");
        }
        model.setUser(userService.read(model.getUser().getId()));
        model.setMake(makeService.read(model.getMake().getId()));
        return model;
    }

    @Override
    public List<Model> readAll() throws DoesNotExistException {
        List<Model> models = modelDao.readAll();
        for (Model m : models){
            m.setUser(userService.read(m.getUser().getId()));
            m.setMake(makeService.read(m.getMake().getId()));
        }
        return models;
    }

    @Override
    public void update(Model model) throws InvalidEntryException, DoesNotExistException {
        read(model.getId());
        validateModel(model);
        modelDao.update(model);
    }

    private void validateModel(Model model) throws InvalidEntryException {
        
        model.setDateAdded(LocalDate.now());
        List<String> errors = new ArrayList<>();
        
        // check properties
        if (model.getName() == null
                || model.getName().trim().isEmpty()){
            errors.add("Invalid Model Name");
        }
        if (model.getDateAdded() == null){
            errors.add("Invalid Model Date Added");
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
