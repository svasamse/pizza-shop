package com.example.pizza_shop.service;

import com.example.pizza_shop.exception.InvalidFileException;
import com.example.pizza_shop.model.Order;
import com.example.pizza_shop.model.Topping;
import com.example.pizza_shop.model.ToppingBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;

public class OrderSorter {

    public Order readOrder() throws IOException {
        String orderFile = System.getProperty("orderFile");
        Reader inputStream = new FileReader(orderFile);
        return readOrder(inputStream);
    }

    public Order readOrder(Reader inputStream) throws IOException {
        List<String> items = IOUtils.readLines(inputStream);
        if(items.isEmpty()) {
            throw new InvalidFileException("File is empty");
        }
        Order order = new Order(items.get(0));
        addToppings(items, order);
        if(order.getToppings().isEmpty()) {
            throw new InvalidFileException("No toppings present");
        }
        return order;
    }

    private void addToppings(List<String> items, Order order) {
        for (int i = 1; i < items.size(); i++) {
            String line = items.get(i);
            if(StringUtils.isBlank(line)) {
                continue;
            }
            ToppingBuilder toppingBuilder = new ToppingBuilder(line);
            Topping topping = toppingBuilder.createTopping();
            order.getToppings().add(topping);
        }
    }

}
