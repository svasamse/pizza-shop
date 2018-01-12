package com.example.pizza_shop.service.comparator;

import com.example.pizza_shop.model.Pizza;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Comparator;

public class PizzaComparator implements Comparator<Pizza>{
    public int compare(Pizza lhs, Pizza rhs) {
        if(lhs == null){
            return -1;
        }
        if(rhs == null){
            return 1;
        }
        return new CompareToBuilder()
                .append(StringUtils.lowerCase(lhs.getName()), StringUtils.lowerCase(rhs.getName()))
                .append(lhs.getAddedOn(), rhs.getAddedOn())
                .toComparison();
    }
}
