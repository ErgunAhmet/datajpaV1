package com.datajpa.demo.model.exception;

import java.text.MessageFormat;

public class AuthorNeedAnZipcodeException extends RuntimeException {
    public AuthorNeedAnZipcodeException() {
        super("You need to assign a zipcode");
    }
}
