package com.datajpa.demo.service;

import com.datajpa.demo.model.Author;
import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.ZipCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    public Author addAuthor(Author author);
    public List<Author> getAuthors();
    public Author getAuthor(Long id);
    public Author deleteAuthor(Long id);
    public Author editAuthor(Long id, Author author);
    public Author addZipCodeToAuthor(Long authorId, Long zipCodeId);
    public Author removeZipCodeFromAuthor(Long authorId);
    public Author addAuthorToBook(Long bookId, Long authorId);
    public Author removeAuthorFromBook(Long bookId, Long authorId);
}
