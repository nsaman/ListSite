package com.lists.web.compares;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lists.web.AuditedEntity;
import com.lists.web.comparator.Comparator;
import com.lists.web.thing.Thing;

import javax.persistence.*;

/**
 * Created by nick on 9/25/2018.
 */

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"comparatorID", "thingID"})})
public class Compares extends AuditedEntity {

    public static float DEFAULT_SCORE = 1600;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer comparesID;

    @ManyToOne
    @JoinColumn(name="comparatorID",foreignKey=@ForeignKey(name="FK_compares_1"))
    private Comparator comparator;

    @JsonBackReference(value="thingCompares")
    @ManyToOne
    @JoinColumn(name="thingID",foreignKey=@ForeignKey(name="FK_compares_2"))
    private Thing thing;

    private float score;

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
}
