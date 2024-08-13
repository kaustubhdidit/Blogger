package com.blog.springs.services.impl;

import com.blog.springs.entities.Category;
import com.blog.springs.entities.User;
import com.blog.springs.exceptions.ResourceNotFoundException;
import com.blog.springs.payloads.CategoryDto;
import com.blog.springs.payloads.UserDto;
import com.blog.springs.repositories.CategoryRepo;
import com.blog.springs.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat=this.modelMapper.map(categoryDto, Category.class);
       Category addedCat= this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedcat = this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedcat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
       return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories= this.categoryRepo.findAll();

        List<CategoryDto> categoryDtos= categories.stream().map((category)->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());

        return categoryDtos;
    }
}
