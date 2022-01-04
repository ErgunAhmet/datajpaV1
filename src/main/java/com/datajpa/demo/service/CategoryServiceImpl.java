package com.datajpa.demo.service;

import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.Category;
import com.datajpa.demo.model.exception.CategoryAlreadyAssignedException;
import com.datajpa.demo.model.exception.CategoryIsNotAssignedException;
import com.datajpa.demo.model.exception.CategoryNotFoundException;
import com.datajpa.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookService bookService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, BookService bookService) {
        this.categoryRepository = categoryRepository;
        this.bookService = bookService;
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new CategoryNotFoundException(categoryId));
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategories() {
        return StreamSupport
                .stream(categoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Category deleteCategory(Long id) {
        Category category = getCategory(id);
        categoryRepository.delete(category);
        return category;
    }

    @Override
    public Category editCategory(Long id, Category category) {
        Category categoryToEdit = getCategory(id);
        categoryToEdit.setBooks(category.getBooks());
        categoryToEdit.setName(category.getName());
        return categoryToEdit;

    }

    @Override
    public Category addBookToCategory(Long bookId, Long categoryId) {
        Category category = getCategory(categoryId);
        Book book = bookService.getBook(bookId);
        if (Objects.nonNull(book.getCategory())) {
            throw new CategoryAlreadyAssignedException(book.getCategory().getId(), bookId);
        }
        category.addBook(book);
        book.setCategory(category);
        return category;
    }

    @Override
    public Category removeBookFromCategory(Long bookId, Long categoryId) {
        Category category = getCategory(categoryId);
        Book book = bookService.getBook(bookId);
        if (!Objects.nonNull(book.getCategory())) {
            throw new CategoryIsNotAssignedException(book.getCategory().getId(), bookId);
        }
        category.removeBook(book);
        book.setCategory(null);
        return category;
    }
}
