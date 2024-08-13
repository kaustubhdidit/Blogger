package com.blog.springs.controllers;

import com.blog.springs.payloads.ApiResponse;
import com.blog.springs.payloads.CategoryDto;
import com.blog.springs.payloads.UserDto;
import com.blog.springs.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import com.blog.springs.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){

        CategoryDto createdCategoryDto=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer cid){
        CategoryDto updateCategory=this.categoryService.updateCategory(categoryDto, cid);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer cid){
        this.categoryService.deleteCategory(cid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Sucessfully",true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    @GetMapping("/{cid}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer cid){
        CategoryDto categoryDto=this.categoryService.getCategory(cid);
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }
}
