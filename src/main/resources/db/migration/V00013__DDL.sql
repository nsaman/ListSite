ALTER TABLE referencethingdescriptor
ADD COLUMN descendentOfThingID int after referenceThingID;

ALTER TABLE referencethingdescriptor ADD CONSTRAINT FK_referenceThing_descriptor_4
FOREIGN KEY (descendentOfThingID) REFERENCES thing(ThingID);