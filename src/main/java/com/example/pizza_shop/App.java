package com.example.pizza_shop;

import com.example.pizza_shop.model.Order;
import com.example.pizza_shop.service.OrderService;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String... args) throws IOException {
        if(StringUtils.isBlank(System.getProperty("orderInputFile"))) {
            printUsage("ERROR: Input file not specified.");
            return;
        }
        OrderService orderService = new OrderService();
        Order order = orderService.readOrder();
        String fileName = orderService.writeOrder(order);
        System.out.println("Finished generating the file.");
        System.out.println("Path: " + new File(fileName).getAbsoluteFile());
    }

    private static void printUsage(String reason) {
        System.err.println("--------------------------------------");
        System.err.println(reason);
        System.err.println("Usage: java -jar pizza-shop.jar -DorderInputFile=<path-to-order-file> [-DorderOutputFile=output.txt]");
        System.err.println("--------------------------------------");
    }

}
