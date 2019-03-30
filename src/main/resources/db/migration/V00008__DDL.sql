ALTER TABLE Comparator
ADD COLUMN createTimestamp DATETIME NOT NULL;
ALTER TABLE Comparator
ADD COLUMN changeTimestamp DATETIME;

ALTER TABLE Compares
ADD COLUMN createTimestamp DATETIME NOT NULL;
ALTER TABLE Compares
ADD COLUMN changeTimestamp DATETIME;

ALTER TABLE Descriptor
ADD COLUMN createTimestamp DATETIME NOT NULL;
ALTER TABLE Descriptor
ADD COLUMN changeTimestamp DATETIME;

ALTER TABLE DescriptorType
ADD COLUMN createTimestamp DATETIME NOT NULL;
ALTER TABLE DescriptorType
ADD COLUMN changeTimestamp DATETIME;

ALTER TABLE Thing
ADD COLUMN createTimestamp DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00.000000';
ALTER TABLE Thing
ADD COLUMN changeTimestamp DATETIME;

ALTER TABLE Vote
ADD COLUMN createUserID char(25);
ALTER TABLE Vote
ADD COLUMN changeUserID char(25);
ALTER TABLE Vote
ADD COLUMN createTimestamp DATETIME NOT NULL;
ALTER TABLE Vote
ADD COLUMN changeTimestamp DATETIME;

Update thing set createTimestamp = now(), changeTimestamp = now() where thingID = 1;