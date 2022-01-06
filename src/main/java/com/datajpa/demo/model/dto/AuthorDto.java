package com.datajpa.demo.model.dto;

import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.ZipCode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class AuthorDto {
    private String name;
    private ZipCode zipCode;
    private List<Book> books = new ArrayList<>();

    public AuthorDto(String name, ZipCode zipCode, List<Book> books) {
        this.name = name;
        if (Objects.nonNull(zipCode)) {
            this.zipCode = zipCode;
        }
        if (Objects.nonNull(books)) {
            this.books = books;
        }
    }
}
