delete from comparator where comparatorID not in (select comparatorID from (select min(comparatorID) as comparatorID from comparator group by title) as inside);
delete from descriptorType where descriptorTypeID not in (select descriptorTypeID from (select min(descriptorTypeID) from descriptorType group by title) as inside);

ALTER TABLE comparator ADD CONSTRAINT title_unique UNIQUE (title);
ALTER TABLE descriptorType ADD CONSTRAINT title_unique UNIQUE (title);