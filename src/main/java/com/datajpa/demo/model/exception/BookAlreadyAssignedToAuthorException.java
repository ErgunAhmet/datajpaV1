package com.datajpa.demo.model.exception;

import java.text.MessageFormat;

public class BookAlreadyAssignedToAuthorException extends RuntimeException {
    public BookAlreadyAssignedToAuthorException(Long authorId, Long bookId) {
        super(MessageFormat.format(
                "Book with id: {0} is already assigned to author with id: {1}"
                , bookId, authorId));
    }
}
