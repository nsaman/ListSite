drop table customSetComparator;

ALTER TABLE vote ADD COLUMN customSetID int after comparatorID;

alter table vote add
CONSTRAINT FK_vote_5
FOREIGN KEY (customSetID)
REFERENCES customSet(customSetID)
ON DELETE CASCADE;

ALTER TABLE compares ADD COLUMN customSetID int after thingID;

alter table compares add
CONSTRAINT FK_compares_3
FOREIGN KEY (customSetID)
REFERENCES customSet(customSetID)
ON DELETE CASCADE;

ALTER TABLE customSet
ADD COLUMN createTimestamp DATETIME NOT NULL;
ALTER TABLE customSet
ADD COLUMN changeTimestamp DATETIME;

drop table customSetThing;

create table customSetThing
(
customSetThingID int NOT NULL AUTO_INCREMENT,
customSetID int,
thingID int,
createUserID char(25),
changeUserID char(25),
createTimestamp DATETIME NOT NULL,
changeTimestamp DATETIME,
PRIMARY KEY (customSetThingID),
CONSTRAINT FK_customSetThing_1
FOREIGN KEY (customSetID)
REFERENCES customSet(customSetID)
ON DELETE CASCADE,
CONSTRAINT FK_customSetThing_2
FOREIGN KEY (thingID)
REFERENCES thing(thingID)
ON DELETE CASCADE
);