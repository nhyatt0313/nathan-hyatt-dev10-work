/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.service;

import com.mycompany.bullsandcows.dto.Game;
import java.util.List;

/**
 *
 * @author nhyat
 */
public interface GameService {
    
    Game addGame();
    
    Game getGame(int gameId);
    
    List<Game> getAllGames();
    
    void finishGame(Game game);
    
}
