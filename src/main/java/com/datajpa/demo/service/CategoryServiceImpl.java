package com.datajpa.demo.service;

import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.Category;
import com.datajpa.demo.model.dto.CategoryDto;
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


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new CategoryNotFoundException(categoryId));
    }

    @Override
    public Category addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
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
    public Category editCategory(Long id, CategoryDto categoryDto) {
        Category categoryToEdit = getCategory(id);
        categoryToEdit.setName(categoryDto.getName());
        return categoryToEdit;

    }
}
