/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.service;


import com.mycompany.moviedatabase.dao.MovieDatabaseDao;
import com.mycompany.moviedatabase.dto.Movie;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nhyat
 */
@Service
public class MovieDatabaseServiceImpl implements MovieDatabaseService {
    
    @Autowired
    MovieDatabaseDao dao;
    
//    public MovieDatabaseServiceImpl(MovieDatabaseDao injectedDao){
//        this.dao = injectedDao;
//    }

    @Override
    public Movie addMovie(Movie movie) {
        return dao.addMovie(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return dao.getAllMovies();
    }

    @Override
    public Movie getMovie(int movieId) {
        return dao.getMovie(movieId);
    }

    @Override
    public Movie removeMovie(int movieId) {
        return dao.removeMovie(movieId);
    }

    @Override
    public List<Movie> searchMovies(String searchValue) {
        return dao.searchMovies(searchValue);
    }

    @Override
    public Movie editMovie(Movie movie) {
        return dao.editMovie(movie);
    }
    
}
