package com.lists.web.descriptor;

import com.lists.web.thing.Thing;

import javax.persistence.*;

/**
 * Created by nick on 1/24/2018.
 */

@Entity
public class Descriptor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer descriptorID;

    private String title;
    private String valueType;
    private String valueData;
    private Boolean nullable;

    @ManyToOne
    @JoinColumn(name="describesThingID",foreignKey=@ForeignKey(name="FK_ParentThing"))
    private Thing describedThing;

    private String createUserID;
    private String changeUserID;
    private Boolean isAbstract;
    @ManyToOne
    @JoinColumn(name="parentThingID",foreignKey=@ForeignKey(name="FK_ParentThing"))
    private Thing parentThing;

}
