package com.example.pizza_shop.model;

import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ToppingTest {

    private Topping topping;

    @Test
    public void toHumanReadableFormat() throws Exception {
        topping = new Topping("bread ", new Date(1477491887L));

        //act
        String actual = topping.toHumanReadableFormat();

        assertThat(actual).containsPattern("bread 01/17/1970 ..:.. (AM|PM)");
    }

    @Test
    public void toHumanReadableFormatWhenDateIsNull() throws Exception {
        topping = new Topping("bread ", null);

        //act
        String actual = topping.toHumanReadableFormat();

        assertThat(actual).isEqualTo("bread ");
    }

}