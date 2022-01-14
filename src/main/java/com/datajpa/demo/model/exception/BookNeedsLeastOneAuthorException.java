package com.datajpa.demo.model.exception;

public class BookNeedsLeastOneAuthorException extends RuntimeException {
    public BookNeedsLeastOneAuthorException() {
        super("A book needs an author");
    }
}
