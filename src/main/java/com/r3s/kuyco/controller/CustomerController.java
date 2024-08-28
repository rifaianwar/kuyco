package com.r3s.kuyco.controller;

import com.r3s.kuyco.model.request.CustomerRq;
import com.r3s.kuyco.model.response.GenericResponse;
import com.r3s.kuyco.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
    @PostMapping("/add")
    ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerRq customerRq) {
        Object object = customerService.addCustomer(customerRq);
        GenericResponse<?> response = new GenericResponse<>(HttpStatus.CREATED.value(),"Success",object);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerRq customerRq, @PathVariable("id") Long id) {
        Object object = customerService.updateCustomer(customerRq, id);
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.OK.value(),"Success",object);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping("/get")
    ResponseEntity<?> getCustomerById(@RequestParam("id") Long id) {
        Object object = customerService.getCustomerById(id);
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.OK.value(),"Success",object);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    ResponseEntity<?> getAllCustomer() {
        Object object = customerService.getAllCustomers();
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.OK.value(),"Success",object);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @DeleteMapping
    ResponseEntity<?> deleteCustomer(@RequestParam("id") Long id) {
        customerService.deleteCustomer(id);
        GenericResponse<?> genericResponse = new GenericResponse<>(HttpStatus.OK.value(),"Success",id);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }
    
}
