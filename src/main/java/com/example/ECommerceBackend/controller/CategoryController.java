package com.example.ECommerceBackend.controller;

import com.example.ECommerceBackend.exception.AlreadyExistsException;
import com.example.ECommerceBackend.exception.ResourceNotFoundException;
import com.example.ECommerceBackend.model.Category;
import com.example.ECommerceBackend.response.ApiResponse;
import com.example.ECommerceBackend.service.category.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController

@RequestMapping("${api.prefix}/categories")
public class CategoryController {


    private final ICategoryService categoryService;
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories(){
        try{
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(   new ApiResponse("Categories retrieved successfully", categories));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to retrieve categories", INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category name){
        try {
            Category theCategory = categoryService.addCategory(name);
            return ResponseEntity.ok(new ApiResponse("Category added successfully", theCategory));
        }catch (AlreadyExistsException e){
            return ResponseEntity.status(CONFLICT)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/category/{id}/category")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        try{
            Category theCategory = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Category retrieved successfully", theCategory));
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{name}/category")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name){
        try{
            Category theCategory = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Category retrieved successfully", theCategory));
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){
        try{
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Category retrieved successfully", null));
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/category/{id}/update")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id , @RequestBody Category category){
        try{
            Category updateCategory = categoryService.updateCategory(category , id);
            return ResponseEntity.ok(new ApiResponse("Update successfully", updateCategory));
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
