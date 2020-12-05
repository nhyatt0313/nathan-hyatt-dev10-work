/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.dto.Game;
import java.util.List;

/**
 *
 * @author nhyat
 */
public interface GameDao {
    Game getGameById(int id);
    List<Game> getAllGames();
    Game addGame(int ans);
    Game finishGame(Game game);  
}
