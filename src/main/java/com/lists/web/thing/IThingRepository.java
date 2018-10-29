package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by nick on 1/23/2018.
 */
public interface IThingRepository extends CrudRepository<Thing, Integer> {

    List<Thing> findByParentThing(Thing parentThing);

    @Query("Select t from Thing t join fetch t.compares c where t.parentThing.thingID = :parentThingID and c.comparator.comparatorID = :comparatorID")
    List<Thing> findThingAndComparesByParentAndComparatorDesc(@Param("parentThingID") Integer parentThingID, @Param("comparatorID") Integer comparatorID);

    @Query("Select t from Thing t join fetch t.compares c left join fetch t.descriptors d where t.parentThing.thingID = :parentThingID and c.comparator.comparatorID = :comparatorID and d.descriptorType.descriptorTypeID in (:descriptorTypeRetrievedIDs) and t in (select d.describedThing from Descriptor d where d.descriptorType.descriptorTypeID in (:descriptorTypeSearchedIDs))")
    List<Thing> findThingAndComparesAndDescriptorsByParentAndComparatorAndDescriptorTypesDesc(@Param("parentThingID") Integer parentThingID, @Param("comparatorID") Integer comparatorID, @Param("descriptorTypeSearchedIDs") Collection<Integer> descriptorTypeSearchedIDs, @Param("descriptorTypeRetrievedIDs") Collection<Integer> descriptorTypeRetrievedIDs);

}
