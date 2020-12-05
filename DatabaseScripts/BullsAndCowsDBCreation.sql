DROP DATABASE IF EXISTS BullsAndCowsDB;
CREATE DATABASE BullsAndCowsDB;

USE BullsAndCowsDB;

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
