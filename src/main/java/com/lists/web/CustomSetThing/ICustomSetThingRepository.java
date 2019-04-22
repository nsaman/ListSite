package com.lists.web.CustomSetThing;

import com.lists.web.customSet.CustomSet;
import com.lists.web.thing.Thing;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by nick on 4/22/2019.
 */
public interface ICustomSetThingRepository extends CrudRepository<CustomSetThing, Integer> {


    Set<CustomSetThing> findByThingAndCustomSet(Thing parentThing, CustomSet customSet);

    static Specification<CustomSetThing> hasThingIDAndCustomSetID(Integer thingID, Integer customSetID) {
        return (customSetThing, cq, cb) -> cb.and(cb.equal(customSetThing.join("thing").get("thingID"), thingID),
                                                    cb.equal(customSetThing.join("customSet").get("customSetID"), customSetID));
    }
}
