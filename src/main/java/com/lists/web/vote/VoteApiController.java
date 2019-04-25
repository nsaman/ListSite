package com.lists.web.vote;

import com.lists.web.compares.Compares;
import com.lists.web.compares.IComparesRepository;
import com.lists.web.user.IUserStubRepository;
import com.lists.web.user.UserStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nick on 9/10/2019.
 */

@RestController
public class VoteApiController {
    @Autowired
    private IVoteRepository voteRepository;
    @Autowired
    private IComparesRepository comparesRepository;
    @Autowired
    private IUserStubRepository userStubRepository;

    @Transactional
    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(path="/api/vote", method = RequestMethod.POST, consumes={"application/json"})
    public void createVote(@Valid @RequestBody NewVoteRequest voteRequest, Principal principal) {

        UserStub user = userStubRepository.findOne(principal.getName());

        Vote vote1 = voteRepository.findByUserAndWinnerThingAndLoserThingAndComparatorAndCustomSet(user, voteRequest.getWinnerThing(), voteRequest.getLoserThing(), voteRequest.getComparator(), voteRequest.getCustomSet());
        Vote vote2 = voteRepository.findByUserAndWinnerThingAndLoserThingAndComparatorAndCustomSet(user, voteRequest.getLoserThing(), voteRequest.getWinnerThing(), voteRequest.getComparator(), voteRequest.getCustomSet());

        if(vote1!=null || vote2!=null)
            return;

        Vote vote = new Vote();
        vote.setComparator(voteRequest.getComparator());
        vote.setCustomSet(voteRequest.getCustomSet());
        vote.setLoserThing(voteRequest.getLoserThing());
        vote.setUser(user);
        vote.setWinnerThing(voteRequest.getWinnerThing());
        voteRepository.save(vote);

        Compares winnerCompares = comparesRepository.findByThingAndComparatorAndCustomSet(voteRequest.getWinnerThing(),voteRequest.getComparator(), voteRequest.getCustomSet());
        if (winnerCompares == null) {
            Compares compares = new Compares();
            compares.setThing(voteRequest.getWinnerThing());
            compares.setComparator(voteRequest.getComparator());
            compares.setCustomSet(voteRequest.getCustomSet());
            compares.setScore(Compares.DEFAULT_SCORE);
            winnerCompares = compares;
        }
        Compares loserCompares = comparesRepository.findByThingAndComparatorAndCustomSet(voteRequest.getLoserThing(),voteRequest.getComparator(), voteRequest.getCustomSet());
        if (loserCompares == null) {
            Compares compares = new Compares();
            compares.setThing(voteRequest.getLoserThing());
            compares.setComparator(voteRequest.getComparator());
            compares.setCustomSet(voteRequest.getCustomSet());
            compares.setScore(Compares.DEFAULT_SCORE);
            loserCompares = compares;
        }

        winnerCompares.setScore(winnerCompares.getScore() + 1);
        loserCompares.setScore(loserCompares.getScore() - 1);

        Set<Compares> compares = new HashSet<>();
        compares.add(winnerCompares);
        compares.add(loserCompares);

        comparesRepository.save(compares);


        if(voteRequest.getCustomSet() != null) {
            voteRequest.setCustomSet(null);
            createVote(voteRequest, principal);
        }
    }
}
