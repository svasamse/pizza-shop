package com.example.pizza_shop.service.comparator;

import com.example.pizza_shop.model.Topping;

import java.util.Comparator;

public class ToppingComparator implements Comparator<Topping>{
    public int compare(Topping lhs, Topping rhs) {
        if(lhs == null){
            return -1;
        }
        if(rhs == null){
            return 1;
        }
        int result = lhs.getName().compareTo(rhs.getName());
        if(result == 0){
            return lhs.getAddedOn().compareTo(rhs.getAddedOn());
        }
        return result;
    }
}
