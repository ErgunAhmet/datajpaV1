package com.datajpa.demo.service;

import com.datajpa.demo.mapper.mapper;
import com.datajpa.demo.model.Category;
import com.datajpa.demo.model.dto.request.CategoryDto;
import com.datajpa.demo.model.dto.response.CategoryResponseDto;
import com.datajpa.demo.model.exception.CategoryNotFoundException;
import com.datajpa.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public CategoryResponseDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new CategoryNotFoundException(categoryId));
        return mapper.categoryToCategoryResponseDto(category);
    }
    @Override
    public Category getCategory(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new CategoryNotFoundException(categoryId));
    }
    @Override
    public CategoryResponseDto addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return mapper.categoryToCategoryResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> getCategories() {
        List<Category> categories = StreamSupport
                .stream(categoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.categoriesToCategoryResponseDtos(categories);
    }

    @Override
    public CategoryResponseDto deleteCategory(Long id) {
        Category category = getCategory(id);
        categoryRepository.delete(category);
        return mapper.categoryToCategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto editCategory(Long id, CategoryDto categoryDto) {
        Category categoryToEdit = getCategory(id);
        categoryToEdit.setName(categoryDto.getName());
        return mapper.categoryToCategoryResponseDto(categoryToEdit);

    }
}
