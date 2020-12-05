/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.ui;

import com.mycompany.moviedatabase.dto.Movie;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nhyat
 */
@Component
public class MovieDatabaseView {

    @Autowired
    UserIO io;

    public MovieDatabaseView(UserIO injectedIo) {
        this.io = injectedIo;
    }

    public void print(String message) {
        io.print(message);
    }

    public void println(String message) {
        print(message + "\n");
    }
    
    public String getInput(String prompt){
        return io.readString(prompt);
    }

    public void displayMenu() {
        println("Main Menu: ");
        println("1. List Movie ID's");
        println("2. Create New Movie");
        println("3. View a Movie");
        println("4. Remove a Movie");
        println("5. Search for a Movie");
        println("6. Edit a Movie");
        println("7. Exit");
    }

    public int readInt(String prompt, int min, int max) {
        return io.readInt(prompt, min, max);

    }

    public Movie getNewMovieInfo(Movie movie) {
        
        
        String newTitle = getInput("Title: ");
        if (!newTitle.isEmpty()){
            movie.setTitle(newTitle);
        }
        
        String newDirector = getInput("Director: ");
        if (!newDirector.isEmpty()){
            movie.setDirectorName(newDirector);
        }
        
        String newStudio = getInput("Studio: ");
        if (!newStudio.isEmpty()){
            movie.setStudio(newStudio);
        }

        String newReleaseDate = getInput("Release Date: ");
        LocalDate date;
        if (!newReleaseDate.isEmpty()){
            while (true){
                try {
                    date = LocalDate.parse(newReleaseDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    break;
                } catch (Exception e){
                    newReleaseDate = getInput("Invalid Date - Try Again: ");
                }
            }
            movie.setReleaseDate(date);
        }
        
        String newMpaaRating= getInput("Mpaa Ratingr: ");
        if (!newMpaaRating.isEmpty()){
            movie.setMpaaRating(newMpaaRating);
        }
        
        String newUserRating= getInput("User Ratingr: ");
        if (!newUserRating.isEmpty()){
            movie.setUserRating(newUserRating);
        }
        
        return movie;
    }

    public void displayCreateMovieBanner() {
        println("=== Create Movie ===");
    }

    public void displayCreateSuccessBanner(Movie movie) {
        try {
            io.readString(movie.getTitle()+" with id: "+movie.getMovieId()+" was successfully created.  Please hit enter to continue");
        } catch (NullPointerException e) {

        }
    }

    public void displayMovie(Movie temp) {
        println("Movie Information: ");
        println("    Title        : " + temp.getTitle());
        println("    Director     : " + temp.getDirectorName());
        println("    MPAA Rating  : " + temp.getMpaaRating());
        println("    Release Date : " + temp.getReleaseDate());
        println("    Studio       : " + temp.getStudio());
        println("    User Rating  : " + temp.getUserRating());
        println("    ID           : " + temp.getMovieId());
    }

    public void displayViewMovieBanner() {
        println("=== View Movie ===");
    }

    public void displayListMoviesBanner() {
        println("=== List Movies ===");
    }

    public void displayRemoveMovieBanner() {
        println("=== Remove Movie ===");
    }

    public void displayRemoveSuccessBanner(Movie movie) {
        try {
            io.readString(movie.getTitle() + " " + " with id: " + movie.getMovieId() + " was removed succesfully. Please hit enter to continue");
        } catch (NullPointerException e) {

        }

    }

    public void displaySearchMovieBanner() {
        println("=== Search Movies ===");
    }

    public void displaySearchResults(List<Movie> movies) {
        if (movies.isEmpty()){
            println("No movies were found");
        } else {
            for (Movie m : movies){
                println("The following movies were found:");
                println("ID: "+m.getMovieId()+" Title: "+m.getTitle());
            }
        }
    }

    public void editMovieBanner() {
        println("=== Edit Movie ===");
    }

    public void displayEditMovieSuccess(Movie movie) {
        println("The following movie was edited to the specified values: ");
        displayMovie(movie);
    }

    public void displayUnknownCommand() {
        println("UNKNOWN COMMAND");
    }

}
