CREATE TABLE entity
(
entityID int NOT NULL AUTO_INCREMENT,
title char(100) not null,
createUserID char(25),
changeUserID char(25),
PRIMARY KEY (entityID)
);
insert into entity (title) value ('Base Entity');

CREATE TABLE descriptor
(
descriptorID int NOT NULL AUTO_INCREMENT,
title CHAR,
valueType CHAR,
valueData CHAR,
nullable Boolean DEFAULT true,
describesEntityID INT,
createUserID char(25),
changeUserID char(25),
PRIMARY KEY (descriptorID),
FOREIGN KEY (describesEntityID)
REFERENCES entity(entityID)
ON DELETE CASCADE
);

CREATE TABLE childOf
(
parentEntityID int,
childEntityID int,
createUserID char(25),
changeUserID char(25),
PRIMARY KEY (parentEntityID, childEntityID),
FOREIGN KEY (parentEntityID)
REFERENCES entity(entityID)
ON DELETE CASCADE,
FOREIGN KEY (childEntityID)
REFERENCES entity(entityID)
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
entityID int,
score float DEFAULT 1500,
createUserID char(25),
changeUserID char(25),
PRIMARY KEY (comparatorID, entityID),
FOREIGN KEY (comparatorID)
REFERENCES comparator(comparatorID)
ON DELETE CASCADE,
FOREIGN KEY (entityID)
REFERENCES entity(entityID)
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
winnerEntityID int,
loserEntityID int,
comparatorID int,
userID char(25),
PRIMARY KEY (comparatorID, winnerEntityID, loserEntityID, userID),
FOREIGN KEY (comparatorID)
REFERENCES comparator(comparatorID)
ON DELETE CASCADE,
FOREIGN KEY (winnerEntityID)
REFERENCES entity(entityID)
ON DELETE CASCADE,
FOREIGN KEY (loserEntityID)
REFERENCES entity(entityID)
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

create table customSetEntity
(
customSetID int,
entityID int,
PRIMARY KEY (customSetID, entityID),
FOREIGN KEY (customSetID)
REFERENCES customSet(customSetID)
ON DELETE CASCADE,
FOREIGN KEY (entityID)
REFERENCES entity(entityID)
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