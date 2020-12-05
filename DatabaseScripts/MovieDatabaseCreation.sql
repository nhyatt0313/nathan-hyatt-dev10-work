DROP DATABASE IF EXISTS MovieDatabase;
CREATE DATABASE MovieDatabase;

USE MovieDatabase;

CREATE TABLE Movies(
	movieId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    movieTitle VARCHAR(50),
    movieDirectorName VARCHAR(50),
    movieStudio VARCHAR(50),
    movieReleaseDate DATE,
    movieMpaaRating VARCHAR(100),
    movieUserRating VARCHAR(100),
    deleted BOOLEAN);
    
INSERT INTO Movies(
    movieTitle,
    movieStudio,
    movieDirectorName,
    movieReleaseDate,
    movieMpaaRating,
    movieUserRating,
    deleted)
VALUES
	('Super Troopers', 'Idontknow', 'Adirec Tor', '2000-01-01', '7', 'pretty funny', FALSE),
    ('Avengers Infinity War', 'Marvel', 'director who', '1675-03-02', '8', 'awesome', FALSE),
    ('Avengers Age of Ultron', 'Marvel', 'direction man', '1212-12-12', '2', 'dont really remember it', FALSE),
    ('Spiderman', 'Marvel', 'some guy', '1999-01-01', '5', '', FALSE),
    ('Spiderman 2', 'Marvel', 'some other guy', '2001-09-09', '4', 'not as good as the first', FALSE),
    ('Land Before Time', 'Notaclue', 'dino man', '1865-01-03', '1000', 'kids love dinos', FALSE),
    ('Jurrasic Park', 'Somestudio', 'another dino man', '1987-09-12', '8', 'adults love dinos too', FALSE),
    ('Planet of the Apes', 'Monkeys', 'a monkey', '8723-09-11', '7', 'everyone likes monkeys', FALSE),
    ('Iron Man', 'Marvel', 'man of steel', '7668-07-09', '8', 'explosions and stuff', FALSE),
    ('Thor Ragnorok', 'Marvel', 'thor', '1876-12-12', '9', 'great movie', FALSE),
    ('The Matrix', 'Stillnoclue', 'Watch out ski brothers', '2100-01-01', '9', 'so cool', FALSE),
    ('Deadpool', 'deadpool', 'deadpool', '1989-01-12', '10', 'very funny', FALSE)
    
    