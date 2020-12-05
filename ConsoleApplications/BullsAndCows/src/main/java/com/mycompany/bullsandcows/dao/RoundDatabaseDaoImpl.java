/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.dto.Round;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
@Profile("database")
public class RoundDatabaseDaoImpl implements RoundDao {

    @Autowired
    JdbcTemplate j;
    
    @Override
    public Round addRound(Round round) {
        
        String sql = "INSERT INTO Round "
                   + " (roundId,"
                   + "  gameId,"
                   + "  guess,"
                   + "  result,"
                   + "  roundTimeStamp)"
                   + "VALUES (?,?,?,?,?);";
        j.update(sql, 
                round.getRoundId(), 
                round.getGameId(), 
                round.getGuess(), 
                round.getResult(), 
                round.getTimeStamp());
        
        return round;
    }

    @Override
    public List<Round> getAllRounds(int gameId) {
        String sql = "SELECT * FROM Round WHERE gameId = ?";
        return j.query(sql, new RoundMapper(), gameId);
    }
    
}
