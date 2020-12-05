/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.dto.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author nhyat
 */
public class GameMapper implements RowMapper<Game> {

    @Override
    public Game mapRow(ResultSet rs, int i) throws SQLException {
        Game game = new Game();
        game.setGameId(rs.getInt("gameId"));
        game.setFinished(rs.getBoolean("finished"));
        game.setAnswer(rs.getInt("answer"));
        return game;
    }
    
}
