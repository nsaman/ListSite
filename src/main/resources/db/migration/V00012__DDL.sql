ALTER TABLE customSetThing
ADD COLUMN logicallyDeleted Boolean NOT NULL DEFAULT false;

ALTER TABLE compares ADD CONSTRAINT uniqueKey UNIQUE (comparatorID,thingID,customSetID);