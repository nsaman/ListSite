package com.lists.web.vote;

import com.lists.web.comparator.Comparator;
import com.lists.web.comparator.IComparatorRepository;
import com.lists.web.thing.IThingRepository;
import com.lists.web.thing.Thing;
import com.lists.web.user.IUserStubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @ModelAttribute("comparatorList")
    public Iterable<Comparator> comparatorList() {
        return comparatorRepository.findAll();
    }

    @ModelAttribute("thingList")
    public Iterable<Thing> thingList() {
        return thingRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getCreateVote() {
        return new ModelAndView("createVote", "vote", new Vote());
    }

    @RequestMapping(value = "/{voteID}", method = RequestMethod.GET)
    public String getVote(@PathVariable(value="voteID") int voteID, Model model) {

        Vote vote = voteRepository.findOne(voteID);

        model.addAttribute("vote", vote);

        return "vote";
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(method = RequestMethod.POST)
    public String createVote(@ModelAttribute("vote") Vote vote) {
        vote.setUser(userStubRepository.findOne(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
        voteRepository.save(vote);

        return "redirect:/vote/" + vote.getVoteID();
    }

}
