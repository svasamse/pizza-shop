package com.example.pizza_shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final String header;
    private List<Topping> toppings = new ArrayList<Topping>(0);

    public Order(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }
}
