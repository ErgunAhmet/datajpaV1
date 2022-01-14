package com.datajpa.demo.model.dto;

import com.datajpa.demo.model.Category;
import lombok.Data;

@Data
public class CategoryDto {
    private String name;

    public static CategoryDto from(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
