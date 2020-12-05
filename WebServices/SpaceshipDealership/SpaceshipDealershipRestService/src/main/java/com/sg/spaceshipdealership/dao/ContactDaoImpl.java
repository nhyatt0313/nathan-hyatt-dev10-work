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
import org.springframework.transaction.annotation.Transactional;

import com.sg.spaceshipdealership.dto.Contact;
import com.sg.spaceshipdealership.dto.Vehicle;

@Repository
public class ContactDaoImpl implements ContactDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Contact read(int id) {

        String query = "SELECT * FROM Contact WHERE ContactId = ?";
        try {
            return jdbc.queryForObject(query, new ContactMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Contact> readAll() {

        String query = "SELECT * FROM Contact";
        return jdbc.query(query, new ContactMapper());

    }

    @Transactional
    @Override
    public Contact create(Contact contact) {
        String query = "Insert into Contact(Name, Phone, Message, Email) Values(?,?,?,?)";

        jdbc.update(query, contact.getName(), contact.getPhone(), contact.getMessage(), contact.getEmail());

        int newid = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        contact.setId(newid);
        return contact;

    }

    @Override
    public void update(Contact contact) {

        String query = "UPDATE Contact Set Name = ?, Phone = ?, Message = ?, Email = ? WHERE ContactId = ?";
        jdbc.update(query, contact.getName(), contact.getPhone(), contact.getMessage(), contact.getEmail(), contact.getId());
    }

    @Override
    public void delete(int id) {
        String query = "DELETE From Contact WHERE ContactId = ?";
        jdbc.update(query, id);
    }

    public class ContactMapper implements RowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int index) throws SQLException {

            Contact contact = new Contact();
            contact.setId(rs.getInt("ContactId"));
            contact.setName(rs.getString("Name"));
            contact.setPhone(rs.getString("Phone"));
            contact.setMessage(rs.getString("Message"));
            contact.setEmail(rs.getString("Email"));

            return contact;
        }
    }

}
