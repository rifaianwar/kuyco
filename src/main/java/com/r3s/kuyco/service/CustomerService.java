package com.r3s.kuyco.service;

import com.r3s.kuyco.model.entity.Customer;
import com.r3s.kuyco.model.request.CustomerRq;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(CustomerRq customerRq);
    Customer updateCustomer(CustomerRq customerRq,Long id);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
    void deleteCustomer(Long id);
}
