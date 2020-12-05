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

import com.sg.spaceshipdealership.dto.User;


@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbc;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public User read(Integer id) {
        String query = "SELECT * FROM User WHERE userId = ?";
        try {
            return jdbc.queryForObject(query, new UserMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> readAll() {
        String query = "SELECT * FROM User";
        return jdbc.query(query, new UserMapper());
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO user (firstName, lastName, email, role, password) VALUES (?, ?, ?, ?, ?)";
        jdbc.update(query,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.getPassword()
        );

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setId(newId);

        return user;
    }

    @Override
    public void update(User user) {
        String query = "UPDATE User SET "
                + "firstName = ?, "
                + "lastName = ?, "
                + "email = ?, "
                + "role = ?, "
                + "password = ? WHERE userId = ?";
        jdbc.update(query,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.getPassword(),
                user.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        
        String query = "DELETE FROM user where userId = ?";
        jdbc.update(query, id);

    }

    private static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("userId"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setEmail(rs.getString("email"));
            user.setRole(rs.getString("role"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

}