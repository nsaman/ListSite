package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import com.lists.web.descriptor.Descriptor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by nick on 1/23/2018.
 */
public interface IThingRepository extends CrudRepository<Thing, Integer>, JpaSpecificationExecutor<Thing> {

    List<Thing> findByParentThing(Thing parentThing);

    static Specification<Thing> hasComparators(Collection<Comparator> comparators) {
        return (thing, cq, cb) -> cb.equal(thing.join("compares").join("comparator"), comparators);
    }
}
