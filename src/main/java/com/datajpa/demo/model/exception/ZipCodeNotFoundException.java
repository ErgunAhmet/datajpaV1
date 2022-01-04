package com.datajpa.demo.model.exception;

import java.text.MessageFormat;

public class ZipCodeNotFoundException extends RuntimeException {
    public ZipCodeNotFoundException(Long id) {
        super(MessageFormat.format("Could not find zipcode with id: {0}", id));
    }
}
