/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author nhyat
 */
public class Round {
    private int gameId;
    private int roundId;
    private int guess;
    private String result;
    private LocalDateTime timeStamp;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.gameId;
        hash = 41 * hash + this.roundId;
        hash = 41 * hash + this.guess;
        hash = 41 * hash + Objects.hashCode(this.result);
        hash = 41 * hash + Objects.hashCode(this.timeStamp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.guess != other.guess) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.timeStamp, other.timeStamp)) {
            return false;
        }
        return true;
    }
    
    
    
}
