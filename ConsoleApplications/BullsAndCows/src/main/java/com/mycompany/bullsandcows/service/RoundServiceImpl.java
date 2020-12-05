/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.service;

import com.mycompany.bullsandcows.dao.GameDao;
import com.mycompany.bullsandcows.dao.RoundDao;
import com.mycompany.bullsandcows.dto.Game;
import com.mycompany.bullsandcows.dto.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoundServiceImpl implements RoundService {
    
    @Autowired
    RoundDao roundDao;
    
    @Autowired
    GameDao gameDao;
    

    @Override
    public Round addRound(Round round, int answer) {
        
        List<Round> rounds = getAllRounds(gameDao.getGameById(round.getGameId()));
        round.setRoundId(rounds.size()+1);
        
        String result = calulateResult(round.getGuess(), answer);
        round.setResult(result);
        round.setTimeStamp(LocalDateTime.now());
        return roundDao.addRound(round);

    }
    
    @Override
    public List<Round> getAllRounds(Game game) {
        return roundDao.getAllRounds(game.getGameId());
    }

    private String calulateResult(int guess, int answer) {
        // check number in the *1000 position
        int num1 = (int)(guess/1000);
        int num2 = (int)((guess/100)%10);
        int num3 = (int)((guess/10)%10);
        int num4 = guess%10;
        
        List<Integer> guessArray = new ArrayList<>();
            guessArray.add(num1);
            guessArray.add(num2);
            guessArray.add(num3);
            guessArray.add(num4);
            
        
        int num5 = (int)(answer/1000);
        int num6 = (int)((answer/100)%10);
        int num7 = (int)((answer/10)%10);
        int num8 = answer%10;
        
        List<Integer> answerArray = new ArrayList<>();
            answerArray.add(num5);
            answerArray.add(num6);
            answerArray.add(num7);
            answerArray.add(num8);
        

        // exact
        int e = 0;
        // partial
        int p = 0;
        for (int i = 0; i < 4; i++){
            int guessAtIndex = guessArray.get(i);
            int answerAtIndex = answerArray.get(i);
            if (guessAtIndex == answerAtIndex){
                e++;
            } else if (answerArray.contains(guessAtIndex)){
                p++;
            }
        }
        return "e:"+e+":p:"+p;
    }
    
}
