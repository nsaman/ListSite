ALTER TABLE descriptor DROP COLUMN title;
ALTER TABLE descriptor DROP COLUMN valueType;
ALTER TABLE descriptor DROP COLUMN valueData;
ALTER TABLE descriptor DROP COLUMN nullable;

CREATE TABLE descriptorType
(
descriptorTypeID int NOT NULL AUTO_INCREMENT,
title CHAR(100),
valueType CHAR(20),
isNullable Boolean DEFAULT true,
createUserID char(25),
changeUserID char(25),
PRIMARY KEY (descriptorTypeID)
);

CREATE TABLE descriptorLongText
(
descriptorID int NOT NULL,
descriptorLongText LONGTEXT,
PRIMARY KEY (descriptorID),
FOREIGN KEY (descriptorID)
REFERENCES descriptor(descriptorID)
ON DELETE CASCADE
);

ALTER TABLE descriptor ADD COLUMN descriptorTypeID int not null;
ALTER TABLE descriptor ADD COLUMN stringValue char(100);
ALTER TABLE descriptor ADD COLUMN intValue int;
ALTER TABLE descriptor ADD COLUMN doubleValue double;
ALTER TABLE descriptor ADD COLUMN dateValue date;
ALTER TABLE descriptor ADD COLUMN longitudeValue double;
ALTER TABLE descriptor ADD COLUMN latitudeValu double;
ALTER TABLE descriptor ADD COLUMN resourceValue char(100);

ALTER TABLE descriptor ADD FOREIGN KEY (descriptorTypeID) REFERENCES descriptorType(descriptorTypeID);