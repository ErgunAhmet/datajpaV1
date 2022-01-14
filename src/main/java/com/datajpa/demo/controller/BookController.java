package com.datajpa.demo.controller;

import com.datajpa.demo.model.Author;
import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.Category;
import com.datajpa.demo.model.dto.AuthorDto;
import com.datajpa.demo.model.dto.BookDto;
import com.datajpa.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody final BookDto bookDto) {
        Book book = bookService.addBook(Book.from(bookDto));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Book> getBook(@PathVariable final Long id) {
        Book book = bookService.getBook(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable final Long id) {
        Book book = bookService.deleteBook(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable final Long id,
                                             @RequestBody final BookDto bookDto) {
        Book book = bookService.editBook(id, Book.from(bookDto));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/addCategory/{categoryId}/toBook/{bookId}")
    public ResponseEntity<Book> addCategory(@PathVariable final Long bookId,
                                            @PathVariable final Long categoryId) {
        Book book = bookService.addCategoryToBook(bookId, categoryId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/removeCategory/{categoryId}/fromBook/{bookId}")
    public ResponseEntity<Book> removeCategory(@PathVariable final Long bookId,
                                               @PathVariable final Long categoryId) {
        Book book = bookService.removeCategoryFromBook(bookId, categoryId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
  }
