package com.r3s.kuyco.service.impl;

import com.r3s.kuyco.exception.BadRequestException;
import com.r3s.kuyco.exception.NotFoundException;
import com.r3s.kuyco.model.entity.Category;
import com.r3s.kuyco.model.entity.Item;
import com.r3s.kuyco.model.entity.Transaction;
import com.r3s.kuyco.model.request.CategoryRq;
import com.r3s.kuyco.repository.CategoryRepository;
import com.r3s.kuyco.repository.ItemRepository;
import com.r3s.kuyco.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    @Override
    public Category addCategory(CategoryRq categoryRq) {

        Category category = new Category();
        category.setCreatedDate(new Date());
        category.setName(categoryRq.getName());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category updateCategory(CategoryRq categoryRq, Long id) {

        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            log.error("Category ID {} not found", id);
            throw new NotFoundException("Category ID " + id + " not found");
        }
        category.get().setName(categoryRq.getName());
        category.get().setUpdatedDate(new Date());
        categoryRepository.save(category.get());

        return category.get();
    }

    @Override
    public Category getCategoryById(Long id) {

        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            log.error("Category ID {} not found", id);
            throw new NotFoundException("Category not found");
        }
        return category.get();

    }

    @Override
    public List<Category> getAll() {

        List<Category> category = categoryRepository.findAll();
        return category;

    }

    @Transactional
    @Override
    public String deleteCategory(Long id) {
        List<Category> i = categoryRepository.checkCategoryRelation(id);
        if (i.size() > 0) {
            throw new BadRequestException("Cannot delete Category with existing category relationship");
        }

        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            log.error("Category ID {} not found", id);
            throw new NotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
        return "Successfully deleted Category";

    }

    @Transactional
    @Override
    public String softDeleteCategory(Long id) {

        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            log.error("Category ID {} not found", id);
            throw new NotFoundException("Category not found");
        }
        category.get().setDeletedDate(new Date());
        categoryRepository.save(category.get());
        return "Successfully deleted Category";

    }
}
