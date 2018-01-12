package com.example.pizza_shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final String header;
    private final List<Pizza> pizzas = new ArrayList<>(0);

    public Order(final String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

}
