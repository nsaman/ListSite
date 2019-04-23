package com.lists.web.CustomSetThing;

import com.lists.web.customSet.CustomSet;
import com.lists.web.thing.Thing;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by nick on 4/22/2019.
 */
public interface ICustomSetThingRepository extends CrudRepository<CustomSetThing, Integer> {

    Set<CustomSetThing> findByThingAndCustomSetAndLogicallyDeletedIsFalse(Thing parentThing, CustomSet customSet);
}
