package com.lists.web.vote;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lists.web.AuditedEntity;
import com.lists.web.comparator.Comparator;
import com.lists.web.thing.Thing;
import com.lists.web.user.UserStub;

import javax.persistence.*;

/**
 * Created by nick on 9/28/2018.
 */

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"comparatorID", "winnerThingID", "loserThingID", "userID"})})
public class Vote extends AuditedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer voteID;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="comparatorID",foreignKey=@ForeignKey(name="FK_vote_1"))
    private Comparator comparator;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="winnerThingID",foreignKey=@ForeignKey(name="FK_vote_2"))
    private Thing winnerThing;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="loserThingID",foreignKey=@ForeignKey(name="FK_vote_3"))
    private Thing loserThing;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="userID",foreignKey=@ForeignKey(name="FK_vote_4"))
    private UserStub user;

    public Integer getVoteID() {
        return voteID;
    }

    public void setVoteID(Integer voteID) {
        this.voteID = voteID;
    }

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

    public UserStub getUser() {
        return user;
    }

    public void setUser(UserStub user) {
        this.user = user;
    }
}
