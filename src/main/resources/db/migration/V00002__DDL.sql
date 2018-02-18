Alter table Descriptor add referenceThingID int;
ALTER TABLE Descriptor ADD CONSTRAINT FK_descriptor_2 FOREIGN KEY (referenceThingID) REFERENCES thing(thingID);