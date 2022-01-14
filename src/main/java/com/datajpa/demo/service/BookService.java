package com.datajpa.demo.service;

import com.datajpa.demo.model.Author;
import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public Book getBook(Long id);
    public Book addBook(BookDto bookDto);
    public List<Book> getBooks();
    public Book deleteBook(Long id);
    public Book editBook(Long id, BookDto bookDto);
    public Book addCategoryToBook(Long bookId, Long categoryId);
    public Book removeCategoryFromBook(Long bookId, Long categoryId);
    public Book addAuthorToBook(Long bookId, Long authorId);
    public Book removeAuthorFromBook(Long bookId, Long authorId);
}
