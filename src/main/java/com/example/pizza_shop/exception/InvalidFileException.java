package com.example.pizza_shop.exception;

import java.text.MessageFormat;

public class InvalidFileException extends RuntimeException {

    public InvalidFileException(String message) {
        super(MessageFormat.format("The file is invalid; Reason: {0}", message));
    }
}
