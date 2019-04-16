package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import com.lists.web.compares.Compares;
import com.lists.web.descriptor.*;
import com.lists.web.descriptorType.DescriptorType;
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
        return (thing, cq, cb) -> {
            Subquery<Compares> subquery = cq.subquery(Compares.class);
            Root<Compares> subqueryRoot = subquery.from(Compares.class);

            subquery.select(subqueryRoot).where(cb.equal(subqueryRoot.join("comparator"), comparator),
                    cb.equal(subqueryRoot.get("thing"), thing));

            return cb.not(cb.exists(subquery));
        };
    }

    static Specification<Thing> comparesValueGreaterThan(Comparator comparator, Double value) {
        return (thing, cq, cb) -> {
            Subquery<Compares> subquery = cq.subquery(Compares.class);
            Root<Compares> subqueryRoot = subquery.from(Compares.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("comparator"), comparator),
                    cb.greaterThan(subqueryRoot.get("score"), value),
                    cb.equal(subqueryRoot.get("thing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> comparesValueLessThan(Comparator comparator, Double value) {
        return (thing, cq, cb) -> {
            Subquery<Compares> subquery = cq.subquery(Compares.class);
            Root<Compares> subqueryRoot = subquery.from(Compares.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("comparator"), comparator),
                    cb.lessThan(subqueryRoot.get("score"), value),
                    cb.equal(subqueryRoot.get("thing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> hasDateDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> cb.and(cb.equal(thing.join("dateDescriptors").join("descriptorType"), descriptorType));
    }

    static Specification<Thing> notHasDateDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> {
            Subquery<DateDescriptor> subquery = cq.subquery(DateDescriptor.class);
            Root<DateDescriptor> subqueryRoot = subquery.from(DateDescriptor.class);

            subquery.select(subqueryRoot).where(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("describedThing"), thing));

            return cb.not(cb.exists(subquery));
        };
    }

    static Specification<Thing> dateDescriptorValueEquals(DescriptorType descriptorType, Date value) {
        return (thing, cq, cb) -> {
            Subquery<DateDescriptor> subquery = cq.subquery(DateDescriptor.class);
            Root<DateDescriptor> subqueryRoot = subquery.from(DateDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("value"), value),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> dateDescriptorValueGreaterThan(DescriptorType descriptorType, Date value) {
        return (thing, cq, cb) -> {
            Subquery<DateDescriptor> subquery = cq.subquery(DateDescriptor.class);
            Root<DateDescriptor> subqueryRoot = subquery.from(DateDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.greaterThan(subqueryRoot.get("value"), value),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> dateDescriptorValueLessThan(DescriptorType descriptorType, Date value) {
        return (thing, cq, cb) -> {
            Subquery<DateDescriptor> subquery = cq.subquery(DateDescriptor.class);
            Root<DateDescriptor> subqueryRoot = subquery.from(DateDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.lessThan(subqueryRoot.get("value"), value),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> hasDoubleDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> cb.and(cb.equal(thing.join("doubleDescriptors").join("descriptorType"), descriptorType));
    }

    static Specification<Thing> notHasDoubleDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> {
            Subquery<DoubleDescriptor> subquery = cq.subquery(DoubleDescriptor.class);
            Root<DoubleDescriptor> subqueryRoot = subquery.from(DoubleDescriptor.class);

            subquery.select(subqueryRoot).where(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("describedThing"), thing));

            return cb.not(cb.exists(subquery));
        };
    }

    static Specification<Thing> doubleDescriptorValueEquals(DescriptorType descriptorType, Double value) {
        return (thing, cq, cb) -> {
            Subquery<DoubleDescriptor> subquery = cq.subquery(DoubleDescriptor.class);
            Root<DoubleDescriptor> subqueryRoot = subquery.from(DoubleDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("value"), value),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> doubleDescriptorValueGreaterThan(DescriptorType descriptorType, Double value) {
        return (thing, cq, cb) -> {
            Subquery<DoubleDescriptor> subquery = cq.subquery(DoubleDescriptor.class);
            Root<DoubleDescriptor> subqueryRoot = subquery.from(DoubleDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.greaterThan(subqueryRoot.get("value"), value),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> doubleDescriptorValueLessThan(DescriptorType descriptorType, Double value) {
        return (thing, cq, cb) -> {
            Subquery<DoubleDescriptor> subquery = cq.subquery(DoubleDescriptor.class);
            Root<DoubleDescriptor> subqueryRoot = subquery.from(DoubleDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.lessThan(subqueryRoot.get("value"), value),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> hasIntegerDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> cb.and(cb.equal(thing.join("integerDescriptors").join("descriptorType"), descriptorType));
    }

    static Specification<Thing> notHasIntegerDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> {
            Subquery<IntegerDescriptor> subquery = cq.subquery(IntegerDescriptor.class);
            Root<IntegerDescriptor> subqueryRoot = subquery.from(IntegerDescriptor.class);

            subquery.select(subqueryRoot).where(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("describedThing"), thing));

            return cb.not(cb.exists(subquery));
        };
    }

    static Specification<Thing> integerDescriptorValueEquals(DescriptorType descriptorType, Integer value) {
        return (thing, cq, cb) -> {
            Subquery<IntegerDescriptor> subquery = cq.subquery(IntegerDescriptor.class);
            Root<IntegerDescriptor> subqueryRoot = subquery.from(IntegerDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("value"), value),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> integerDescriptorValueGreaterThan(DescriptorType descriptorType, Integer value) {
        return (thing, cq, cb) -> {
            Subquery<IntegerDescriptor> subquery = cq.subquery(IntegerDescriptor.class);
            Root<IntegerDescriptor> subqueryRoot = subquery.from(IntegerDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.greaterThan(subqueryRoot.get("value"), value),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> integerDescriptorValueLessThan(DescriptorType descriptorType, Integer value) {
        return (thing, cq, cb) -> {
            Subquery<IntegerDescriptor> subquery = cq.subquery(IntegerDescriptor.class);
            Root<IntegerDescriptor> subqueryRoot = subquery.from(IntegerDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.lessThan(subqueryRoot.get("value"), value),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> hasLocationDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> cb.and(cb.equal(thing.join("locationDescriptors").join("descriptorType"), descriptorType));
    }

    static Specification<Thing> notHasLocationDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> {
            Subquery<LocationDescriptor> subquery = cq.subquery(LocationDescriptor.class);
            Root<LocationDescriptor> subqueryRoot = subquery.from(LocationDescriptor.class);

            subquery.select(subqueryRoot).where(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("describedThing"), thing));

            return cb.not(cb.exists(subquery));
        };
    }

    static Specification<Thing> locationDescriptorValueEquals(DescriptorType descriptorType, Double[] locationPair) {
        return (thing, cq, cb) -> {
            Subquery<LocationDescriptor> subquery = cq.subquery(LocationDescriptor.class);
            Root<LocationDescriptor> subqueryRoot = subquery.from(LocationDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("latitude"), locationPair[0]),
                    cb.equal(subqueryRoot.get("longitude"), locationPair[1]),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> locationDescriptorWithin(DescriptorType descriptorType, Double[] locationPair, Double distance) {
        return (thing, cq, cb) -> {
            Subquery<LocationDescriptor> subquery = cq.subquery(LocationDescriptor.class);
            Root<LocationDescriptor> subqueryRoot = subquery.from(LocationDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("describedThing"), thing),
                    cb.greaterThan(subqueryRoot.get("latitude"), locationPair[0]-distance),
                    cb.lessThan(subqueryRoot.get("latitude"), locationPair[0]+distance),
                    cb.greaterThan(subqueryRoot.get("longitude"), locationPair[1]-distance),
                    cb.lessThan(subqueryRoot.get("longitude"), locationPair[1]+distance)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> hasReferenceThingDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> cb.and(cb.equal(thing.join("referenceThingDescriptors").join("descriptorType"), descriptorType));
    }

    static Specification<Thing> notHasReferenceThingDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> {
            Subquery<ReferenceThingDescriptor> subquery = cq.subquery(ReferenceThingDescriptor.class);
            Root<ReferenceThingDescriptor> subqueryRoot = subquery.from(ReferenceThingDescriptor.class);

            subquery.select(subqueryRoot).where(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("describedThing"), thing));

            return cb.not(cb.exists(subquery));
        };
    }

    static Specification<Thing> referenceThingDescriptorValueEquals(DescriptorType descriptorType, Integer referenceThingID) {
        return (thing, cq, cb) -> {
            Subquery<ReferenceThingDescriptor> subquery = cq.subquery(ReferenceThingDescriptor.class);
            Root<ReferenceThingDescriptor> subqueryRoot = subquery.from(ReferenceThingDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("referenceThing").get("thingID"), referenceThingID),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> hasResourceDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> cb.and(cb.equal(thing.join("resourceDescriptors").join("descriptorType"), descriptorType));
    }

    static Specification<Thing> notHasResourceDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> {
            Subquery<ResourceDescriptor> subquery = cq.subquery(ResourceDescriptor.class);
            Root<ResourceDescriptor> subqueryRoot = subquery.from(ResourceDescriptor.class);

            subquery.select(subqueryRoot).where(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("describedThing"), thing));

            return cb.not(cb.exists(subquery));
        };
    }

    static Specification<Thing> resourceDescriptorValueEquals(DescriptorType descriptorType, String value) {
        return (thing, cq, cb) -> {
            Subquery<ResourceDescriptor> subquery = cq.subquery(ResourceDescriptor.class);
            Root<ResourceDescriptor> subqueryRoot = subquery.from(ResourceDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("value"), value),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> resourceDescriptorValueContains(DescriptorType descriptorType, String value) {
        return (thing, cq, cb) -> {
            Subquery<ResourceDescriptor> subquery = cq.subquery(ResourceDescriptor.class);
            Root<ResourceDescriptor> subqueryRoot = subquery.from(ResourceDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.like(subqueryRoot.get("value"), "%" + value + "%"),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> hasStringDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> cb.and(cb.equal(thing.join("stringDescriptors").join("descriptorType"), descriptorType));
    }

    static Specification<Thing> notHasStringDescriptor(DescriptorType descriptorType) {
        return (thing, cq, cb) -> {
            Subquery<StringDescriptor> subquery = cq.subquery(StringDescriptor.class);
            Root<StringDescriptor> subqueryRoot = subquery.from(StringDescriptor.class);

            subquery.select(subqueryRoot).where(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("describedThing"), thing));

            return cb.not(cb.exists(subquery));
        };
    }

    static Specification<Thing> stringDescriptorValueEquals(DescriptorType descriptorType, String value) {
        return (thing, cq, cb) -> {
            Subquery<StringDescriptor> subquery = cq.subquery(StringDescriptor.class);
            Root<StringDescriptor> subqueryRoot = subquery.from(StringDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.equal(subqueryRoot.get("value"), value),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }

    static Specification<Thing> stringDescriptorValueContains(DescriptorType descriptorType, String value) {
        return (thing, cq, cb) -> {
            Subquery<StringDescriptor> subquery = cq.subquery(StringDescriptor.class);
            Root<StringDescriptor> subqueryRoot = subquery.from(StringDescriptor.class);

            subquery.select(subqueryRoot).where(cb.and(cb.equal(subqueryRoot.join("descriptorType"), descriptorType),
                    cb.like(subqueryRoot.get("value"), "%" + value + "%"),
                    cb.equal(subqueryRoot.get("describedThing"), thing)));

            return cb.exists(subquery);
        };
    }
}
