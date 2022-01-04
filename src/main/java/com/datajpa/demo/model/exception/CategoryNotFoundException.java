package com.datajpa.demo.model.exception;

import java.text.MessageFormat;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long categoryId) {
        super(MessageFormat.format("Category with id: {0} is not found", categoryId));
    }
}
