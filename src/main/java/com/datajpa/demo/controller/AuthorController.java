package com.datajpa.demo.controller;

import com.datajpa.demo.model.Author;
import com.datajpa.demo.model.dto.AuthorDto;
import com.datajpa.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody final AuthorDto authorDto) {
        Author author = authorService.addAuthor(Author.from(authorDto));
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable final Long id) {
        Author author = authorService.getAuthor(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Author>> getAuthors() {
        List<Author> authors = authorService.getAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable final Long id) {
        Author author = authorService.deleteAuthor(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> editAuthor(@PathVariable final Long id,
                                             @RequestBody final AuthorDto authorDto) {
        Author author = authorService.editAuthor(id, Author.from(authorDto));
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/addZipCode/{zipcodeId}/toAuthor/{authorId}")
    public ResponseEntity<Author> addZipCode(@PathVariable final Long zipcodeId,
                                             @PathVariable final Long authorId) {
        Author author = authorService.addZipCodeToAuthor(authorId, zipcodeId);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/removeZipCode/{authorId}")
    public ResponseEntity<Author> removeZipCode(@PathVariable final Long authorId) {
        Author author = authorService.removeZipCodeFromAuthor(authorId);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/addBook/{bookId}/toAuthor/{authorId}")
    public ResponseEntity<Author> addBook(@PathVariable final Long bookId,
                                          @PathVariable final Long authorId) {
        Author author = authorService.addAuthorToBook(bookId, authorId);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/removeBook/{bookId}/fromAuthor/{authorId}")
    public ResponseEntity<Author> removeBook(@PathVariable final Long bookId,
                                          @PathVariable final Long authorId) {
        Author author = authorService.removeAuthorFromBook(bookId, authorId);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
