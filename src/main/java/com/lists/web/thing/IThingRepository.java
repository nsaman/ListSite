package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by nick on 1/23/2018.
 */
public interface IThingRepository extends CrudRepository<Thing, Integer>, JpaSpecificationExecutor<Thing> {

    List<Thing> findByParentThing(Thing parentThing);

    static Specification<Thing> hasComparator(Comparator comparator) {
        return (thing, cq, cb) -> cb.equal(thing.join("compares").join("comparator"), comparator);
    }

    static Specification<Thing> hasParentThing(Thing parentThing) {
        return (thing, cq, cb) -> cb.equal(thing.join("parentThing"), parentThing);
    }
}
