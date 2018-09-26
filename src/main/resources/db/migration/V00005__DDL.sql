drop table compares;

create table compares
(
comparesID int NOT NULL AUTO_INCREMENT,
comparatorID int,
thingID int,
score float DEFAULT 1500,
createUserID char(25),
changeUserID char(25),
PRIMARY KEY (comparesID),
UNIQUE KEY(comparatorID,thingID),
CONSTRAINT FK_compares_1
FOREIGN KEY (comparatorID)
REFERENCES comparator(comparatorID)
ON DELETE CASCADE,
CONSTRAINT FK_compares_2
FOREIGN KEY (thingID)
REFERENCES thing(thingID)
ON DELETE CASCADE
);