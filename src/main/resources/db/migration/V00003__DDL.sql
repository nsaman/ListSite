Alter table thing add isAbstract Boolean not null;
Update thing set isAbstract = true where thingID = 1;

alter table thing add parentThingID int;
ALTER TABLE thing ADD CONSTRAINT FK_ParentThing FOREIGN KEY (parentThingID) REFERENCES thing(thingID);
drop table childof;