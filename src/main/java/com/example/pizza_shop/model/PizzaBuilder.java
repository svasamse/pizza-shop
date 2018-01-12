package com.example.pizza_shop.model;

import com.example.pizza_shop.exception.InvalidItemException;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PizzaBuilder {
    private String item;

    public PizzaBuilder(String item) {
        if(item == null) {
            throw new InvalidItemException(item);
        }
        this.item = item;
    }

    public Pizza createPizza() {
        Pattern pattern = Pattern.compile("(.+?)(\\d+)");
        Matcher matcher = pattern.matcher(item);
        boolean matches = matcher.matches();
        if(!matches) {
            throw new InvalidItemException(item);
        }
        Date timestamp = new Date(Long.valueOf(matcher.group(2)));
        return new Pizza(matcher.group(1), timestamp);
    }
}