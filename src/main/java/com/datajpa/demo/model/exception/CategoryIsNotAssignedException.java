package com.datajpa.demo.model.exception;

import java.text.MessageFormat;

public class CategoryIsNotAssignedException extends RuntimeException {
    public CategoryIsNotAssignedException(Long id, Long bookId) {
        super(MessageFormat.format(
                "Category with id: {0} is not assigned to book with id: {1}"
                , id, bookId));
    }
}
