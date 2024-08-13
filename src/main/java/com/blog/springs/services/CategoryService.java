package com.blog.springs.services;

import com.blog.springs.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    // making methods public is not necessary because in interfaces methods are public by default

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    //delete
   void deleteCategory(Integer categoryId);

    //get
   CategoryDto getCategory( Integer categoryId);

    //getAll
    List<CategoryDto> getCategories();
}
