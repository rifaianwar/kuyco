package com.r3s.kuyco.controller;

import com.r3s.kuyco.model.entity.Item;
import com.r3s.kuyco.model.request.ItemRq;
import com.r3s.kuyco.model.request.ItemRq;
import com.r3s.kuyco.model.response.GenericResponse;
import com.r3s.kuyco.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    ResponseEntity<?> addItem(@RequestBody ItemRq itemRq) {
        Object object = itemService.addItem(itemRq);
        GenericResponse<?> response = new GenericResponse<>(HttpStatus.CREATED.value(),"Success",object);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<?> updateItem(@Valid @RequestBody ItemRq itemRq, @PathVariable("id") Long id) {
        Object object = itemService.updateItem(itemRq, id);
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.OK.value(),"Success",object);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping("/get")
    ResponseEntity<?> getItemById(@RequestParam("id") Long id) {
        Object object = itemService.getItemById(id);
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.OK.value(),"Success",object);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    ResponseEntity<?> getAllItem() {
        Object object = itemService.getAll();
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.OK.value(),"Success",object);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteItem(@PathVariable("id") Long id) {
        Object object = itemService.deleteItem(id);
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.OK.value(),"Success",object);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }
}
