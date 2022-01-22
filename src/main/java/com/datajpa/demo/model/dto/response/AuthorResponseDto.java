package com.datajpa.demo.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AuthorResponseDto {
    private Long id;
    private String name;
    private List<String> books;
    private String zipcode;
}
