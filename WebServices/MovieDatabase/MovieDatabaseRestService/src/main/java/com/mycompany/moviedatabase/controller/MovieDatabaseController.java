///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.moviedatabase.controller;
//
//import com.mycompany.moviedatabase.dto.Movie;
//import com.mycompany.moviedatabase.service.MovieDatabaseService;
//import com.mycompany.moviedatabase.ui.MovieDatabaseView;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
///**
// *
// * @author nhyat
// */
//
//public class MovieDatabaseController {
//    
//    
//
//    @Autowired
//    MovieDatabaseView view;
//    @Autowired
//    MovieDatabaseService service;
//
//    private Integer nextMovieId = 0;
//    private Integer inputLowBound = 0;
//    private Integer inputHighBound = 0;
//
////    public MovieDatabaseController(MovieDatabaseView injectedView, MovieDatabaseService injectedService) {
////        this.view = injectedView;
////        this.service = injectedService;
////    }
//
//    public void run() {
//        
//        boolean terminate = false;
//        int menuSelection;
//
//        while (!terminate) {
//            menuSelection = displayMenuAndGetSelection();
//            switch (menuSelection) {
//                case 1: // List Movie ID's
//                    ListMovieIds();
//                    break;
//                case 2: // Create a Movie
//                    addMovie();
//                    break;
//                case 3: // View a Movie 
//                    viewMovie();
//                    break;
//                case 4: // Remove a Movie
//                    removeMovie();
//                    break;
//                case 5: // search for a movie
//                    searchMovie();
//                    break;
//                case 6: // edit a movie
//                    editMovie();
//                    break;
//                case 7: // Exit
//                    terminate = true;
//                    break;
//                default:
//                    unknownCommand();
//                    break;
//            }
//        }
//        view.println("GOOD BYE!");
//    }
//
//    private int displayMenuAndGetSelection() {
//        view.displayMenu();
//        return view.readInt("Please select a choice: ", 1, 7);
//    }
//
//    private Movie addMovie() {
//        view.displayCreateMovieBanner();
//        Movie newMovie = view.getNewMovieInfo(new Movie());
//        service.addMovie(newMovie);
//        view.displayCreateSuccessBanner(newMovie);
//        return newMovie;
//
//    }
//
//    private void ListMovieIds() {
//        view.displayListMoviesBanner();
//        List<Movie> temp = service.getAllMovies();
//        if (temp == null) {
//            view.println("There are no movie's to display");
//        } else {
//            for (Movie mov : temp) {
//                view.println("ID: " + mov.getMovieId() + "\t Title: " + mov.getTitle());
//            }
//        }
//    }
//
//    private void viewMovie() {
//        view.displayViewMovieBanner();
//        // are there movie's?
//        if (service.getAllMovies().isEmpty()) {
//            view.println("There are no movie's to be viewed");
//        } else {
//            // get movie id
//            inputHighBound = getHighestId();
//            inputLowBound = getLowestId();
//            Integer id = view.readInt("Enter the movie id: ", inputLowBound, inputHighBound);
//            // check if id is valid
//            Movie test = service.getMovie(id);
//            if (test == null){
//                view.println("That movie no longer exist in the database");
//                return;
//            }
//            boolean valid = false;
//            while (!valid) {
//                if (test == null) {
//                    test = service.getMovie(view.readInt("Movie not found - try again: ", inputLowBound, inputHighBound));
//                } else {
//                    valid = true;
//                }
//            }
//            view.displayMovie(test);
//        }
//    }
//
//    private Movie removeMovie() {
//        view.displayRemoveMovieBanner();
//        Movie test = null;
//        if (service.getAllMovies().isEmpty()) {
//            view.println("There are no movie's to be removed");
//        } else {
//            inputHighBound = getHighestId();
//            Integer id = view.readInt("Enter movie ID: ", inputLowBound, inputHighBound);
//            test = service.getMovie(id);
//            boolean valid = false;
//            while (!valid) {
//                if (test == null) {
//                    test = service.getMovie(view.readInt("Movie not found - try again: ", inputLowBound, inputHighBound));
//                } else {
//                    test = service.removeMovie(id);
//                    valid = true;
//                }
//            }
//        }
//        view.displayRemoveSuccessBanner(test);
//        return test;
//    }
//    
//    private int getHighestId(){
//        List<Movie> movies = service.getAllMovies();
//        int i = 0;
//        for (Movie m : movies){
//            if (m.getMovieId() > i){
//                i = m.getMovieId();
//            }
//        }
//        return i;
//    }
//    
//    private int getLowestId(){
//        List<Movie> movies = service.getAllMovies();
//        int i = Integer.MAX_VALUE;
//        for (Movie m : movies){
//            if (m.getMovieId() < i){
//                i = m.getMovieId();
//            }
//        }
//        return i;
//    }
//
//    private List<Movie> searchMovie() {
//        view.displaySearchMovieBanner();
//        String searchValue = view.getInput("Search for movies beginning with: ");
//        List<Movie> movies = service.searchMovies(searchValue);
//        view.displaySearchResults(movies);
//        return movies;
//    }
//
//    private Movie editMovie() {
//        view.editMovieBanner();
//        
//        inputLowBound = getLowestId();
//        inputHighBound = getHighestId();
//        
//        int id = view.readInt("Enter the movie id: ", inputLowBound, inputHighBound);
//
//
//        Movie edited = view.getNewMovieInfo(service.getMovie(id));
//        service.editMovie(edited);
//        view.displayEditMovieSuccess(edited);
//        return edited;
//    }
//
//    private void unknownCommand() {
//        view.displayUnknownCommand();
//    }
//
//}
