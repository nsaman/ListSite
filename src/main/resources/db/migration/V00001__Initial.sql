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
CONSTRAINT FK_descriptor_1
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
CONSTRAINT FK_childOf_1
FOREIGN KEY (parentThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE,
CONSTRAINT FK_childOf_2
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
CONSTRAINT FK_compares_1
FOREIGN KEY (comparatorID)
REFERENCES comparator(comparatorID)
ON DELETE CASCADE,
CONSTRAINT FK_compares_2
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
CONSTRAINT FK_userGroupUser_1
FOREIGN KEY (userGroupID)
REFERENCES userGroup(userGroupID)
ON DELETE CASCADE,
CONSTRAINT FK_userGroupUser_2
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
CONSTRAINT FK_customSetThing_1
FOREIGN KEY (customSetID)
REFERENCES customSet(customSetID)
ON DELETE CASCADE,
CONSTRAINT FK_customSetThing_2
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
CONSTRAINT FK_customSetUserGroupPermissions_1
FOREIGN KEY (customSetID)
REFERENCES customSet(customSetID)
ON DELETE CASCADE,
CONSTRAINT FK_customSetUserGroupPermissions_2
FOREIGN KEY (userGroupID)
REFERENCES userGroup(userGroupID)
ON DELETE CASCADE
);

create table customSetComparator
(
customSetID int,
comparatorID int,
PRIMARY KEY (customSetID, comparatorID),
CONSTRAINT FK_customSetComparator_1
FOREIGN KEY (customSetID)
REFERENCES customSet(customSetID)
ON DELETE CASCADE,
CONSTRAINT FK_customSetComparator_2
FOREIGN KEY (comparatorID)
REFERENCES comparator(comparatorID)
ON DELETE CASCADE
);