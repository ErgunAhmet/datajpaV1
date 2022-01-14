package com.datajpa.demo.service;

import com.datajpa.demo.model.Author;
import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.Category;
import com.datajpa.demo.model.dto.BookDto;
import com.datajpa.demo.model.exception.*;
import com.datajpa.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, CategoryService categoryService, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException(id));
    }

    @Transactional
    @Override
    public Book addBook(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        if (bookDto.getAuthorId().isEmpty()) {
            throw new BookNeedsLeastOneAuthorException();
        } else {
            List<Author> authors = new ArrayList<>();
            for (Long authorId: bookDto.getAuthorId()) {
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            book.setAuthors(authors);
        }
        if (bookDto.getCategoryId() == null) {
            throw new BookNeedsCategoryException();
        }
        Category category = categoryService.getCategory(bookDto.getCategoryId());
        book.setCategory(category);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks() {
        return StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Book deleteBook(Long id) {
        Book book = getBook(id);
        bookRepository.delete(book);
        return book;
    }

    @Transactional
    @Override
    public Book editBook(Long id, BookDto bookDto) {
        Book bookToEdit = getBook(id);
        bookToEdit.setName(bookDto.getName());
        if (!bookDto.getAuthorId().isEmpty()) {
            List<Author> authors = new ArrayList<>();
            for (Long authorId: bookDto.getAuthorId()) {
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            bookToEdit.setAuthors(authors);
        }
        if (bookDto.getCategoryId() != null) {
            Category category = categoryService.getCategory(bookDto.getCategoryId());
            bookToEdit.setCategory(category);
        }
        return bookToEdit;
    }



    @Transactional
    @Override
    public Book addCategoryToBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (Objects.nonNull(book.getCategory())) {
            throw new CategoryAlreadyAssignedException(book.getCategory().getId(), bookId);
        }
        book.setCategory(category);
        category.addBook(book);
        return book;
    }

    @Transactional
    @Override
    public Book removeCategoryFromBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (!Objects.nonNull(book.getCategory())) {
            throw new CategoryIsNotAssignedException(book.getCategory().getId(), bookId);
        }
        book.setCategory(null);
        category.removeBook(book);
        return book;
    }
    @Transactional
    @Override
    public Book addAuthorToBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if (author.getBooks().contains(book)) {
            throw new BookAlreadyAssignedToAuthorException(authorId, bookId);
        }
        book.addAuthor(author);
        author.addBook(book);
        return book;
    }

    @Transactional
    @Override
    public Book removeAuthorFromBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if (!(author.getBooks().contains(book))) {
            throw new BookIsNotAssignedToAuthorException(authorId, bookId);
        }
        author.removeBook(book);
        book.removeAuthor(author);
        return book;
    }
}
