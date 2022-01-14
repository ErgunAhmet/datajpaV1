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


    public AuthorDto(String name) {
        this.name = name;

    }
}
