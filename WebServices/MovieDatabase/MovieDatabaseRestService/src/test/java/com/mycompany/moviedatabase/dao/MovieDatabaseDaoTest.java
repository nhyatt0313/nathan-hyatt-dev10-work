///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.moviedatabase.dao;
//
//import com.mycompany.moviedatabase.dto.Movie;
//import java.time.LocalDate;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// *
// * @author nhyat
// */
//public class MovieDatabaseDaoTest {
//    
//    MovieDatabaseDao dao;
//
//    public MovieDatabaseDaoTest() {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        dao= ctx.getBean("daoStub", MovieDatabaseDao.class);
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//        List<Movie> movieList = dao.getAllMovies();
//        movieList.forEach((movie) -> {
//            dao.removeMovie(movie.getMovieId());
//        });
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of addMovie method, of class MovieDatabaseDao.
//     */
//    @Test
//    public void testAddGetMovie() {
//        Movie movie = new Movie(1);
//        movie.setTitle("movie 1");
//        movie.setDirectorName("director 1");
//        movie.setReleaseDate(LocalDate.now());
//        movie.setStudio("studio 1");
//        movie.setMpaaRating("mpaa 1");
//        movie.setUserRating("user 1");
//        
//        dao.addMovie(movie);
//        
//        Movie fromDao = dao.getMovie(movie.getMovieId());
//        
//        assertEquals(movie, fromDao);
//    }
//
//    /**
//     * Test of getAllMovies method, of class MovieDatabaseDao.
//     */
//    @Test
//    public void testGetAllMovies() {
//    }
//
//    /**
//     * Test of removeMovie method, of class MovieDatabaseDao.
//     */
//    @Test
//    public void testRemoveMovie() {
//    }
//
//    /**
//     * Test of searchMovies method, of class MovieDatabaseDao.
//     */
//    @Test
//    public void testSearchMovies() {
//    }
//
//}
