package com.example.pizza_shop.exception;

public class InvalidItemException extends RuntimeException {

    public InvalidItemException(final String item) {
        super("The item [" + item + "] is invalid.");
    }
}
