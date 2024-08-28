package com.r3s.kuyco.controller;

import com.r3s.kuyco.model.entity.Category;
import com.r3s.kuyco.model.request.CategoryRq;
import com.r3s.kuyco.model.request.ItemRq;
import com.r3s.kuyco.model.response.GenericResponse;
import com.r3s.kuyco.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    ResponseEntity<?> addCategory(@Valid @RequestBody CategoryRq categoryRq) {
        Object object = categoryService.addCategory(categoryRq);
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.CREATED.value(),"Success",object);
        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryRq categoryRq, @PathVariable("id") Long id) {
        Object object = categoryService.updateCategory(categoryRq, id);
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.OK.value(),"Success",object);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping("/get")
    ResponseEntity<?> getCategoryById(@RequestParam("id") Long id) {
        Object object = categoryService.getCategoryById(id);
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.OK.value(),"Success",object);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    ResponseEntity<?> getAllCategory() {
        Object object = categoryService.getAll();
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.OK.value(),"Success",object);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        Object object = categoryService.deleteCategory(id);
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.OK.value(),"Success",object);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @DeleteMapping("/softdelete/{id}")
    ResponseEntity<?> softDeleteCategory(@PathVariable("id") Long id) {
        Object object = categoryService.softDeleteCategory(id);
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.OK.value(),"Success",object);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }



}
