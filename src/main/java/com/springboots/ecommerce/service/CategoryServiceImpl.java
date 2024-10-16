package com.springboots.ecommerce.service;

import com.springboots.ecommerce.exceptions.APIException;
import com.springboots.ecommerce.exceptions.ResourceNotFoundException;
import com.springboots.ecommerce.entities.Category;
import com.springboots.ecommerce.model.CategoryDTO;
import com.springboots.ecommerce.model.CategoryResponse;
import com.springboots.ecommerce.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Category> categories = categoryRepository.findAll(pageable);

        List<CategoryDTO> contentDtos =categories.getContent()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

        if (categories.isEmpty()) {
            throw new APIException("Category is not created!");
        }

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(contentDtos);
        categoryResponse.setPageNumber(categories.getNumber());
        categoryResponse.setPageSize(categories.getSize());
        categoryResponse.setTotalPages(categories.getTotalPages());
        categoryResponse.setTotalElements(categories.getTotalElements());
        categoryResponse.setLastPage(categories.isLast());
        return categoryResponse;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO category) {
        Category categoryEntity = modelMapper.map(category, Category.class);
        return modelMapper.map(this.categoryRepository.save(categoryEntity), CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(long id) {
        Category category = this.categoryRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", id, "categoryId"));

        CategoryDTO deletedCategory = this.modelMapper.map(category, CategoryDTO.class);
        this.categoryRepository.delete(category);
        return deletedCategory;
    }

    @Override
    public CategoryDTO updateCategory(long categoryId, CategoryDTO updatedCategory) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", categoryId, "categoryId"));

        category.setCategoryName(updatedCategory.getCategoryName());

        return modelMapper.map(  this.categoryRepository.save(category), CategoryDTO.class);
    }
}
