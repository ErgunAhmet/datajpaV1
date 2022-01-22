package com.datajpa.demo.controller;

import com.datajpa.demo.model.dto.request.BookDto;
import com.datajpa.demo.model.dto.response.BookResponseDto;
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
    public ResponseEntity<BookResponseDto> addBook(@RequestBody final BookDto bookDto) {
        BookResponseDto book = bookService.addBook(bookDto);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable final Long id) {
        BookResponseDto book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BookResponseDto>> getBooks() {
        List<BookResponseDto> books = bookService.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookResponseDto> deleteBook(@PathVariable final Long id) {
        BookResponseDto book = bookService.deleteBook(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BookResponseDto> editBook(@PathVariable final Long id,
                                             @RequestBody final BookDto bookDto) {
        BookResponseDto book = bookService.editBook(id,bookDto);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/addCategory/{categoryId}/toBook/{bookId}")
    public ResponseEntity<BookResponseDto> addCategory(@PathVariable final Long bookId,
                                            @PathVariable final Long categoryId) {
        BookResponseDto book = bookService.addCategoryToBook(bookId, categoryId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/removeCategory/{categoryId}/fromBook/{bookId}")
    public ResponseEntity<BookResponseDto> removeCategory(@PathVariable final Long bookId,
                                               @PathVariable final Long categoryId) {
        BookResponseDto book = bookService.removeCategoryFromBook(bookId, categoryId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/addBook/{bookId}/toAuthor/{authorId}")
    public ResponseEntity<BookResponseDto> addBook(@PathVariable final Long bookId,
                                          @PathVariable final Long authorId) {
        BookResponseDto book = bookService.addAuthorToBook(bookId, authorId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/removeBook/{bookId}/fromAuthor/{authorId}")
    public ResponseEntity<BookResponseDto> removeBook(@PathVariable final Long bookId,
                                             @PathVariable final Long authorId) {
        BookResponseDto book = bookService.removeAuthorFromBook(bookId, authorId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
  }
