package com.datajpa.demo.service;

import com.datajpa.demo.model.Category;
import com.datajpa.demo.model.dto.request.CategoryDto;
import com.datajpa.demo.model.dto.response.CategoryResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public Category getCategory(Long categoryId);
    public CategoryResponseDto getCategoryById(Long categoryId);
    public CategoryResponseDto addCategory(CategoryDto categoryDto);
    public List<CategoryResponseDto> getCategories();
    public CategoryResponseDto deleteCategory(Long id);
    public CategoryResponseDto editCategory(Long id, CategoryDto categoryDto);
}
