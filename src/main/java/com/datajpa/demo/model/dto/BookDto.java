package com.datajpa.demo.model.dto;

import com.datajpa.demo.model.Book;
import lombok.Data;

@Data
public class BookDto {
    private String name;

    public static BookDto from(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setName(book.getName());
        return bookDto;
    }
}
