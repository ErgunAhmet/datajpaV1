package com.datajpa.demo.model.exception;

import java.text.MessageFormat;

public class CategoryAlreadyAssignedException extends RuntimeException {
    public CategoryAlreadyAssignedException(Long id, Long bookId) {
        super(MessageFormat.format(
                "Book with id: {0} was already assigned to category with id: {1}"
                , bookId, id));
    }
}
