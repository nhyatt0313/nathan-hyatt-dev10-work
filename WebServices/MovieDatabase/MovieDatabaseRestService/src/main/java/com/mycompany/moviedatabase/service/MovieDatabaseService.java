/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.service;

import com.mycompany.moviedatabase.dto.Movie;
import java.util.List;

/**
 *
 * @author nhyat
 */
public interface MovieDatabaseService {
    
    Movie getMovie(int movieId);
    
    List<Movie> getAllMovies();

    Movie addMovie(Movie movie);
    
    Movie removeMovie(int movieId);
    
    Movie editMovie(Movie movie);

    List<Movie> searchMovies(String searchValue);



}
