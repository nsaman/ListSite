package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by nick on 1/23/2018.
 */
public interface IThingRepository extends CrudRepository<Thing, Integer> {

    List<Thing> findByParentThing(Thing parentThing);

    @Query("Select t from Thing t join fetch t.compares c where t.parentThing.thingID = :parentThingID and c.comparator.comparatorID = :comparatorID")
    List<Thing> findThingAndComparesByParentAndComparatorDesc(@Param("parentThingID") Integer parentThingID, @Param("comparatorID") Integer comparatorID);

}
