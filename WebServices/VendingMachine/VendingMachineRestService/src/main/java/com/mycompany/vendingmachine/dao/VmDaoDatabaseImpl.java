/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.Id;
import com.mycompany.vendingmachine.dto.Snack;
import com.mycompany.vendingmachine.exception.VmPersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nhyat
 */
@Repository
public class VmDaoDatabaseImpl implements VmDao{
    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public void buySnack(Snack snack) throws SQLException {
        jdbc.update("UPDATE snacks SET snackNumInStock = ? WHERE snackId = ?", snack.getNumInStock(), snack.getId().toString());
    }

    @Override
    public List<Snack> getAllSnacks(){
        return jdbc.query("SELECT * FROM snacks", new SnackMapper());
    }

    @Override
    public Snack getSnackById(Id id) throws VmPersistenceException {
        try{
            return jdbc.queryForObject("SELECT * FROM snacks WHERE snackId = ?", new SnackMapper(), id.toString());
        } catch (DataAccessException e){
            throw new VmPersistenceException("Failed to find snack in database", new Throwable());
        }
    }
    
    private static final class SnackMapper implements RowMapper<Snack> {

        @Override
        public Snack mapRow(ResultSet rs, int i) throws SQLException {
            Snack snack = new Snack();
            snack.setId(rs.getString("snackId").toUpperCase());
            snack.setType(rs.getString("SnackType"));
            snack.setPrice(rs.getBigDecimal("snackPrice"));
            snack.setNumInStock(rs.getInt("snackNumInStock"));
            
            return snack;
        }
        
    }
}
