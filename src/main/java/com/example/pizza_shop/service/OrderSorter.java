package com.example.pizza_shop.service;

import com.example.pizza_shop.exception.InvalidFileException;
import com.example.pizza_shop.model.Order;
import com.example.pizza_shop.model.Topping;
import com.example.pizza_shop.model.ToppingBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OrderSorter {

    private static final String UTF_8 = "UTF-8";

    public Order readOrderFile() throws IOException {
        String orderFile = System.getProperty("orderFile");
        List<String> items = FileUtils.readLines(new File(orderFile), UTF_8);
        if(items.size() == 0) {
            throw new InvalidFileException(new File(orderFile), "File is empty");
        }
        Order order = new Order(items.get(0));
        for (int i = 1; i < items.size(); i++) {
            String line = items.get(i);
            if(StringUtils.isBlank(line)) {
                continue;
            }
            ToppingBuilder toppingBuilder = new ToppingBuilder(line);
            Topping topping = toppingBuilder.createTopping();
            order.getToppings().add(topping);
        }
        if(order.getToppings().isEmpty()) {
            throw new InvalidFileException(new File(orderFile), "No toppings present");
        }
        return order;
    }

}
