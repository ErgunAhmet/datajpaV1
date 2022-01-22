package com.datajpa.demo.model.dto.response;

import lombok.Data;

import java.util.List;
@Data
public class BookResponseDto {
    private Long id;
    private String name;
    private List<String> authors;
    private String category;
}
