package com.datajpa.demo.model.exception;

import java.text.MessageFormat;

public class ZipCodeIsAlreadyAssignedToAuthorException extends RuntimeException {
    public ZipCodeIsAlreadyAssignedToAuthorException(Long authorId, Long zipCodeId) {
        super(MessageFormat.format(
                "Zipcode with id: {0} is already assigned to author with id: {1}"
                , zipCodeId, authorId));
    }
}
