/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.service;

import com.mycompany.bullsandcows.dto.Game;
import com.mycompany.bullsandcows.dto.Round;
import java.util.List;

/**
 *
 * @author nhyat
 */
public interface RoundService {
    
    Round addRound(Round round, int answer);
    
    List<Round> getAllRounds(Game game);
    
}
