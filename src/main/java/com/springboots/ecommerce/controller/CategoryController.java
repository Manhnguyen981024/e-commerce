package com.springboots.ecommerce.controller;

import com.springboots.ecommerce.entities.Category;
import com.springboots.ecommerce.model.CategoryDTO;
import com.springboots.ecommerce.model.CategoryResponse;
import com.springboots.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getCategories(@RequestParam(name = "pageNumber") Integer pageNumber,
                                                          @RequestParam(name = "pageSize") Integer pageSize) {
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO resDto = categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(resDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable long categoryId) {
        CategoryDTO categoryDTO = this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable long categoryId) {
        CategoryDTO responseDto = this.categoryService.updateCategory(categoryId, categoryDTO);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
