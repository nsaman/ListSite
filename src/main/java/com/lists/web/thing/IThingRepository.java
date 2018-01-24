package com.lists.web.thing;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by nick on 1/23/2018.
 */
public interface IThingRepository extends CrudRepository<Thing, Integer> {

    List<Thing> findByParentThing(Thing parentThing);

}
