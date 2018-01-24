CREATE TABLE thing
(
thingID int NOT NULL AUTO_INCREMENT,
title char(100) not null,
createUserID char(25),
changeUserID char(25),
PRIMARY KEY (thingID)
);
insert into thing (title, createUserID, changeUserID) value ('Base Thing', 'system', 'system');

CREATE TABLE descriptor
(
descriptorID int NOT NULL AUTO_INCREMENT,
title CHAR,
valueType CHAR,
valueData CHAR,
nullable Boolean DEFAULT true,
describesThingID INT,
createUserID char(25),
changeUserID char(25),
PRIMARY KEY (descriptorID),
FOREIGN KEY (describesThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE
);

CREATE TABLE childOf
(
parentThingID int,
childThingID int,
createUserID char(25),
changeUserID char(25),
PRIMARY KEY (parentThingID, childThingID),
FOREIGN KEY (parentThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE,
FOREIGN KEY (childThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE
);

create table comparator
(
comparatorID int NOT NULL AUTO_INCREMENT,
title char(100) not null,
createUserID char(25),
changeUserID char(25),
PRIMARY KEY (comparatorID)
);

create table compares
(
comparatorID int,
thingID int,
score float DEFAULT 1500,
createUserID char(25),
changeUserID char(25),
PRIMARY KEY (comparatorID, thingID),
FOREIGN KEY (comparatorID)
REFERENCES comparator(comparatorID)
ON DELETE CASCADE,
FOREIGN KEY (thingID)
REFERENCES thing(thingID)
ON DELETE CASCADE
);

create table user
(
userID char(25) NOT NULL UNIQUE,
PRIMARY KEY (userID)
);

create table userGroup
(
userGroupID int NOT NULL AUTO_INCREMENT,
title char(100) NOT NULL UNIQUE,
PRIMARY KEY (userGroupID)
);

create table userGroupUser
(
userGroupID int,
userID char(25),
PRIMARY KEY (userGroupID, userID),
FOREIGN KEY (userGroupID)
REFERENCES userGroup(userGroupID)
ON DELETE CASCADE,
FOREIGN KEY (userID)
REFERENCES user(userID)
ON DELETE CASCADE
);

create table vote
(
winnerThingID int,
loserThingID int,
comparatorID int,
userID char(25),
PRIMARY KEY (comparatorID, winnerThingID, loserThingID, userID),
FOREIGN KEY (comparatorID)
REFERENCES comparator(comparatorID)
ON DELETE CASCADE,
FOREIGN KEY (winnerThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE,
FOREIGN KEY (loserThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE,
FOREIGN KEY (userID)
REFERENCES user(userID)
ON DELETE CASCADE
);

create table customSet
(
customSetID int,
title char(100) NOT NULL UNIQUE,
createUserID char(25),
changeUserID char(25),
PRIMARY KEY (customSetID)
);

create table customSetThing
(
customSetID int,
thingID int,
PRIMARY KEY (customSetID, thingID),
FOREIGN KEY (customSetID)
REFERENCES customSet(customSetID)
ON DELETE CASCADE,
FOREIGN KEY (thingID)
REFERENCES thing(thingID)
ON DELETE CASCADE
);

create table customSetUserGroupPermissions
(
customSetID int,
userGroupID int,
allowView Boolean DEFAULT false,
allowVote Boolean DEFAULT false,
allowOwner Boolean DEFAULT false,
PRIMARY KEY (customSetID, userGroupID),
FOREIGN KEY (customSetID)
REFERENCES customSet(customSetID)
ON DELETE CASCADE,
FOREIGN KEY (userGroupID)
REFERENCES userGroup(userGroupID)
ON DELETE CASCADE
);

create table customSetComparator
(
customSetID int,
comparatorID int,
PRIMARY KEY (customSetID, comparatorID),
FOREIGN KEY (customSetID)
REFERENCES customSet(customSetID)
ON DELETE CASCADE,
FOREIGN KEY (comparatorID)
REFERENCES comparator(comparatorID)
ON DELETE CASCADE
);