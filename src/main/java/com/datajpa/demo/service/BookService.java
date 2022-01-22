package com.datajpa.demo.service;

import com.datajpa.demo.model.dto.request.BookDto;
import com.datajpa.demo.model.dto.response.BookResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public BookResponseDto getBookById(Long id);
    public BookResponseDto addBook(BookDto bookDto);
    public List<BookResponseDto> getBooks();
    public BookResponseDto deleteBook(Long id);
    public BookResponseDto editBook(Long id, BookDto bookDto);
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId);
    public BookResponseDto removeCategoryFromBook(Long bookId, Long categoryId);
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId);
    public BookResponseDto removeAuthorFromBook(Long bookId, Long authorId);
}
