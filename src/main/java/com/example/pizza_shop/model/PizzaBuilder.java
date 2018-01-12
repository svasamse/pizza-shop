package com.example.pizza_shop.model;

import com.example.pizza_shop.exception.InvalidItemException;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PizzaBuilder {
    private final String item;

    public PizzaBuilder(final String item) {
        if(item == null) {
            throw new InvalidItemException(null);
        }
        this.item = item;
    }

    public Pizza createPizza() {
        final Pattern pattern = Pattern.compile("(.+?)(\\d+)");
        final Matcher matcher = pattern.matcher(item);
        final boolean matches = matcher.matches();
        if(!matches) {
            throw new InvalidItemException(item);
        }
        final Date timestamp = new Date(Long.valueOf(matcher.group(2)));
        return new Pizza(matcher.group(1), timestamp);
    }
}