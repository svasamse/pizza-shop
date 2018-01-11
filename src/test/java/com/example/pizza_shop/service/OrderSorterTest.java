package com.example.pizza_shop.service;

import com.example.pizza_shop.exception.InvalidFileException;
import com.example.pizza_shop.model.Order;
import com.example.pizza_shop.model.Topping;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
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
        //act
        Order actual = orderSorter.readOrderFile();

        assertThat(actual.getHeader()).isEqualTo("Order\t\ttime");
        assertThat(actual.getToppings()).hasSize(9);

    }

    @Test(expected = FileNotFoundException.class)
    public void readOrderFileWhenFileNotFound() throws Exception {
        System.setProperty("orderFile", "non/existing.file");

        //act
        orderSorter.readOrderFile();
    }

    @Test(expected = InvalidFileException.class)
    public void readOrderFileWhenFileIsEmpty() throws Exception {
        System.setProperty("orderFile", "src/test/resources/empty_file.txt");

        //act
        orderSorter.readOrderFile();
    }

    @Test(expected = InvalidFileException.class)
    public void readOrderFileWhenOnlyHeaderIsPresent() throws Exception {
        System.setProperty("orderFile", "src/test/resources/only_header.txt");

        //act
        orderSorter.readOrderFile();
    }

}