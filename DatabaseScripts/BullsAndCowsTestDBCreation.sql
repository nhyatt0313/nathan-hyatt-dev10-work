DROP DATABASE IF EXISTS BullsAndCowsTestDB;
CREATE DATABASE BullsAndCowsTestDB;

USE BullsAndCowsTestDB;

CREATE TABLE Game (
	gameId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    answer CHAR(4) NOT NULL,
    finished BOOLEAN NOT NULL
);
	

CREATE TABLE Round (
	roundId INT NOT NULL,
    gameId INT NOT NULL,
    PRIMARY KEY (roundId, gameId),
    guess CHAR(4) NOT NULL,
    result CHAR(7) NOT NULL,
    roundTimeStamp DATETIME NOT NULL,
    FOREIGN KEY fk_roundId_gameId (gameId)
		REFERENCES Game (gameId)
);

insert into game (answer, finished) values (2342, false);

select * from game
