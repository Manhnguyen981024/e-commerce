package com.springboots.ecommerce.service;

import com.springboots.ecommerce.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    void addCategory(Category category);

    String deleteCategory(long id);

    String updateCategory(long categoryId, Category category);
}
