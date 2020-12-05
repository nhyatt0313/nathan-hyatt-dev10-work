/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author nhyat
 */
public class Movie {

    private int movieId;
    
    private String title;
    private LocalDate releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userRating; // allows user to enter additional information, e.g., "5 stars. Would watch again."
    
    public void setMovieId(int movieId){
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.movieId;
        hash = 71 * hash + Objects.hashCode(this.title);
        hash = 71 * hash + Objects.hashCode(this.releaseDate);
        hash = 71 * hash + Objects.hashCode(this.mpaaRating);
        hash = 71 * hash + Objects.hashCode(this.directorName);
        hash = 71 * hash + Objects.hashCode(this.studio);
        hash = 71 * hash + Objects.hashCode(this.userRating);
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
        final Movie other = (Movie) obj;
        if (this.movieId != other.movieId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
            return false;
        }
        if (!Objects.equals(this.directorName, other.directorName)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.userRating, other.userRating)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "ID: " + movieId + " | TITLE: " + title + " | RELEASE: " + releaseDate + " | DIRECTOR: " + directorName + " | MPAA: " + mpaaRating+ " | " + " | STUDIO: " + studio + " | USER_RATING: " + userRating;
    }
    
}
