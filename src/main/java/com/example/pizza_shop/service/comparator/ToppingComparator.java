package com.example.pizza_shop.service.comparator;

import com.example.pizza_shop.model.Topping;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Comparator;

public class ToppingComparator implements Comparator<Topping>{
    public int compare(Topping lhs, Topping rhs) {
        if(lhs == null){
            return -1;
        }
        if(rhs == null){
            return 1;
        }
        return new CompareToBuilder()
                .append(lhs.getName(), rhs.getName())
                .append(lhs.getAddedOn(), rhs.getAddedOn())
                .toComparison();
    }
}
