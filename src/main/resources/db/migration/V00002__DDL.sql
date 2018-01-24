Alter table Descriptor add referenceThingID int;
ALTER TABLE Descriptor ADD CONSTRAINT FK_ReferenceThing FOREIGN KEY (referenceThingID) REFERENCES thing(thingID);