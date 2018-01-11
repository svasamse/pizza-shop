package com.example.pizza_shop.service;

import com.example.pizza_shop.model.Order;
import com.example.pizza_shop.model.Topping;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class OrderSorterTest {

    private OrderSorter orderSorter;

    @Before
    public void setUp() throws Exception {
        System.setProperty("orderFile", "src/test/resources/sample_data_unnordered.txt");
        orderSorter = new OrderSorter();
    }

    @After
    public void tearDown() throws Exception {
        System.clearProperty("orderFile");
    }

    @Test
    public void readOrderFile() throws Exception {
        Order actual = orderSorter.readOrderFile();

        assertThat(actual.getHeader()).isEqualTo("Order\t\ttime");
        assertThat(actual.getToppings()).hasSize(9);

    }

}