package com.datajpa.demo.controller;

import com.datajpa.demo.model.Author;
import com.datajpa.demo.model.dto.request.AuthorDto;
import com.datajpa.demo.model.dto.response.AuthorResponseDto;
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
    public ResponseEntity<AuthorResponseDto> addAuthor(@RequestBody final AuthorDto authorDto) {
        AuthorResponseDto author = authorService.addAuthor(authorDto);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthor(@PathVariable final Long id) {
        AuthorResponseDto author = authorService.getAuthorById(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AuthorResponseDto>> getAuthors() {
        List<AuthorResponseDto> authors = authorService.getAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AuthorResponseDto> deleteAuthor(@PathVariable final Long id) {
        AuthorResponseDto author = authorService.deleteAuthor(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AuthorResponseDto> editAuthor(@PathVariable final Long id,
                                             @RequestBody final AuthorDto authorDto) {
        AuthorResponseDto author = authorService.editAuthor(id, authorDto);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/addZipCode/{zipcodeId}/toAuthor/{authorId}")
    public ResponseEntity<AuthorResponseDto> addZipCode(@PathVariable final Long zipcodeId,
                                             @PathVariable final Long authorId) {
        AuthorResponseDto author = authorService.addZipCodeToAuthor(authorId, zipcodeId);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/removeZipCode/{authorId}")
    public ResponseEntity<AuthorResponseDto> removeZipCode(@PathVariable final Long authorId) {
        AuthorResponseDto author = authorService.removeZipCodeFromAuthor(authorId);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }


}
