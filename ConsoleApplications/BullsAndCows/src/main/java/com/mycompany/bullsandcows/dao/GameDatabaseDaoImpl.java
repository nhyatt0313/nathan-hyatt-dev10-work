/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.dto.Game;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile("database")
public class GameDatabaseDaoImpl implements GameDao {

    @Autowired
    JdbcTemplate j;
    
    @Override
    public Game getGameById(int id) {
        String sql = "SELECT * FROM Game WHERE GameId = ?";
        try {
            return j.queryForObject(sql, new GameMapper(), id);
        } catch (DataAccessException e){
            return null;
        }
        
    }

    @Override
    public List<Game> getAllGames() {
        String sql = "SELECT * FROM Game";
        return j.query(sql, new GameMapper());
    }

    @Override
    public Game addGame(int ans) {
        Game game = new Game();
        game.setAnswer(ans);
        game.setFinished(false);
        String sql = "INSERT INTO Game ("
                + "answer, "
                + "finished) "
                + "VALUES (?,?)";
         j.update(sql, game.getAnswer(), game.isFinished());
         
         game.setGameId(j.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
         return game;
    }

    @Override
    public Game finishGame(Game game) {
        j.update("UPDATE Game SET finished = TRUE WHERE gameId = ?", game.getGameId());
        game = getGameById(game.getGameId());
        return game;
    }
    
}
