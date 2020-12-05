/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.dto.Round;
import java.util.List;


/**
 *
 * @author nhyat
 */

public interface RoundDao {
    Round addRound(Round round);

    public List<Round> getAllRounds(int gameId);
    
}
