/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.service;

import com.mycompany.moviedatabase.TestApplicationConfiguration;
import com.mycompany.moviedatabase.dto.Movie;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author nhyat
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class MovieDatabaseServiceImplTest {
    
    @Autowired
    MovieDatabaseService service;
    
    public MovieDatabaseServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
   @Before
    public void setUp() {
        // empty the database
        List<Movie> movies = service.getAllMovies();
        for(Movie movie : movies) {
            service.removeMovie(movie.getMovieId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addMovie method, of class MovieDatabaseDaoSqlImpl.
     */
    @Test
    public void testAddGetMovie() {
        
        // create Movie and add to database
        Movie movie = new Movie();
        movie.setTitle("title");
        movie.setDirectorName("director");
        movie.setReleaseDate(LocalDate.parse("1111-11-11"));
        movie.setStudio("studio");
        movie.setMpaaRating("mpaa rating");
        movie.setUserRating("user rating");
        
        // add should set the id of the movie
        Movie add = service.addMovie(movie);
        Movie get = service.getMovie(add.getMovieId());
        
        assertEquals(add, get);
        
    }

    /**
     * Test of getAllMovies method, of class MovieDatabaseDaoSqlImpl.
     */
    @Test
    public void testGetAllMovies() {
        
        // create two movies
        Movie movie1 = new Movie();
        movie1.setTitle("title1");
        movie1.setDirectorName("director1");
        movie1.setReleaseDate(LocalDate.parse("1111-01-01"));
        movie1.setStudio("studio1");
        movie1.setMpaaRating("mpaa rating1");
        movie1.setUserRating("user rating1");
        service.addMovie(movie1);
        
        Movie movie2 = new Movie();
        movie2.setTitle("title2");
        movie2.setDirectorName("director2");
        movie2.setReleaseDate(LocalDate.parse("2222-02-02"));
        movie2.setStudio("studio2");
        movie2.setMpaaRating("mpaa rating2");
        movie2.setUserRating("user rating2");
        service.addMovie(movie2);
        
        // get the list of movies
        List<Movie> movies = service.getAllMovies();
        
        // assert that we have 2 movies
        assertEquals(2, movies.size());
        
        // assert that each movie is in the list
        assertTrue(movies.contains(movie1));
        assertTrue(movies.contains(movie2));
    }

    /**
     * Test of removeMovie method, of class MovieDatabaseDaoSqlImpl.
     */
    @Test
    public void testRemoveMovie() {
        
        // create a movie and add it to the database
        Movie movie = new Movie();
        movie.setTitle("title");
        movie.setDirectorName("director");
        movie.setReleaseDate(LocalDate.parse("1111-11-11"));
        movie.setStudio("studio");
        movie.setMpaaRating("mpaa rating");
        movie.setUserRating("user rating");
        service.addMovie(movie);
        
        // remove movie
        service.removeMovie(movie.getMovieId());
        
        Movie removed = service.getMovie(movie.getMovieId());
        
        //assert that the movie is no longer in the database
        assertNull(removed);
        
    }

    /**
     * Test of searchMovies method, of class MovieDatabaseDaoSqlImpl.
     */
    @Test
    public void testSearchMovies() {
        // create two movies
        Movie movie1 = new Movie();
        movie1.setTitle("title1");
        movie1.setDirectorName("director1");
        movie1.setReleaseDate(LocalDate.parse("1111-01-01"));
        movie1.setStudio("studio1");
        movie1.setMpaaRating("mpaa rating1");
        movie1.setUserRating("user rating1");
        service.addMovie(movie1);
        
        Movie movie2 = new Movie();
        movie2.setTitle("title2");
        movie2.setDirectorName("director2");
        movie2.setReleaseDate(LocalDate.parse("2222-02-02"));
        movie2.setStudio("studio2");
        movie2.setMpaaRating("mpaa rating2");
        movie2.setUserRating("user rating2");
        service.addMovie(movie2);
        
        // create another movie to not appear in search
        Movie movie3 = new Movie();
        movie3.setTitle("nottoappear");
        movie3.setDirectorName("director3");
        movie3.setReleaseDate(LocalDate.parse("3333-03-03"));
        movie3.setStudio("studio3");
        movie3.setMpaaRating("mpaa rating3");
        movie3.setUserRating("user rating3");
        service.addMovie(movie3);
        
        // search for mobies beginning with title
        List<Movie> searched = service.searchMovies("title");
        
        // assert that the list size is 2
        assertEquals(2, searched.size());
        
        // assert that only the two that are supposed to be are in the databse
        assertTrue(searched.contains(movie1));
        assertTrue(searched.contains(movie2));
        
        // assert that movie 3 is not in the database
        assertFalse(searched.contains(movie3));
    }

    /**
     * Test of editMovie method, of class MovieDatabaseDaoSqlImpl.
     */
    @Test
    public void testEditMovie() {
        
        // create Movie and add to database
        Movie movie = new Movie();
        movie.setTitle("title");
        movie.setDirectorName("director");
        movie.setReleaseDate(LocalDate.parse("1111-11-11"));
        movie.setStudio("studio");
        movie.setMpaaRating("mpaa rating");
        movie.setUserRating("user rating");
        movie = service.addMovie(movie);
        
        movie.setTitle("changedTitle");
        
        // get old movie from database
        Movie getFromDao = service.getMovie(movie.getMovieId());
        
        // assert that the movies no longer match
        assertNotEquals(movie, getFromDao);
        
        // make the edit
        Movie editFromDao = service.editMovie(movie);
        
        // assert that movie matches the database
        assertEquals(movie, editFromDao);
    }
    
}
