/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sg.spaceshipdealership.dto.Special;


@Repository
public class SpecialDaoImpl implements SpecialDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Special read(int id) {
        try {
            return jdbc.queryForObject("SELECT * FROM Specials WHERE specialId = ?", new SpecialMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Special> readList() {
        return jdbc.query("SELECT * FROM Specials", new SpecialMapper());
    }

    @Override
    public Special create(Special special) {
        jdbc.update("INSERT INTO Specials(title, description) VALUES(?,?)",
                special.getTitle(),
                special.getDescription()
        );
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        special.setId(newId);

        return special;
    }

    @Override
    public void update(Special special) {
        jdbc.update("UPDATE Specials SET title = ?, description = ? WHERE specialId = ?",
                special.getTitle(), special.getDescription(), special.getId());
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM Specials WHERE specialId = ?", id);
    }

    private static final class SpecialMapper implements RowMapper<Special> {

        @Override
        public Special mapRow(ResultSet rs, int index) throws SQLException {
            Special sp = new Special();
            sp.setId(rs.getInt("specialId"));
            sp.setTitle(rs.getString("title"));
            sp.setDescription(rs.getString("description"));

            return sp;
        }

    }

}