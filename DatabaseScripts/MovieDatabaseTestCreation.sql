DROP DATABASE IF EXISTS MovieDatabaseTest;
CREATE DATABASE MovieDatabaseTest;

USE MovieDatabaseTest;

CREATE TABLE Movies(
	movieId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    movieTitle VARCHAR(50),
    movieDirectorName VARCHAR(50),
    movieStudio VARCHAR(50),
    movieReleaseDate DATE,
    movieMpaaRating VARCHAR(100),
    movieUserRating VARCHAR(100),
    deleted BOOLEAN);