package com.lists.web.compares;

import com.lists.web.comparator.Comparator;
import com.lists.web.descriptorType.DescriptorType;
import com.lists.web.thing.Thing;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nick on 9/25/2018.
 */

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"comparatorID", "thingID"})})
public class Compares {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer comparesID;

    @ManyToOne
    @JoinColumn(name="comparatorID",foreignKey=@ForeignKey(name="FK_compares_1"))
    private Comparator comparator;

    @ManyToOne
    @JoinColumn(name="thingID",foreignKey=@ForeignKey(name="FK_compares_2"))
    private Thing thing;

    private float score;
    private String createUserID;
    private String changeUserID;

    public Integer getComparesID() {
        return comparesID;
    }

    public void setComparesID(Integer comparesID) {
        this.comparesID = comparesID;
    }

    public Comparator getComparator() {
        return comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getCreateUserID() {
        return createUserID;
    }

    public void setCreateUserID(String createUserID) {
        this.createUserID = createUserID;
    }

    public String getChangeUserID() {
        return changeUserID;
    }

    public void setChangeUserID(String changeUserID) {
        this.changeUserID = changeUserID;
    }
}
