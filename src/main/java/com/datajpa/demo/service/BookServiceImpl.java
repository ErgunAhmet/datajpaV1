package com.datajpa.demo.service;

import com.datajpa.demo.model.Author;
import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.Category;
import com.datajpa.demo.model.exception.*;
import com.datajpa.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException(id));
    }

    @Override
    public Book addBook(Book book) {
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
    public Book editBook(Long id, Book book) {
        Book bookToEdit = getBook(id);
        bookToEdit.setAuthors(book.getAuthors());
        bookToEdit.setCategory(book.getCategory());
        bookToEdit.setName(book.getName());
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
}
