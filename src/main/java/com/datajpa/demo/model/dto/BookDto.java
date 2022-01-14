package com.datajpa.demo.model.dto;

import com.datajpa.demo.model.Book;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private String name;
    private List<Long> authorId;
    private Long categoryId;

    public static BookDto from(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setName(book.getName());
        return bookDto;
    }
}
