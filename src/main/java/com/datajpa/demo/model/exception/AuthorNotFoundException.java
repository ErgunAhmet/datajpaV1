package com.datajpa.demo.model.exception;

import java.text.MessageFormat;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(final Long id) {
        super(MessageFormat.format("Author with id: {0} not found", id));
    }
}
