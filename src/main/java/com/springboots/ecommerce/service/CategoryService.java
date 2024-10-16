package com.springboots.ecommerce.service;

import com.springboots.ecommerce.entities.Category;
import com.springboots.ecommerce.model.CategoryDTO;
import com.springboots.ecommerce.model.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize);

    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(long id);

    CategoryDTO updateCategory(long categoryId, CategoryDTO categoryDTO);
}
