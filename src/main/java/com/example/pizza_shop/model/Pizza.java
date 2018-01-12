package com.example.pizza_shop.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pizza {

    private static final String DATE_PATTERN = "MM/dd/yyyy hh:mm a";

    private final String name;
    private final Date addedOn;

    public Pizza(String name, Date addedOn) {
        this.name = name;
        this.addedOn = addedOn;
    }

    public String getName() {
        return name;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public String toHumanReadableFormat() {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        if (addedOn != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
            builder.append(dateFormat.format(addedOn));
        }
        return builder.toString();
    }
}
