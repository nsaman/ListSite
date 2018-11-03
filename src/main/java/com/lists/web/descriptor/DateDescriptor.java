package com.lists.web.descriptor;

import com.lists.web.descriptorType.DescriptorTypes;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by nick on 10/28/2018.
 */

@Entity
public class DateDescriptor extends Descriptor {

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date value;

    public DateDescriptor() {
        super(DescriptorTypes.DATE);
    }

    @Override
    public String getReadableString() {
        return value.toString();
    }

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }
}
