/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.service;

import com.mycompany.bullsandcows.dao.GameDao;
import com.mycompany.bullsandcows.dto.Game;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    
    @Autowired
    GameDao gameDao;

    @Override
    public Game addGame() {
        int answer = generateNumber();
        return gameDao.addGame(answer);
    }

    @Override
    public Game getGame(int gameId) {
        return gameDao.getGameById(gameId);
    }

    @Override
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @Override
    public void finishGame(Game game) {
        gameDao.finishGame(game);
    }

    private int generateNumber() {
        int num1 = (int)(Math.random()*9 + 1);
        int num2 = (int)(Math.random()*9 + 1);
        while (num2 == num1){
            num2 = (int)(Math.random()*9 + 1);
        }
        int num3 = (int)(Math.random()*9 + 1);
        while ((num3 == num1)||(num3 == num2)){
            num3 = (int)(Math.random()*9 + 1);
        }
        int num4 = (int)(Math.random()*9 + 1);
        while ((num4 == num1)||(num4 == num2)||(num4 == num3)){
            num4 = (int)(Math.random()*9 + 1);
        }
        
        return num1 + num2*10 + num3*100 + num4*1000;
        
    }
    
}
