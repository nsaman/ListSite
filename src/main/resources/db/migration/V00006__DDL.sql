drop table vote;

create table vote
(
voteID int NOT NULL AUTO_INCREMENT,
winnerThingID int NOT NULL,
loserThingID int NOT NULL,
comparatorID int NOT NULL,
userID char(25) NOT NULL,
PRIMARY KEY (voteID),
CONSTRAINT FK_vote_1
FOREIGN KEY (comparatorID)
REFERENCES comparator(comparatorID)
ON DELETE CASCADE,
CONSTRAINT FK_vote_2
FOREIGN KEY (winnerThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE,
CONSTRAINT FK_vote_3
FOREIGN KEY (loserThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE,
CONSTRAINT FK_vote_4
FOREIGN KEY (userID)
REFERENCES user(userID)
ON DELETE CASCADE
);