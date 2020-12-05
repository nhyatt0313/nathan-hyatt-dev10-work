/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.dao;


import com.mycompany.moviedatabase.dto.Movie;
import java.util.List;

/**
 *
 * @author nhyat
 */
public interface MovieDatabaseDao {

        Movie addMovie(Movie movie);

        List<Movie> getAllMovies();

        Movie getMovie(int movieId);

        Movie removeMovie(int movieId);

        List<Movie> searchMovies(String searchValue);

        Movie editMovie(Movie movie);
}
