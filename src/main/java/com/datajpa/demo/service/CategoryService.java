package com.datajpa.demo.service;

import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public Category getCategory(Long categoryId);
    public Category addCategory(Category category);
    public List<Category> getCategories();
    public Category deleteCategory(Long id);
    public Category editCategory(Long id, Category category);
//    public Category addBookToCategory(Long bookId, Long categoryId);
//    public Category removeBookFromCategory(Long bookId, Long categoryId);
}
