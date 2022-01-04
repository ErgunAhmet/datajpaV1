package com.datajpa.demo.model.exception;

import java.text.MessageFormat;

public class ZipCodeIsAlreadyAssignedException extends RuntimeException {
    public ZipCodeIsAlreadyAssignedException(Long zipCodeId, Long cityId) {
        super(MessageFormat
                .format("Zipcode with id: {0} is already assigned to city with id: {1}"
                        , zipCodeId, cityId));
    }
}
