package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import com.lists.web.compares.Compares;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.Date;
import java.util.List;

/**
 * Created by nick on 1/23/2018.
 */
public interface IThingRepository extends CrudRepository<Thing, Integer>, JpaSpecificationExecutor<Thing> {

    List<Thing> findByParentThing(Thing parentThing);

    static Specification<Thing> hasThing(Thing thing) {
        return (rootThing, cq, cb) -> cb.equal(rootThing, thing);
    }

    static Specification<Thing> hasTitle(String title) {
        return (thing, cq, cb) -> cb.equal(thing.get("title"), title);
    }

    static Specification<Thing> titleContains(String title) {
        return (thing, cq, cb) -> cb.like(thing.get("title"), "%" + title + "%");
    }

    static Specification<Thing> isAbstract(Boolean isAbstract) {
        return (thing, cq, cb) -> cb.equal(thing.get("isAbstract"), isAbstract);
    }

    static Specification<Thing> hasParentThing(Thing parentThing) {
        return (thing, cq, cb) -> cb.equal(thing.join("parentThing"), parentThing);
    }

    static Specification<Thing> hasParentThingByID(Integer parentThingID) {
        return (thing, cq, cb) -> cb.equal(thing.join("parentThing").get("thingID"), parentThingID);
    }

    static Specification<Thing> hasCreateUserID(String createUserID) {
        return (thing, cq, cb) -> cb.equal(thing.get("createUserID"), createUserID);
    }

    static Specification<Thing> hasCreateTimestamp(Date date) {
        return (thing, cq, cb) -> cb.equal(thing.get("createTimestamp"), date);
    }

    static Specification<Thing> hasCreateTimestampGreaterThan(Date date) {
        return (thing, cq, cb) -> cb.greaterThan(thing.get("createTimestamp"), date);
    }

    static Specification<Thing> hasCreateTimestampLessThan(Date date) {
        return (thing, cq, cb) -> cb.lessThan(thing.get("createTimestamp"), date);
    }

    static Specification<Thing> hasChangeTimestamp(Date date) {
        return (thing, cq, cb) -> cb.equal(thing.get("changeTimestamp"), date);
    }

    static Specification<Thing> hasChangeTimestampGreaterThan(Date date) {
        return (thing, cq, cb) -> cb.greaterThan(thing.get("changeTimestamp"), date);
    }

    static Specification<Thing> hasChangeTimestampLessThan(Date date) {
        return (thing, cq, cb) -> cb.lessThan(thing.get("changeTimestamp"), date);
    }

    static Specification<Thing> hasComparator(Comparator comparator) {
        return (thing, cq, cb) -> cb.and(cb.equal(thing.join("compares").join("comparator"), comparator));
    }

    static Specification<Thing> notHasComparator(Comparator comparator) {
        return (thing, cq, cb) -> cb.and(cb.equal(thing.join("compares").join("comparator"), comparator).not());
    }

    static Specification<Thing> comparesValueGreaterThan(Comparator comparator, Double value) {
        return (thing, cq, cb) -> {
            Subquery<Compares> subquery = cq.subquery(Compares.class);
            Root<Compares> subqueryRoot = subquery.from(Compares.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("comparator"), comparator),
                cb.greaterThan(subqueryRoot.get("score"), value),
                cb.equal(subqueryRoot.get("thing"),thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> comparesValueLessThan(Comparator comparator, Double value) {
        return (thing, cq, cb) -> {
            Subquery<Compares> subquery = cq.subquery(Compares.class);
            Root<Compares> subqueryRoot = subquery.from(Compares.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("comparator"), comparator),
                    cb.lessThan(subqueryRoot.get("score"), value),
                    cb.equal(subqueryRoot.get("thing"),thing)));

            return cb.exists(subquery);
        };
    }
}
