package com.example.pizza_shop.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pizza {

    private static final String DATE_PATTERN = "MM/dd/yyyy hh:mm a";

    private final String name;
    private final Date orderedOn;

    public Pizza(final String name, final Date orderedOn) {
        this.name = name;
        this.orderedOn = orderedOn;
    }

    public String getName() {
        return name;
    }

    public Date getOrderedOn() {
        return orderedOn;
    }

    public String toHumanReadableFormat() {
        final StringBuilder builder = new StringBuilder();
        builder.append(name);
        if (orderedOn != null) {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
            builder.append(dateFormat.format(orderedOn));
        }
        return builder.toString();
    }
}
