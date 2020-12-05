/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.controller;

import com.mycompany.moviedatabase.dto.Movie;
import com.mycompany.moviedatabase.service.MovieDatabaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nhyat
 */
@RestController
@RequestMapping("/MovieDatabase")
public class MovieDatabaseRestController {
    @Autowired
    MovieDatabaseService service;
    
    
    // get all of the movies in the database
    @GetMapping("/movies")
    @CrossOrigin(origins="*")
    public ResponseEntity<List<Movie>> getAllMovies(){
        List<Movie> movies = service.getAllMovies();
        if (movies == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(movies);
        
    }
    
    
    // get a particular movie by id from the database
    @GetMapping("/movies/{id}")
    @CrossOrigin(origins="*")
    public ResponseEntity<Movie> getMovie(@PathVariable int id){
        Movie movie = service.getMovie(id);
        if (movie == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(movie);
    }
    
    // add a movie to the database
    @PostMapping("/add")
    @CrossOrigin(origins="*")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie m){
        
        if (m.getTitle() == null
                || m.getDirectorName() == null
                || m.getReleaseDate() == null
                || m.getStudio() == null
                || m.getMpaaRating() == null
                || m.getUserRating() == null) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        Movie movie = service.addMovie(m);
        return ResponseEntity.ok(movie);
    } 
    
    // remove a movie from the database
    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins="*")
    public ResponseEntity<Movie> deleteMovie(@PathVariable int id){
        Movie movie = service.removeMovie(id);
        if (movie == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(movie);
    }
    
    
    @PutMapping("/edit/{id}")
    @CrossOrigin(origins="*")
    public ResponseEntity<Movie> editMovie(@RequestBody Movie m){
        Movie movie = service.getMovie(m.getMovieId());
        movie = service.editMovie(m);
        return ResponseEntity.ok(movie);
    }

}
