/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.controller;

import com.mycompany.bullsandcows.dto.Game;
import com.mycompany.bullsandcows.dto.Round;
import com.mycompany.bullsandcows.service.GameService;
import com.mycompany.bullsandcows.service.RoundService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nhyat
 */
@RestController
@RequestMapping("/BullsAndCows")
public class Controller {

    @Autowired
    GameService gameService;

    @Autowired
    RoundService roundService;

    @PutMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public String beginGame() {
        Game game = gameService.addGame();
        return "Game " + game.getGameId() + " started with answer "+game.getAnswer();

    }

    @GetMapping("/game")
    public List<Game> getAllGames() {
        List<Game> games = gameService.getAllGames();
        games.stream().filter((g) -> (!g.isFinished())).forEachOrdered((g) -> {
            g.setAnswer(0000);
        });
        return games;
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> GetGameById(@PathVariable int id) {
        Game game = gameService.getGame(id);
        
        if (game == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        if (!game.isFinished()) {
            game.setAnswer(0000);
        }
        return ResponseEntity.ok(game);
    }

    @PutMapping("/guess")
    public ResponseEntity<Round> addRoundToGame(@RequestBody Round round) {
        Game game = gameService.getGame(round.getGameId());
        if (game == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        Round result = roundService.addRound(round, game.getAnswer());
        if (result.getResult().equals("e:4:p:0")) {
            gameService.finishGame(gameService.getGame(result.getGameId()));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/rounds/{id}")
    public ResponseEntity<List<Round>> findRoundsForGame(@PathVariable int id) {
        Game game = gameService.getGame(id);
        if (game == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(roundService.getAllRounds(game));
    }

}
