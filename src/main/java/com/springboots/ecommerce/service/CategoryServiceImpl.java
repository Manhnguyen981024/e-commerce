package com.springboots.ecommerce.service;

import com.springboots.ecommerce.exceptions.APIException;
import com.springboots.ecommerce.exceptions.ResourceNotFoundException;
import com.springboots.ecommerce.model.Category;
import com.springboots.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();

        if (categories.isEmpty()) {
            throw new APIException("Category is not created!");
        }

        return categories;
    }

    @Override
    public void addCategory(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(long id) {
        Category category = this.categoryRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", id, "categoryId"));

        this.categoryRepository.delete(category);
        return "Deleted Category !!!";
    }

    @Override
    public String updateCategory(long categoryId, Category updatedCategory) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", categoryId, "categoryId"));

        category.setCategoryName(updatedCategory.getCategoryName());
        this.categoryRepository.save(category);
        return "Updated Category successfully !!!";
    }
}
