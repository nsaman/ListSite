package com.lists.web.vote;

import com.lists.web.comparator.Comparator;
import com.lists.web.comparator.IComparatorRepository;
import com.lists.web.thing.IThingRepository;
import com.lists.web.thing.Thing;
import com.lists.web.user.IUserStubRepository;
import com.lists.web.user.UserStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by nick on 9/28/2018.
 */

@Controller
@RequestMapping(path="/vote")
public class VoteController {
    @Autowired
    private IVoteRepository voteRepository;
    @Autowired
    private IComparatorRepository comparatorRepository;
    @Autowired
    private IThingRepository thingRepository;
    @Autowired
    private IUserStubRepository userStubRepository;

    @RequestMapping(value = "/{voteID}", method = RequestMethod.GET)
    public String getVote(@PathVariable(value="voteID") int voteID, Model model) {

        Vote vote = voteRepository.findOne(voteID);

        model.addAttribute("vote", vote);

        return "vote";
    }

    @RequestMapping(params = {"comparatorID", "winnerThingID", "loserThingID", "userID"}, method = RequestMethod.POST)
    public String createVote(@RequestParam("comparatorID") Integer comparatorID, @RequestParam("winnerThingID") Integer winnerThingID,
                             @RequestParam("loserThingID") Integer loserThingID, @RequestParam("userID") String userID) {

        Comparator comparator = comparatorRepository.findOne(comparatorID);
        Thing winnerThing = thingRepository.findOne(winnerThingID);
        Thing loserThing = thingRepository.findOne(loserThingID);
        UserStub user = userStubRepository.findOne(userID);

        Vote vote = new Vote();

        vote.setComparator(comparator);
        vote.setWinnerThing(winnerThing);
        vote.setLoserThing(loserThing);
        vote.setUser(user);

        voteRepository.save(vote);

        return "redirect:/vote/" + vote.getVoteID();
    }
}
