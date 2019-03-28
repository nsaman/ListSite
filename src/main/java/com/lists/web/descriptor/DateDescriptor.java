package com.lists.web.descriptor;

import com.lists.web.descriptorType.DescriptorTypes;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nick on 10/28/2018.
 */

@Entity
public class DateDescriptor extends Descriptor {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern=DATE_FORMAT)
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

    public static Date formatDate(String string) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(string);
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Could not convert to " + DATE_FORMAT + " from value=" + string, ex);
        }
    }
}
