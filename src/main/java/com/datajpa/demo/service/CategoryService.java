package com.datajpa.demo.service;

import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.Category;
import com.datajpa.demo.model.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public Category getCategory(Long categoryId);
    public Category addCategory(CategoryDto categoryDto);
    public List<Category> getCategories();
    public Category deleteCategory(Long id);
    public Category editCategory(Long id, CategoryDto categoryDto);
}
