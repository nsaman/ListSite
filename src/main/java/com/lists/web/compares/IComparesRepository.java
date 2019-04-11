package com.lists.web.compares;

import com.lists.web.comparator.Comparator;
import com.lists.web.thing.Thing;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nick on 9/25/2018.
 */
public interface IComparesRepository extends CrudRepository<Compares, Integer> {
    Compares findByThingAndComparator(Thing thing, Comparator comparator);
}
