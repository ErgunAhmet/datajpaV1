package com.datajpa.demo.service;

import com.datajpa.demo.mapper.mapper;
import com.datajpa.demo.model.Author;
import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.Category;
import com.datajpa.demo.model.dto.request.BookDto;
import com.datajpa.demo.model.dto.response.BookResponseDto;
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
    public BookResponseDto getBookById(Long id) {
        Book book = getBook(id);
        return mapper.bookToBookResponseDto(book);
    }

    public Book getBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException(id));
        return book;
    }

    @Transactional
    @Override
    public BookResponseDto addBook(BookDto bookDto) {
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
        Book book1 = bookRepository.save(book);

        BookResponseDto bookResponseDto = mapper.bookToBookResponseDto(book1);
        return bookResponseDto;
    }

    @Override
    public List<BookResponseDto> getBooks() {
        List<Book> books = StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.booksToBookResponseDtos(books);
    }

    @Override
    public BookResponseDto deleteBook(Long id) {
        Book book = getBook(id);
        bookRepository.delete(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto editBook(Long id, BookDto bookDto) {
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
        return mapper.bookToBookResponseDto(bookToEdit);
    }



    @Transactional
    @Override
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (Objects.nonNull(book.getCategory())) {
            throw new CategoryAlreadyAssignedException(book.getCategory().getId(), bookId);
        }
        book.setCategory(category);
        category.addBook(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto removeCategoryFromBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (!Objects.nonNull(book.getCategory())) {
            throw new CategoryIsNotAssignedException(book.getCategory().getId(), bookId);
        }
        book.setCategory(null);
        category.removeBook(book);
        return mapper.bookToBookResponseDto(book);
    }
    @Transactional
    @Override
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if (author.getBooks().contains(book)) {
            throw new BookAlreadyAssignedToAuthorException(authorId, bookId);
        }
        book.addAuthor(author);
        author.addBook(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto removeAuthorFromBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if (!(author.getBooks().contains(book))) {
            throw new BookIsNotAssignedToAuthorException(authorId, bookId);
        }
        author.removeBook(book);
        book.removeAuthor(author);
        return mapper.bookToBookResponseDto(book);
    }
}
