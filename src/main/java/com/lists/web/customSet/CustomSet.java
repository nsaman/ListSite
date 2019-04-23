package com.lists.web.customSet;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lists.web.AuditedEntity;
import com.lists.web.CustomSetThing.CustomSetThing;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nick on 4/7/2019.
 */

@Entity
public class CustomSet extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customSetID;
    private String title;

    @JsonManagedReference(value="customSetCustomSetThings")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "customSetID")
    private Set<CustomSetThing> customSetThings;

    public Integer getCustomSetID() {
        return customSetID;
    }

    public void setCustomSetID(Integer customSetID) {
        this.customSetID = customSetID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<CustomSetThing> getCustomSetThings() {
        return customSetThings;
    }

    public void setCustomSetThings(Set<CustomSetThing> customSetThings) {
        this.customSetThings = customSetThings;
    }

}
