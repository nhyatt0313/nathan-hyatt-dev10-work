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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.dto.User;

@Repository
public class MakeDaoImpl implements MakeDao {

    private final JdbcTemplate jdbc;

    public MakeDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Make create(Make make) {

        String query = "INSERT INTO Make (name, dateAdded, userId) VALUES (?, ?, ?)";
        jdbc.update(query,
                make.getName(),
                make.getDateAdded(),
                make.getUser().getId()
        );

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        make.setId(newId);

        return make;
    }

    @Override
    public Make read(Integer id) {
        String query = "SELECT * FROM Make WHERE makeId = ?";
        try {
            return jdbc.queryForObject(query, new MakeDaoImpl.MakeMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Make> readAll() {
        String query = "SELECT * FROM Make";
        return jdbc.query(query, new MakeDaoImpl.MakeMapper());
    }

    @Override
    public void update(Make make) {
        String query = "UPDATE Make SET "
                + "name = ?, "
                + "dateAdded = ?, "
                + "userId = ? WHERE makeId = ?";
        jdbc.update(query,
                make.getName(),
                make.getDateAdded(),
                make.getUser().getId(),
                make.getId()
        );

    }

    private static class MakeMapper implements RowMapper<Make> {

        @Override
        public Make mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("userId"));

            Make make = new Make();
            make.setId(rs.getInt("makeId"));
            make.setName(rs.getString("Make.name"));
            make.setDateAdded(LocalDate.parse(rs.getString("dateAdded")));
            make.setUser(user);

            return make;
        }
    }
    
}
