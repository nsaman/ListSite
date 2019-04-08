package com.lists.web.CustomSetThing;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lists.web.AuditedEntity;
import com.lists.web.customSet.CustomSet;
import com.lists.web.thing.Thing;

import javax.persistence.*;

/**
 * Created by nick on 4/7/2019.
 */

@Entity
public class CustomSetThing extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customSetThingID;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="customSetID",foreignKey=@ForeignKey(name="FK_customSetThing_1"))
    private CustomSet customSet;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="thingID",foreignKey=@ForeignKey(name="FK_customSetThing_2"))
    private Thing thing;

    public CustomSet getCustomSet() {
        return customSet;
    }

    public void setCustomSet(CustomSet customSet) {
        this.customSet = customSet;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }


}
