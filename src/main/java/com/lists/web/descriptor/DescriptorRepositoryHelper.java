package com.lists.web.descriptor;

import com.lists.web.descriptorType.DescriptorType;
import com.lists.web.descriptorType.DescriptorTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by nick on 2/18/2018.
 */
@Component
public class DescriptorRepositoryHelper {

    private IDateDescriptorRepository dateDescriptorRepository;
    private IDoubleDescriptorRepository doubleDescriptorRepository;
    private IIntegerDescriptorRepository integerDescriptorRepository;
    private ILocationDescriptorRepository locationDescriptorRepository;
    private IReferenceThingDescriptorRepository referenceThingDescriptorRepository;
    private IResourceDescriptorRepository resourceDescriptorRepository;
    private IStringDescriptorRepository stringDescriptorRepository;

    Map<DescriptorTypes,CrudRepository<? extends Descriptor, Integer>> descriptorRepoMap = new HashMap<>();

    @Autowired
    public DescriptorRepositoryHelper(IDateDescriptorRepository dateDescriptorRepository, IDoubleDescriptorRepository doubleDescriptorRepository, IIntegerDescriptorRepository integerDescriptorRepository, ILocationDescriptorRepository locationDescriptorRepository, IReferenceThingDescriptorRepository referenceThingDescriptorRepository, IResourceDescriptorRepository resourceDescriptorRepository, IStringDescriptorRepository stringDescriptorRepository) {
        this.dateDescriptorRepository = dateDescriptorRepository;
        this.doubleDescriptorRepository = doubleDescriptorRepository;
        this.integerDescriptorRepository = integerDescriptorRepository;
        this.locationDescriptorRepository = locationDescriptorRepository;
        this.referenceThingDescriptorRepository = referenceThingDescriptorRepository;
        this.resourceDescriptorRepository = resourceDescriptorRepository;
        this.stringDescriptorRepository = stringDescriptorRepository;
        descriptorRepoMap.put(DescriptorTypes.DATE,dateDescriptorRepository);
        descriptorRepoMap.put(DescriptorTypes.DOUBLE,doubleDescriptorRepository);
        descriptorRepoMap.put(DescriptorTypes.INTEGER,integerDescriptorRepository);
        descriptorRepoMap.put(DescriptorTypes.LOCATION,locationDescriptorRepository);
        descriptorRepoMap.put(DescriptorTypes.REFERENCE_THING,referenceThingDescriptorRepository);
        descriptorRepoMap.put(DescriptorTypes.RESOURCE,resourceDescriptorRepository);
        descriptorRepoMap.put(DescriptorTypes.STRING,stringDescriptorRepository);
    }


    public Descriptor save(Descriptor descriptor) {
        if (descriptor instanceof DateDescriptor)
            return dateDescriptorRepository.save((DateDescriptor) descriptor);
        else if (descriptor instanceof DoubleDescriptor)
            return doubleDescriptorRepository.save((DoubleDescriptor) descriptor);
        else if (descriptor instanceof IntegerDescriptor)
            return integerDescriptorRepository.save((IntegerDescriptor) descriptor);
        else if (descriptor instanceof LocationDescriptor)
            return locationDescriptorRepository.save((LocationDescriptor) descriptor);
        else if (descriptor instanceof ReferenceThingDescriptor)
            return referenceThingDescriptorRepository.save((ReferenceThingDescriptor) descriptor);
        else if (descriptor instanceof ResourceDescriptor)
            return resourceDescriptorRepository.save((ResourceDescriptor) descriptor);
        else if (descriptor instanceof StringDescriptor)
            return stringDescriptorRepository.save((StringDescriptor) descriptor);
        return null;
    }

    public Iterable<Descriptor> save(Iterable<Descriptor> descriptors) {
        Set<DateDescriptor> dateDescriptors = new HashSet<>();
        Set<DoubleDescriptor> doubleDescriptorDescriptors = new HashSet<>();
        Set<IntegerDescriptor> integerDescriptorDescriptors = new HashSet<>();
        Set<LocationDescriptor> locationDescriptorDescriptors = new HashSet<>();
        Set<ReferenceThingDescriptor> referenceThingDescriptorDescriptors = new HashSet<>();
        Set<ResourceDescriptor> resourceDescriptorDescriptors = new HashSet<>();
        Set<StringDescriptor> stringDescriptorDescriptors = new HashSet<>();

        Set<Descriptor> returnDescriptors = new HashSet<>();

        for (Descriptor descriptor : descriptors){
            if (descriptor instanceof DateDescriptor)
                dateDescriptors.add((DateDescriptor) descriptor);
            else if (descriptor instanceof DoubleDescriptor)
                doubleDescriptorDescriptors.add((DoubleDescriptor) descriptor);
            else if (descriptor instanceof IntegerDescriptor)
                integerDescriptorDescriptors.add((IntegerDescriptor) descriptor);
            else if (descriptor instanceof LocationDescriptor)
                locationDescriptorDescriptors.add((LocationDescriptor) descriptor);
            else if (descriptor instanceof ReferenceThingDescriptor)
                referenceThingDescriptorDescriptors.add((ReferenceThingDescriptor) descriptor);
            else if (descriptor instanceof ResourceDescriptor)
                resourceDescriptorDescriptors.add((ResourceDescriptor) descriptor);
            else if (descriptor instanceof StringDescriptor)
                stringDescriptorDescriptors.add((StringDescriptor) descriptor);
        }
        dateDescriptorRepository.save(dateDescriptors).forEach(returnDescriptors::add);
        doubleDescriptorRepository.save(doubleDescriptorDescriptors).forEach(returnDescriptors::add);
        integerDescriptorRepository.save(integerDescriptorDescriptors).forEach(returnDescriptors::add);
        locationDescriptorRepository.save(locationDescriptorDescriptors).forEach(returnDescriptors::add);
        referenceThingDescriptorRepository.save(referenceThingDescriptorDescriptors).forEach(returnDescriptors::add);
        resourceDescriptorRepository.save(resourceDescriptorDescriptors).forEach(returnDescriptors::add);
        stringDescriptorRepository.save(stringDescriptorDescriptors).forEach(returnDescriptors::add);

        return returnDescriptors;
    }

    Descriptor findOne(String descriptorType, Integer id) {
        return descriptorRepoMap.get(DescriptorTypes.byString(descriptorType)).findOne(id);
    }

    public Map<DescriptorTypes, CrudRepository<? extends Descriptor, Integer>> getDescriptorRepoMap() {
        return descriptorRepoMap;
    }

    public IDateDescriptorRepository getDateDescriptorRepository() {
        return dateDescriptorRepository;
    }

    public IDoubleDescriptorRepository getDoubleDescriptorRepository() {
        return doubleDescriptorRepository;
    }

    public IIntegerDescriptorRepository getIntegerDescriptorRepository() {
        return integerDescriptorRepository;
    }

    public ILocationDescriptorRepository getLocationDescriptorRepository() {
        return locationDescriptorRepository;
    }

    public IReferenceThingDescriptorRepository getReferenceThingDescriptorRepository() {
        return referenceThingDescriptorRepository;
    }

    public IResourceDescriptorRepository getResourceDescriptorRepository() {
        return resourceDescriptorRepository;
    }

    public IStringDescriptorRepository getStringDescriptorRepository() {
        return stringDescriptorRepository;
    }
}
