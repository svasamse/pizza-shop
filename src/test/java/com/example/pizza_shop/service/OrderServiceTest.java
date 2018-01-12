package com.example.pizza_shop.service;

import com.example.pizza_shop.exception.InvalidFileException;
import com.example.pizza_shop.model.Order;
import com.example.pizza_shop.model.Topping;
import com.example.pizza_shop.model.ToppingBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;


public class OrderServiceTest {

    private OrderService orderService;

    @Before
    public void setUp() throws Exception {
        System.setProperty("orderFile", "src/test/resources/sample_data_unnordered.txt");
        orderService = new OrderService();
    }

    @After
    public void tearDown() throws Exception {
        System.clearProperty("orderFile");
    }

    @Test
    public void readOrder() throws Exception {
        //act
        Order actual = orderService.readOrder();

        assertThat(actual.getHeader()).isEqualTo("Order\t\ttime");
        assertThat(actual.getToppings()).hasSize(9);

    }

    @Test(expected = FileNotFoundException.class)
    public void readOrderWhenFileNotFound() throws Exception {
        System.setProperty("orderFile", "non/existing.file");

        //act
        orderService.readOrder();
    }

    @Test(expected = InvalidFileException.class)
    public void readOrderWhenContentIsEmpty() throws Exception {
        String content = "";

        //act
        orderService.readOrder(new StringReader(content));
    }

    @Test(expected = InvalidFileException.class)
    public void readOrderWhenOnlyHeaderIsPresent() throws Exception {
        String content = "Order\t\ttime\n";

        //act
        orderService.readOrder(new StringReader(content));
    }

    @Test(expected = IllegalArgumentException.class)
    public void writeOrderWhenOrderIsNull() throws Exception {
        orderService.writeOrder(null, new StringWriter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void writeOrderWhenWriterIsNull() throws Exception {
        orderService.writeOrder(new Order("header"), null);
    }

    @Test
    public void writeOrder() throws Exception {
        Order order = new Order("header");
        StringWriter writer = new StringWriter();

        Topping veggie = new ToppingBuilder("Veggie\t\t1474295087").createTopping();
        order.getToppings().add(veggie);

        Topping meat = new ToppingBuilder("Meat\t\t1506176687").createTopping();
        order.getToppings().add(meat);

        //act
        orderService.writeOrder(order, writer);

        String[] lines = writer.toString().split(System.lineSeparator());

        assertThat(lines).hasSize(3);
        assertThat(lines[0]).isEqualTo("header");
        assertThat(lines[1]).containsPattern("Meat");
        assertThat(lines[2]).containsPattern("Veggie");
    }
}