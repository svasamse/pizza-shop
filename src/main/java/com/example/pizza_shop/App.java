package com.example.pizza_shop;

import com.example.pizza_shop.model.Order;
import com.example.pizza_shop.service.OrderService;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

public class App {

    private static final String ORDER_INPUT_FILE = "orderInputFile";
    private static final String ORDER_OUTPUT_FILE = "orderOutputFile";
    private static final String DEFAULT_OUTPUT_FILE = "output.txt";

    public static void main(final String... args) throws IOException {
        final String orderInputFile = System.getProperty(ORDER_INPUT_FILE);
        if (StringUtils.isBlank(orderInputFile)) {
            printUsage();
            return;
        }
        final OrderService orderService = new OrderService();
        final Order order = orderService.readOrder(orderInputFile);
        final String orderOutputFile = System.getProperty(ORDER_OUTPUT_FILE, DEFAULT_OUTPUT_FILE);
        final String fileName = orderService.writeOrder(order, orderOutputFile);
        printSuccess(fileName);
    }

    private static void printUsage() {
        System.err.println("--------------------------------------");
        System.err.println("ERROR: Input file not specified.");
        System.err.printf("Usage: java -jar pizza-shop.jar -D%s=<path-to-order-file> [-D%s=%s]%n", ORDER_INPUT_FILE, ORDER_OUTPUT_FILE, DEFAULT_OUTPUT_FILE);
        System.err.println("--------------------------------------");
    }

    private static void printSuccess(final String fileName) {
        System.out.println("--------------------------------------");
        System.out.println("Finished generating the file.");
        System.out.println("Path: " + new File(fileName).getAbsoluteFile());
        System.out.println("--------------------------------------");
    }

}
