DROP TABLE descriptorLongText;
DROP TABLE descriptor;

CREATE TABLE dateDescriptor
(
descriptorID int NOT NULL AUTO_INCREMENT,
describedThingID INT not null,
descriptorTypeID int not null,
value date,
createUserID char(25),
changeUserID char(25),
createTimestamp DATETIME NOT NULL,
changeTimestamp DATETIME,
PRIMARY KEY (descriptorID),
CONSTRAINT FK_date_descriptor_1
FOREIGN KEY (describedThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE,
CONSTRAINT FK_date_descriptor_2
FOREIGN KEY (descriptorTypeID)
REFERENCES descriptorType(descriptorTypeID)
ON DELETE CASCADE
);

CREATE TABLE doubleDescriptor
(
descriptorID int NOT NULL AUTO_INCREMENT,
describedThingID INT not null,
descriptorTypeID int not null,
value double,
createUserID char(25),
changeUserID char(25),
createTimestamp DATETIME NOT NULL,
changeTimestamp DATETIME,
PRIMARY KEY (descriptorID),
CONSTRAINT FK_double_descriptor_1
FOREIGN KEY (describedThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE,
CONSTRAINT FK_double_descriptor_2
FOREIGN KEY (descriptorTypeID)
REFERENCES descriptorType(descriptorTypeID)
ON DELETE CASCADE
);

CREATE TABLE integerDescriptor
(
descriptorID int NOT NULL AUTO_INCREMENT,
describedThingID INT not null,
descriptorTypeID int not null,
value int,
createUserID char(25),
changeUserID char(25),
createTimestamp DATETIME NOT NULL,
changeTimestamp DATETIME,
PRIMARY KEY (descriptorID),
CONSTRAINT FK_integer_descriptor_1
FOREIGN KEY (describedThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE,
CONSTRAINT FK_integer_descriptor_2
FOREIGN KEY (descriptorTypeID)
REFERENCES descriptorType(descriptorTypeID)
ON DELETE CASCADE
);

CREATE TABLE locationDescriptor
(
descriptorID int NOT NULL AUTO_INCREMENT,
describedThingID INT not null,
descriptorTypeID int not null,
latitude double,
longitude double,
createUserID char(25),
changeUserID char(25),
createTimestamp DATETIME NOT NULL,
changeTimestamp DATETIME,
PRIMARY KEY (descriptorID),
CONSTRAINT FK_location_descriptor_1
FOREIGN KEY (describedThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE,
CONSTRAINT FK_location_descriptor_2
FOREIGN KEY (descriptorTypeID)
REFERENCES descriptorType(descriptorTypeID)
ON DELETE CASCADE
);

CREATE TABLE referenceThingDescriptor
(
descriptorID int NOT NULL AUTO_INCREMENT,
describedThingID INT not null,
descriptorTypeID int not null,
referenceThingID int,
createUserID char(25),
changeUserID char(25),
createTimestamp DATETIME NOT NULL,
changeTimestamp DATETIME,
PRIMARY KEY (descriptorID),
CONSTRAINT FK_referenceThing_descriptor_1
FOREIGN KEY (describedThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE,
CONSTRAINT FK_referenceThing_descriptor_2
FOREIGN KEY (descriptorTypeID)
REFERENCES descriptorType(descriptorTypeID)
ON DELETE CASCADE,
CONSTRAINT FK_referenceThing_descriptor_3
FOREIGN KEY (referenceThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE
);

CREATE TABLE resourceDescriptor
(
descriptorID int NOT NULL AUTO_INCREMENT,
describedThingID INT not null,
descriptorTypeID int not null,
value char(100),
createUserID char(25),
changeUserID char(25),
createTimestamp DATETIME NOT NULL,
changeTimestamp DATETIME,
PRIMARY KEY (descriptorID),
CONSTRAINT FK_resource_descriptor_1
FOREIGN KEY (describedThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE,
CONSTRAINT FK_resource_descriptor_2
FOREIGN KEY (descriptorTypeID)
REFERENCES descriptorType(descriptorTypeID)
ON DELETE CASCADE
);

CREATE TABLE stringDescriptor
(
descriptorID int NOT NULL AUTO_INCREMENT,
describedThingID INT not null,
descriptorTypeID int not null,
value char(100),
createUserID char(25),
changeUserID char(25),
createTimestamp DATETIME NOT NULL,
changeTimestamp DATETIME,
PRIMARY KEY (descriptorID),
CONSTRAINT FK_string_descriptor_1
FOREIGN KEY (describedThingID)
REFERENCES thing(thingID)
ON DELETE CASCADE,
CONSTRAINT FK_string_descriptor_2
FOREIGN KEY (descriptorTypeID)
REFERENCES descriptorType(descriptorTypeID)
ON DELETE CASCADE
);