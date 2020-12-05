/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.dto.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author nhyat
 */
public class RoundMapper implements RowMapper<Round> {

    @Override
    public Round mapRow(ResultSet rs, int i) throws SQLException {
        Round round = new Round();
        round.setGameId(rs.getInt("gameId"));
        round.setGuess(rs.getInt("guess"));
        round.setResult(rs.getString("result"));
        round.setRoundId(rs.getInt("roundId"));
        round.setTimeStamp(LocalDateTime.parse(rs.getString("roundTimeStamp"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        return round;
    }
    

    
}
