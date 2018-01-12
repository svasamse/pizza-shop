package com.example.pizza_shop.model;

import com.example.pizza_shop.exception.InvalidItemException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PizzaBuilderTest {

    private PizzaBuilder pizzaBuilder;

    @Test(expected = InvalidItemException.class)
    public void itemCannotBeNull() throws Exception {
        pizzaBuilder = new PizzaBuilder(null);
    }

    @Test
    public void createPizzaWhenLineItemIsValid() throws Exception {
        String item = "p1zza\t\t1477491887";

        pizzaBuilder = new PizzaBuilder(item);

        //act
        Pizza pizza = pizzaBuilder.createPizza();

        assertThat(pizza.getName()).isEqualTo("p1zza\t\t");
        assertThat(pizza.getAddedOn().getTime()).isEqualTo(1477491887L);
    }

    @Test(expected = InvalidItemException.class)
    public void createPizzaWhenLineItemIsNotValid() throws Exception {
        String item = "p1zza  1477491887invalidtextaftertimestamp";

        pizzaBuilder = new PizzaBuilder(item);

        //act
        pizzaBuilder.createPizza();

    }

}