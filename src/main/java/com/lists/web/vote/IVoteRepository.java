package com.lists.web.vote;

import com.lists.web.comparator.Comparator;
import com.lists.web.customSet.CustomSet;
import com.lists.web.thing.Thing;
import com.lists.web.user.UserStub;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nick on 9/28/2018.
 */
public interface IVoteRepository extends CrudRepository<Vote, Integer> {
    Vote findByUserAndWinnerThingAndLoserThingAndComparatorAndCustomSet(UserStub userStub, Thing winnerThing, Thing loserThing, Comparator comparator, CustomSet customSet);
}
