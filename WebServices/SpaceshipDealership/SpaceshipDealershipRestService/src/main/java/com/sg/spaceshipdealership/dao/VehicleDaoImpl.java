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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.dto.Model;
import com.sg.spaceshipdealership.dto.User;
import com.sg.spaceshipdealership.dto.Vehicle;

@Repository
public class VehicleDaoImpl implements VehicleDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Vehicle create(Vehicle vehicle) {
        String query = "INSERT INTO Vehicle ("
                + "year,"
                + "mileage,"
                + "isNew,"
                + "salePrice,"
                + "style,"
                + "interior,"
                + "trans,"
                + "msrp,"
                + "color,"
                + "vin,"
                + "description,"
                + "featured,"
                + "sold,"
                + "fileImg,"
                + "makeId,"
                + "modelId,"
                + "userId) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(query,
                vehicle.getYear(),
                vehicle.getMileage(),
                vehicle.isNewVehicle(),
                vehicle.getSalePrice(),
                vehicle.getStyle(),
                vehicle.getInterior(),
                vehicle.getTrans(),
                vehicle.getMsrp(),
                vehicle.getColor(),
                vehicle.getVin(),
                vehicle.getDescription(),
                vehicle.isFeatured(),
                vehicle.isSold(),
                vehicle.getFileImg(),
                (int) vehicle.getMake().getId(),
                (int) vehicle.getModel().getId(),
                (int) vehicle.getUser().getId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        vehicle.setId(newId);

        return vehicle;
    }

    @Override
    public Vehicle read(Integer id) {
        String query = "SELECT * FROM Vehicle WHERE vehicleId = ?";
        return jdbc.queryForObject(query, new VehicleMapper(), id);
    }

    @Override
    public void update(Vehicle vehicle) {
        String query = "UPDATE Vehicle SET "
                + "year = ?,"
                + "mileage = ?,"
                + "isNew = ?,"
                + "salePrice = ?,"
                + "style = ?,"
                + "interior = ?,"
                + "trans = ?,"
                + "msrp = ?,"
                + "color = ?,"
                + "vin = ?,"
                + "description = ?,"
                + "featured = ?,"
                + "sold = ?,"
                + "fileImg = ?,"
                + "makeId = ?,"
                + "modelId = ?,"
                + "userId = ? "
                + "WHERE vehicleId = ?";
        jdbc.update(query,
                vehicle.getYear(),
                vehicle.getMileage(),
                vehicle.isNewVehicle(),
                vehicle.getSalePrice(),
                vehicle.getStyle(),
                vehicle.getInterior(),
                vehicle.getTrans(),
                vehicle.getMsrp(),
                vehicle.getColor(),
                vehicle.getVin(),
                vehicle.getDescription(),
                vehicle.isFeatured(),
                vehicle.isSold(),
                vehicle.getFileImg(),
                vehicle.getMake().getId(),
                vehicle.getModel().getId(),
                vehicle.getUser().getId(),
                (int) vehicle.getId());
    }

    @Override
    public List<Vehicle> readAll() {
        String query = "SELECT * FROM Vehicle";
        return jdbc.query(query, new VehicleMapper());
    }
    
    @Override
    public List<Vehicle> readAllSorted(boolean isNew) {
        String query = "SELECT * FROM Vehicle"
                + "WHERE ( isNew = ? ) "
                + "ORDER BY year, makeId, modelId";
        return jdbc.query(query, new VehicleMapper(), isNew);
    }
    

    @Override
    @Transactional
    public List<Vehicle> search(String search, Boolean isNew) {
        int year;
        try {
            year = Integer.parseInt(search);
            if (year >= 2000) {
                if (isNew != null) {
                    String query = "SELECT * FROM Vehicle "
                            + "WHERE ( isNew = ? ) "
                            + "AND ( year = ? )";
                    return jdbc.query(query, new VehicleMapper(),
                            isNew,
                            year);
                } else {
                    String query = "SELECT * FROM Vehicle "
                            + "WHERE ( year = ? )";
                    return jdbc.query(query, new VehicleMapper(),
                            year);
                }
            }
        } catch (NumberFormatException e) {
        }

        if (isNew != null) {
            String query = "SELECT * FROM Vehicle "
                    + "JOIN Make ON Vehicle.makeId = Make.makeId "
                    + "JOIN Model ON Vehicle.modelId = Model.modelId "
                    + "WHERE ( isNew = ? ) "
                    + "AND ( Make.name LIKE ? OR Model.name LIKE ? )";
            return jdbc.query(query, new VehicleMapper(),
                    isNew,
                    search + "%",
                    search + "%");
        } else {
            String query = "SELECT * FROM Vehicle "
                    + "JOIN Make ON Vehicle.makeId = Make.makeId "
                    + "JOIN Model ON Vehicle.modelId = Model.modelId "
                    + "WHERE ( Make.name LIKE ? OR Model.name LIKE ? )";
            return jdbc.query(query, new VehicleMapper(),
                    search + "%",
                    search + "%");
        }
    }
    
    @Override
    public List<Vehicle> findFeatured() {
        String query = "SELECT * FROM Vehicle WHERE featured = true LIMIT 8";
        return jdbc.query(query, new VehicleMapper());
    }

    private static class VehicleMapper implements RowMapper<Vehicle> {

        @Override
        public Vehicle mapRow(ResultSet rs, int i) throws SQLException {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(rs.getInt("vehicleId"));

            Make make = new Make();
            make.setId(rs.getInt("makeId"));
            Model model = new Model();
            model.setId(rs.getInt("modelId"));
            User user = new User();
            user.setId(rs.getInt("userId"));

            vehicle.setMake(make);
            vehicle.setModel(model);
            vehicle.setUser(user);

            vehicle.setYear(rs.getInt("year"));
            vehicle.setMileage(rs.getInt("mileage"));
            vehicle.setSalePrice(rs.getBigDecimal("salePrice"));
            vehicle.setMsrp(rs.getBigDecimal("msrp"));
            vehicle.setStyle(rs.getString("style"));
            vehicle.setInterior(rs.getString("interior"));
            vehicle.setTrans(rs.getString("trans"));
            vehicle.setColor(rs.getString("color"));
            vehicle.setVin(rs.getString("vin"));
            vehicle.setDescription(rs.getString("description"));
            vehicle.setFileImg(rs.getString("fileImg"));
            vehicle.setNewVehicle(rs.getBoolean("isNew"));
            vehicle.setFeatured(rs.getBoolean("featured"));
            vehicle.setSold(rs.getBoolean("sold"));

            return vehicle;
        }
    }

}
