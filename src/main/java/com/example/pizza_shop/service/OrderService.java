package com.example.pizza_shop.service;

import com.example.pizza_shop.exception.InvalidFileException;
import com.example.pizza_shop.model.Order;
import com.example.pizza_shop.model.Topping;
import com.example.pizza_shop.model.ToppingBuilder;
import com.example.pizza_shop.service.comparator.ToppingComparator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

public class OrderService {

    public Order readOrder() throws IOException {
        String orderInputFile = System.getProperty("orderInputFile");
        Reader inputStream = new FileReader(orderInputFile);
        return readOrder(inputStream);
    }

    public Order readOrder(Reader reader) throws IOException {
        List<String> items = IOUtils.readLines(reader);
        if(CollectionUtils.isEmpty(items)) {
            throw new InvalidFileException("File is empty");
        }
        Order order = new Order(items.get(0));
        addToppings(items, order);
        if(CollectionUtils.isEmpty(order.getToppings())) {
            throw new InvalidFileException("No toppings present");
        }
        return order;
    }

    public void writeOrder(Order order) {

    }

    public void writeOrder(Order order, Writer writer) throws IOException {
        if(order == null || writer == null) {
            throw new IllegalArgumentException(MessageFormat.format("Arguments cannot be null; Order: [{0}], Writer: [{1}]", order, writer));
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(writer); PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.println(order.getHeader());
            List<Topping> toppings = order.getToppings();
            Collections.sort(toppings, new ToppingComparator());
            for (Topping topping : toppings) {
                printWriter.println(topping.toHumanReadableFormat());
            }
        }
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
