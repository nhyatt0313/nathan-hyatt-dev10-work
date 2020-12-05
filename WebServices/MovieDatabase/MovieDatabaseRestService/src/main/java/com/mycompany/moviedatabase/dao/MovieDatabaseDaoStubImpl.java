///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.moviedatabase.dao;
//
//import com.mycompany.moviedatabase.dto.Movie;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author nhyat
// */
//public class MovieDatabaseDaoStubImpl implements MovieDatabaseDao {
//
//    Movie onlyMovie;
//    List<Movie> movies;
//
//    public MovieDatabaseDaoStubImpl() {
//        this.onlyMovie = new Movie(1);
//        onlyMovie.setTitle("movie 1");
//        onlyMovie.setDirectorName("director 1");
//        onlyMovie.setReleaseDate(LocalDate.now());
//        onlyMovie.setStudio("studio 1");
//        onlyMovie.setMpaaRating("mpaa 1");
//        onlyMovie.setUserRating("user 1");
//        this.movies = new ArrayList<>();
//        movies.add(onlyMovie);
//    }
//
//    @Override
//    public Movie addMovie(Movie movie) {
//        if (movie.getMovieId() == onlyMovie.getMovieId()) {
//            return onlyMovie;
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<Movie> getAllMovies() {
//        return movies;
//    }
//
//    @Override
//    public Movie getMovie(int movieId) {
//        if (movieId == onlyMovie.getMovieId()) {
//            return onlyMovie;
//        } else {
//            return null;
//        }
//
//    }
//
//    @Override
//    public Movie removeMovie(int movieId) {
//        if (movieId == onlyMovie.getMovieId()) {
//            return onlyMovie;
//        } else {
//            return null;
//        }
//
//    }
//
//    @Override
//    public List<Movie> searchMovies(String searchValue) {
//        if (onlyMovie.getTitle().startsWith(searchValue)){
//            return movies;
//        } else {
//            return null;
//        }
//    }
//
//}
