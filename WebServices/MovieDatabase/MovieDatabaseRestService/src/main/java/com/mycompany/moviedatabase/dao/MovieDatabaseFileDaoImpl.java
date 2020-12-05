///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.moviedatabase.dao;
//
//import com.mycompany.moviedatabase.dto.Movie;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author nhyat
// */
//public class MovieDatabaseFileDaoImpl implements MovieDatabaseDao {
//
//    private final String ROSTER_FILE = "movies.txt";
//    private final String DELIMETER = "::";
//    List<Movie> movieList = new ArrayList<>();
//
//    File movies;
//
//    public MovieDatabaseFileDaoImpl() {
//
//        this.movies = new File(ROSTER_FILE);
//
//    }
//
//    private void readMovies() {
//        movieList.clear();
//        Scanner sc = null;
//
//        Movie currentMovie;
//        String currentLine;
//        try {
//            sc = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
//            while (sc.hasNextLine()) {
//
//                currentLine = sc.nextLine();
//                
//
//                String[] temp = currentLine.split(DELIMETER);
//                currentMovie = new Movie(Integer.parseInt(temp[0]));
//                currentMovie.setTitle(temp[1]);
//                currentMovie.setDirectorName(temp[2]);
//                currentMovie.setStudio(temp[3]);
//                
//                LocalDate release = LocalDate.parse(temp[4]); 
//                currentMovie.setReleaseDate(release);
//                
//                currentMovie.setMpaaRating(temp[5]);
//                currentMovie.setUserRating(temp[6]);
//
//                movieList.add(currentMovie);
//            }
//            sc.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(MovieDatabaseFileDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    private void writeMovies() {
//
//        PrintWriter pw;
//        boolean append = false;
//        try {
//            pw = new PrintWriter(new FileWriter("movies.txt", append));
//            for (Movie movie : movieList) {
//
//                pw.print(movie.getMovieId() + DELIMETER);
//                pw.print(movie.getTitle() + DELIMETER);
//                pw.print(movie.getDirectorName() + DELIMETER);
//                pw.print(movie.getStudio() + DELIMETER);
//                pw.print(movie.getReleaseDate() + DELIMETER);
//                pw.print(movie.getMpaaRating() + DELIMETER);
//                pw.print(movie.getUserRating() + "\n");
//
//            }
//            pw.flush();
//            pw.close();
//        } catch (IOException ex) {
//            Logger.getLogger(MovieDatabaseFileDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    @Override
//    public Movie addMovie(Movie movie) {
//        movieList.add(movie);
//        writeMovies();
//        return movie;
//    }
//
//    @Override
//    public List<Movie> getAllMovies() {
//
//        readMovies();
//        return movieList;
//    }
//
//    @Override
//    public Movie getMovie(int movieId) {
//
//        readMovies();
//        for (Movie movie : movieList) {
//            if (movie.getMovieId() == movieId) {
//                return movie;
//            }
//        }
//
//        return null;
//
//    }
//
//    @Override
//    public Movie removeMovie(int movieId) {
//        
//        Movie currentMovie = getMovie(movieId);
//
//        if (currentMovie != null){
//            movieList.remove(currentMovie);
//            writeMovies();
//        }
//        return currentMovie;
//    }
//
//    @Override
//    public List<Movie> searchMovies(String searchValue) {
//        
//        writeMovies();
//        List<Movie> searchMovies = getAllMovies();
//        List<Movie> matchingMovies = new ArrayList<>();
//
//        for (Movie mov : searchMovies) {
//            // get char array from title
//            char[] charTitle = mov.getTitle().toCharArray();
//            char[] compare = searchValue.toCharArray();
//
//            //choose the shorter array to iterate with
//            int iter = 0;
//            if (charTitle.length >= compare.length) {
//                iter = compare.length;
//            } else {
//                iter = charTitle.length;
//            }
//
//            // now iterate through and compare the arrays
//            boolean matchingSearch = true;
//            for (int i = 0; i < iter; i++) {
//                if (compare[i] == charTitle[i]) {
//                    // continue
//                } else {
//                    matchingSearch = false;
//                }
//            }
//            if (matchingSearch) {
//                matchingMovies.add(mov);
//            }
//
//        }
//        return matchingMovies;
//    }
//
//}
