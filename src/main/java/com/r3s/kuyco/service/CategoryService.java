package com.r3s.kuyco.service;

import com.r3s.kuyco.model.entity.Category;
import com.r3s.kuyco.model.request.CategoryRq;
import com.r3s.kuyco.model.response.GenericResponse;

import java.util.List;

public interface CategoryService {

    Category addCategory(CategoryRq inMsg);
    Category updateCategory(CategoryRq inMsg, Long id);
    Category getCategoryById(Long id);
    List<Category> getAll();
    String deleteCategory(Long id);
    String softDeleteCategory(Long id);


}
