package com.datajpa.demo.model.exception;

import java.text.MessageFormat;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super(MessageFormat.format("Book with id: {0} is not found", id));
    }
}
