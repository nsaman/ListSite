Alter table Descriptor add referenceEntityID int;
ALTER TABLE Descriptor ADD CONSTRAINT FK_ReferenceEntity FOREIGN KEY (referenceEntityID) REFERENCES entity(entityID);