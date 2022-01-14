package com.datajpa.demo.model.exception;

public class BookNeedsCategoryException extends RuntimeException {
    public BookNeedsCategoryException() {
        super("you need to assign a category to book");
    }
}
