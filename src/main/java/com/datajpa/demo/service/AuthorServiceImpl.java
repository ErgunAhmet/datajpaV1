package com.datajpa.demo.service;

import com.datajpa.demo.model.Author;
import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.ZipCode;
import com.datajpa.demo.model.exception.*;
import com.datajpa.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final ZipcodeService zipcodeService;
    private final BookService bookService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ZipcodeService zipcodeService, BookService bookService) {
        this.authorRepository = authorRepository;
        this.zipcodeService = zipcodeService;
        this.bookService = bookService;
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAuthors() {
        return StreamSupport
                .stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException(id));
    }

    @Override
    public Author deleteAuthor(Long id) {
        Author author = getAuthor(id);
        authorRepository.delete(author);
        return author;
    }

    @Transactional
    @Override
    public Author editAuthor(Long id, Author author) {
        Author authorToEdit = getAuthor(id);
        authorToEdit.setName(author.getName());
        return authorToEdit;
    }

    @Transactional
    @Override
    public Author addZipCodeToAuthor(Long authorId, Long zipCodeId) {
        Author author = getAuthor(authorId);
        ZipCode zipCode = zipcodeService.getZipCode(zipCodeId);
        if(Objects.nonNull(author.getZipCode())){
            throw new ZipCodeIsAlreadyAssignedToAuthorException(authorId,
                    author.getZipCode().getId());
        }
        author.setZipCode(zipCode);
        return author;
    }

    @Transactional
    @Override
    public Author removeZipCodeFromAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        author.setZipCode(null);
        return author;
    }

    @Transactional
    @Override
    public Author addAuthorToBook(Long bookId, Long authorId) {
        Book book = bookService.getBook(bookId);
        Author author = getAuthor(authorId);
        if (author.getBooks().contains(book)) {
            throw new BookAlreadyAssignedToAuthorException(authorId, bookId);
        }
        book.addAuthor(author);
        author.addBook(book);
        return author;
    }

    @Transactional
    @Override
    public Author removeAuthorFromBook(Long bookId, Long authorId) {
        Book book = bookService.getBook(bookId);
        Author author = getAuthor(authorId);
        if (!(author.getBooks().contains(book))) {
            throw new BookIsNotAssignedToAuthorException(authorId, bookId);
        }
        author.removeBook(book);
        book.removeAuthor(author);
        return author;
    }
}
