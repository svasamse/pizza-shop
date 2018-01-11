package com.example.pizza_shop.model;

import com.example.pizza_shop.exception.InvalidItemException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ToppingBuilderTest {

    private ToppingBuilder toppingBuilder;

    @Test(expected = InvalidItemException.class)
    public void itemCannotBeNull() throws Exception {
        toppingBuilder = new ToppingBuilder(null);
    }

    @Test
    public void createToppingWhenLineItemIsValid() throws Exception {
        String item = "p1zza\t\t1477491887";

        toppingBuilder = new ToppingBuilder(item);

        //act
        Topping topping = toppingBuilder.createTopping();

        assertThat(topping.getName()).isEqualTo("p1zza\t\t");
        assertThat(topping.getAddedOn().getTime()).isEqualTo(1477491887L);
    }

    @Test(expected = InvalidItemException.class)
    public void createToppingWhenLineItemIsNotValid() throws Exception {
        String item = "p1zza  1477491887invalidtextaftertimestamp";

        toppingBuilder = new ToppingBuilder(item);

        //act
        toppingBuilder.createTopping();

    }

}