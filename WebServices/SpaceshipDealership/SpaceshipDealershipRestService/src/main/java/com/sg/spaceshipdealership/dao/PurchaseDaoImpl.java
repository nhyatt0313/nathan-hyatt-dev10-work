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

import com.sg.spaceshipdealership.dto.Purchase;
import com.sg.spaceshipdealership.dto.User;
import com.sg.spaceshipdealership.dto.Vehicle;

@Repository
public class PurchaseDaoImpl implements PurchaseDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Purchase read(Integer id) {
        try {
            return jdbc.queryForObject("SELECT * FROM Purchase WHERE purchaseId = ?", new PurchaseMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Purchase> readAll() {
        return jdbc.query("SELECT * FROM Purchase", new PurchaseMapper());
    }

    @Override
    public Purchase create(Purchase purchase) {
        String query = "INSERT INTO Purchase (purchaseCost, purchaseType, firstName, lastName, street, street2, "
                + "city, state, zip, phone, email, vehicleId, userId, purchaseDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbc.update(query,
                purchase.getPurchaseCost(),
                purchase.getPurchaseType(),
                purchase.getFirstName(),
                purchase.getLastName(),
                purchase.getStreet(),
                purchase.getStreet2(),
                purchase.getCity(),
                purchase.getState(),
                purchase.getZip(),
                purchase.getPhone(),
                purchase.getEmail(),
                purchase.getVehicle().getId(),
                purchase.getUser().getId(),
                purchase.getPurchaseDate()
        );

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        purchase.setId(newId);
        return purchase;
    }

    @Override
    public void update(Purchase purchase) {
        String query = "UPDATE Purchase SET "
                + "purchaseCost = ?, "
                + "purchaseType = ?, "
                + "purchaseDate = ?, "
                + "firstName = ?, "
                + "lastName = ?, "
                + "street = ?, "
                + "street2 = ?, "
                + "city = ?, "
                + "state = ?, "
                + "zip = ?, "
                + "phone = ?, "
                + "email = ?, "
                + "vehicleId = ?, "
                + "userId = ? WHERE purchaseId = ?";
        jdbc.update(query,
                purchase.getPurchaseCost(),
                purchase.getPurchaseType(), 
                purchase.getPurchaseDate(),
                purchase.getFirstName(),
                purchase.getLastName(),
                purchase.getStreet(),
                purchase.getStreet2(),
                purchase.getCity(),
                purchase.getState(),
                purchase.getZip(),
                purchase.getPhone(),
                purchase.getEmail(),
                purchase.getVehicle().getId(),
                purchase.getUser().getId(),
                purchase.getId()
        );
    }
    
    private static class PurchaseMapper implements RowMapper<Purchase> {

        @Override
        public Purchase mapRow(ResultSet rs, int i) throws SQLException {
            Vehicle vehicle = new Vehicle();
            User user = new User();
            vehicle.setId(rs.getInt("vehicleId"));
            user.setId(rs.getInt("userId"));
            
            Purchase purchase = new Purchase();
            purchase.setId(rs.getInt("purchaseId"));
            purchase.setPurchaseCost(rs.getBigDecimal("purchaseCost"));
            purchase.setPurchaseType(rs.getString("purchaseType"));
            purchase.setFirstName(rs.getString("firstName"));
            purchase.setLastName(rs.getString("lastName"));
            purchase.setStreet(rs.getString("street"));
            purchase.setStreet2(rs.getString("street2"));
            purchase.setCity(rs.getString("city"));
            purchase.setState(rs.getString("state"));
            purchase.setZip(rs.getInt("zip"));
            purchase.setPhone(rs.getString("phone"));
            purchase.setEmail(rs.getString("email"));
            purchase.setPurchaseDate(rs.getDate("purchaseDate").toLocalDate());
            purchase.setVehicle(vehicle);
            purchase.setUser(user);
            return purchase;
        }
    }
    
}