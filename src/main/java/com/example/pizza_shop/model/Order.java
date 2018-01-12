package com.example.pizza_shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final String header;
    private List<Pizza> pizzas = new ArrayList<Pizza>(0);

    public Order(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
