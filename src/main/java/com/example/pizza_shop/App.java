package com.example.pizza_shop;

import com.example.pizza_shop.model.Order;
import com.example.pizza_shop.service.OrderService;

import java.io.IOException;

public class App {

    public static void main(String... args) throws IOException {
        OrderService orderService = new OrderService();
        Order order = orderService.readOrder();
        orderService.writeOrder(order);
    }

}
