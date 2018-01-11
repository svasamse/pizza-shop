package com.example.pizza_shop.exception;

import java.io.File;
import java.text.MessageFormat;

public class InvalidFileException extends RuntimeException {

    public InvalidFileException(File file, String message) {
        super(MessageFormat.format("The file [{0}] is invalid; Reason: {1}", file.getAbsolutePath(), message));
    }
}
