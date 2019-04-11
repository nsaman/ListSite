package com.lists.web.vote;

import com.lists.web.comparator.Comparator;
import com.lists.web.customSet.CustomSet;
import com.lists.web.thing.Thing;

import javax.validation.constraints.NotNull;

public class NewVoteRequest {

    @NotNull
    private Comparator comparator;
    @NotNull
    private Thing winnerThing;
    @NotNull
    private Thing loserThing;

    private CustomSet customSet;

    public Comparator getComparator() {
        return comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    public Thing getWinnerThing() {
        return winnerThing;
    }

    public void setWinnerThing(Thing winnerThing) {
        this.winnerThing = winnerThing;
    }

    public Thing getLoserThing() {
        return loserThing;
    }

    public void setLoserThing(Thing loserThing) {
        this.loserThing = loserThing;
    }

    public CustomSet getCustomSet() {
        return customSet;
    }

    public void setCustomSet(CustomSet customSet) {
        this.customSet = customSet;
    }
}
