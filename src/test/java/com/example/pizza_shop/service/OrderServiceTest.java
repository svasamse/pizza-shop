package com.example.pizza_shop.service;

import com.example.pizza_shop.exception.InvalidFileException;
import com.example.pizza_shop.model.Order;
import com.example.pizza_shop.model.Pizza;
import com.example.pizza_shop.model.PizzaBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class OrderServiceTest {

    private static final String OUTPUT_FILE = "target/ordered.txt";
    private static final String INPUT_FILE = "src/test/resources/sample_data_unordered.txt";

    private OrderService orderService;

    @Before
    public void setUp() throws Exception {
        FileUtils.forceMkdirParent(new File(OUTPUT_FILE));
        orderService = new OrderService();
    }

    @After
    public void tearDown() throws Exception {
        FileUtils.deleteQuietly(new File(OUTPUT_FILE));
    }

    @Test
    public void readOrder() throws Exception {
        //act
        final Order actual = orderService.readOrder(INPUT_FILE);

        assertThat(actual.getHeader()).isEqualTo("Order\t\ttime");
        assertThat(actual.getPizzas()).hasSize(9);

    }

    @Test(expected = FileNotFoundException.class)
    public void readOrderWhenFileNotFound() throws Exception {
        //act
        orderService.readOrder("non/existing.file");
    }

    @Test(expected = InvalidFileException.class)
    public void readOrderWhenContentIsEmpty() throws Exception {
        final String content = "";

        //act
        orderService.readOrder(new StringReader(content));
    }

    @Test(expected = InvalidFileException.class)
    public void readOrderWhenOnlyHeaderIsPresent() throws Exception {
        final String content = "Order\t\ttime\n";

        //act
        orderService.readOrder(new StringReader(content));
    }

    @Test(expected = IllegalArgumentException.class)
    public void writeOrderWhenOrderIsNull() throws Exception {
        orderService.writeOrder(null, new StringWriter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void writeOrderWhenWriterIsNull() throws Exception {
        orderService.writeOrder(new Order("header"), (Writer) null);
    }

    @Test
    public void writeOrder() throws Exception {
        final Order order = new Order("header");
        final StringWriter writer = new StringWriter();

        final Pizza veggie = new PizzaBuilder("Veggie\t\t1474295087").createPizza();
        order.getPizzas().add(veggie);

        final Pizza meat = new PizzaBuilder("Meat\t\t1506176687").createPizza();
        order.getPizzas().add(meat);

        //act
        orderService.writeOrder(order, writer);

        final String[] lines = writer.toString().split(System.lineSeparator());

        assertThat(lines).hasSize(3);
        assertThat(lines[0]).isEqualTo("header");
        assertThat(lines[1]).containsPattern("Meat");
        assertThat(lines[2]).containsPattern("Veggie");
    }

    @Test
    public void writeOrderToFile() throws Exception {
        final Order order = new Order("header");

        final Pizza veggie = new PizzaBuilder("Veggie\t\t1474295087").createPizza();
        order.getPizzas().add(veggie);

        final Pizza meat = new PizzaBuilder("Meat\t\t1506176687").createPizza();
        order.getPizzas().add(meat);

        //act
        orderService.writeOrder(order, new File(OUTPUT_FILE));

        final List<String> lines = FileUtils.readLines(new File(OUTPUT_FILE), StandardCharsets.UTF_8);

        assertThat(lines).hasSize(3);
    }
}