/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.dao;

import com.mycompany.moviedatabase.dto.Movie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nhyat
 */
@Repository
public class MovieDatabaseDaoSqlImpl implements MovieDatabaseDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Movie addMovie(Movie movie) {
        jdbc.update("INSERT INTO Movies ("
                + "   movieTitle,"
                + "   movieDirectorName,"
                + "   movieStudio,"
                + "   movieReleaseDate,"
                + "   movieMpaaRating,"
                + "   movieUserRating,"
                + "   deleted) "
                + "VALUES "
                + "   (?, ?, ?, ?, ?, ?, FALSE)",
                movie.getTitle(),
                movie.getDirectorName(),
                movie.getStudio(),
                movie.getReleaseDate(),
                movie.getMpaaRating(),
                movie.getUserRating());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        movie.setMovieId(newId);
        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        return jdbc.query("SELECT * FROM Movies WHERE deleted = FALSE", new MovieMapper());
    }

    @Override
    public Movie getMovie(int movieId) {
        try {
            return jdbc.queryForObject("SELECT * FROM Movies WHERE (deleted = FALSE) AND (movieId = ?)", new MovieMapper(), movieId);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Movie removeMovie(int movieId) {
        Movie movie = getMovie(movieId);
        if (movie != null){
            jdbc.update("UPDATE Movies SET deleted = ? WHERE movieId = ?", true, movieId);
        }
        return movie;
    }

    @Override
    public List<Movie> searchMovies(String searchValue) {
        return jdbc.query("SELECT * FROM Movies WHERE (deleted = FALSE) AND (movieTitle LIKE ?)", new MovieMapper(), searchValue + "%");
    }

    @Override
    public Movie editMovie(Movie movie) {
            jdbc.update("UPDATE Movies SET "
                    + "     movieTitle = ?, "
                    + "     movieDirectorName = ?, "
                    + "     movieStudio = ?, "
                    + "     movieReleaseDate = ?, "
                    + "     movieMpaaRating = ?, "
                    + "     movieUserRating = ? "
                    + " WHERE movieId = ?", 
                            movie.getTitle(), 
                            movie.getDirectorName(),
                            movie.getStudio(),
                            movie.getReleaseDate(),
                            movie.getMpaaRating(),
                            movie.getUserRating(),
                            movie.getMovieId());
 
        return movie;
        
    }

    private static final class MovieMapper implements RowMapper<Movie> {

        @Override
        public Movie mapRow(ResultSet rs, int i) throws SQLException {
            Movie movie = new Movie();
            movie.setMovieId(rs.getInt("movieId"));
            movie.setTitle(rs.getString("movieTitle"));
            movie.setDirectorName(rs.getString("movieDirectorName"));
            movie.setStudio(rs.getString("movieStudio"));
            movie.setReleaseDate(rs.getDate("movieReleaseDate").toLocalDate());
            movie.setMpaaRating(rs.getString("movieMpaaRating"));
            movie.setUserRating(rs.getString("movieUserRating"));
            return movie;
        }

    }

}
