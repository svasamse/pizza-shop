package com.example.pizza_shop.model;

import java.util.Date;

public class Topping {

    private final String name;
    private final Date addedOn;

    public Topping(String name, Date addedOn) {
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
            builder.append(addedOn);
        }
        return builder.toString();
    }
}
