package com.example.pizza_shop.service;

import com.example.pizza_shop.exception.InvalidFileException;
import com.example.pizza_shop.model.Order;
import com.example.pizza_shop.model.Pizza;
import com.example.pizza_shop.model.PizzaBuilder;
import com.example.pizza_shop.service.comparator.PizzaComparator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

public class OrderService {

    public Order readOrder(final String orderInputFile) throws IOException {
        final Reader inputStream = new FileReader(orderInputFile);
        return readOrder(inputStream);
    }

    public Order readOrder(final Reader reader) throws IOException {
        final List<String> items = IOUtils.readLines(reader);
        if (CollectionUtils.isEmpty(items)) {
            throw new InvalidFileException("File is empty");
        }
        final Order order = new Order(items.get(0));
        addPizzas(items, order);
        if (CollectionUtils.isEmpty(order.getPizzas())) {
            throw new InvalidFileException("No pizzas present");
        }
        return order;
    }

    public void writeOrder(final Order order, final File orderOutputFile) throws IOException {
        writeOrder(order, new FileWriter(orderOutputFile));
    }

    public void writeOrder(final Order order, final Writer writer) throws IOException {
        if (order == null || writer == null) {
            throw new IllegalArgumentException(MessageFormat.format("Arguments cannot be null; Order: [{0}], Writer: [{1}]", order, writer));
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(writer); PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.println(order.getHeader());
            final List<Pizza> pizzas = order.getPizzas();
            Collections.sort(pizzas, new PizzaComparator());
            for (final Pizza pizza : pizzas) {
                printWriter.println(pizza.toHumanReadableFormat());
            }
        }
    }

    private void addPizzas(final List<String> items, final Order order) {
        for (int i = 1; i < items.size(); i++) {
            final String line = items.get(i);
            if (StringUtils.isBlank(line)) {
                continue;
            }
            final PizzaBuilder pizzaBuilder = new PizzaBuilder(line);
            final Pizza pizza = pizzaBuilder.createPizza();
            order.getPizzas().add(pizza);
        }
    }

}
