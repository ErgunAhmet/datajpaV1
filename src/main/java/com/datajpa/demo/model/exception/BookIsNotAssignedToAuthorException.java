package com.datajpa.demo.model.exception;

import java.text.MessageFormat;

public class BookIsNotAssignedToAuthorException extends RuntimeException {
    public BookIsNotAssignedToAuthorException(Long authorId, Long bookId) {
        super(MessageFormat.format(
                "Book with id: {0} is not assigned to author with id:{1}"
                , bookId, authorId));
    }
}
