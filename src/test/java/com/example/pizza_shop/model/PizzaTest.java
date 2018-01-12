package com.example.pizza_shop.model;

import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class PizzaTest {

    private Pizza pizza;

    @Test
    public void toHumanReadableFormat() throws Exception {
        pizza = new Pizza("bread ", new Date(1477491887L));

        //act
        String actual = pizza.toHumanReadableFormat();

        assertThat(actual).containsPattern("bread 01/17/1970 ..:.. (AM|PM)");
    }

    @Test
    public void toHumanReadableFormatWhenDateIsNull() throws Exception {
        pizza = new Pizza("bread ", null);

        //act
        String actual = pizza.toHumanReadableFormat();

        assertThat(actual).isEqualTo("bread ");
    }

}