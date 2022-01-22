package com.datajpa.demo.controller;

import com.datajpa.demo.model.Category;
import com.datajpa.demo.model.dto.request.CategoryDto;
import com.datajpa.demo.model.dto.response.CategoryResponseDto;
import com.datajpa.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryResponseDto> addAuthor(@RequestBody final CategoryDto categoryDto) {
        CategoryResponseDto category = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryResponseDto> getAuthor(@PathVariable final Long id) {
        CategoryResponseDto category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryResponseDto>> getAuthors() {
        List<CategoryResponseDto> categories = categoryService.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryResponseDto> deleteAuthor(@PathVariable final Long id) {
        CategoryResponseDto category = categoryService.deleteCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CategoryResponseDto> editAuthor(@PathVariable final Long id,
                                               @RequestBody final CategoryDto categoryDto) {
        CategoryResponseDto category = categoryService.editCategory(id,categoryDto);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
