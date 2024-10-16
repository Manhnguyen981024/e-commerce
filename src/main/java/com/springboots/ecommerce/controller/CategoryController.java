package com.springboots.ecommerce.controller;

import com.springboots.ecommerce.model.Category;
import com.springboots.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        categoryService.addCategory(category);
        return new ResponseEntity<>("Add category successfully!!", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable long categoryId) {
        try {
            String message = this.categoryService.deleteCategory(categoryId);
            return new ResponseEntity<String>(message, HttpStatus.OK);

        } catch (ResponseStatusException e) {
            return new ResponseEntity<String>(e.getReason(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable long categoryId) {
        try {
            String message = this.categoryService.updateCategory(categoryId, category);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<String>(e.getReason(), HttpStatus.NOT_FOUND);
        }
    }
}
