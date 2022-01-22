package com.datajpa.demo.model.dto.request;

import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.ZipCode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class AuthorDto {
    private String name;
    private Long zipCodeId;

    public AuthorDto(String name, Long zipCodeId) {
        this.name = name;
        this.zipCodeId = zipCodeId;
    }
}
