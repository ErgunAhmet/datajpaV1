package com.datajpa.demo.controller;

import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.Category;
import com.datajpa.demo.model.dto.BookDto;
import com.datajpa.demo.model.dto.CategoryDto;
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
    public ResponseEntity<Category> addAuthor(@RequestBody final CategoryDto categoryDto) {
        Category category = categoryService.addCategory(Category.from(categoryDto));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Category> getAuthor(@PathVariable final Long id) {
        Category category = categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAuthors() {
        List<Category> categories = categoryService.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> deleteAuthor(@PathVariable final Long id) {
        Category category = categoryService.deleteCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> editAuthor(@PathVariable final Long id,
                                               @RequestBody final CategoryDto categoryDto) {
        Category category = categoryService.editCategory(id, Category.from(categoryDto));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
