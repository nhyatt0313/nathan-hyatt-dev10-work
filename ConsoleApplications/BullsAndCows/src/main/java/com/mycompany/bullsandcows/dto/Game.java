/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dto;

/**
 *
 * @author nhyat
 */
public class Game {
    private int gameId;
    private int answer;
    private boolean finished;


    public int getGameId() {
        return gameId;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    } 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.gameId;
        hash = 43 * hash + this.answer;
        hash = 43 * hash + (this.finished ? 1 : 0);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.answer != other.answer) {
            return false;
        }
        if (this.finished != other.finished) {
            return false;
        }
        return true;
    }
    
    
    
}
