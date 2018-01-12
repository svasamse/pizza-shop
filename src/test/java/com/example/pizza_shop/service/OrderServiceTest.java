package com.example.pizza_shop.service;

import com.example.pizza_shop.exception.InvalidFileException;
import com.example.pizza_shop.model.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.StringReader;

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
}