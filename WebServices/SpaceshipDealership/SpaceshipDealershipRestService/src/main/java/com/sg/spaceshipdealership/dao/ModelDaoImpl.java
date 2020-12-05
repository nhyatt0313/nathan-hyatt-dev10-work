/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.dto.Model;
import com.sg.spaceshipdealership.dto.User;


@Repository
public class ModelDaoImpl implements ModelDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Model read(Integer id) {
        try {
            return jdbc.queryForObject("SELECT * FROM Model WHERE modelId = ?", new ModelMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Model> readAll() {
        return jdbc.query("SELECT * FROM Model", new ModelMapper());
    }

    @Override
    public Model create(Model model) {
        jdbc.update("INSERT INTO Model(name, dateAdded, userId, makeId) VALUES (?, ?, ?, ?)",
                model.getName(),
                model.getDateAdded(),
                model.getUser().getId(),
                model.getMake().getId()
        );
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setId(newId);
        
        return model;
    }

    @Override
    public void update(Model model) {
        String query = "UPDATE Model SET "
                + "name = ?, "
                + "dateAdded = ?, "
                + "userId = ?, "
                + "makeId = ?  WHERE modelId = ?";
        jdbc.update(query,
                model.getName(),
                model.getDateAdded(),
                model.getUser().getId(),
                model.getMake().getId(),
                model.getId()
        );
    }

    static class ModelMapper implements RowMapper<Model> {

        @Override
        public Model mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            Make make = new Make();
            Model model = new Model();
            model.setId((Integer) rs.getInt("modelId"));
            model.setName(rs.getString("Model.name"));
            model.setDateAdded(LocalDate.parse(rs.getString("dateAdded")));
            user.setId((Integer) rs.getInt("userId"));
            make.setId((Integer) rs.getInt("makeId"));
            model.setUser(user);
            model.setMake(make);
            return model;
        }
    }

}