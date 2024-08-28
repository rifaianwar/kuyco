package com.r3s.kuyco.service.impl;

import com.r3s.kuyco.exception.BadRequestException;
import com.r3s.kuyco.exception.NotFoundException;
import com.r3s.kuyco.model.entity.Customer;
import com.r3s.kuyco.model.entity.Item;
import com.r3s.kuyco.model.entity.Transaction;
import com.r3s.kuyco.model.request.CustomerRq;
import com.r3s.kuyco.repository.CustomerRepository;
import com.r3s.kuyco.repository.TransactionRepository;
import com.r3s.kuyco.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    @Override
    public Customer addCustomer(CustomerRq customerRq) {
        Customer customer = new Customer();
        customer.setCreatedDate(new Date());
        customer.setName(customerRq.getName());
        customer.setEmail(customerRq.getEmail());
        customer.setPhone(customerRq.getPhone());
        customer.setAddress(customerRq.getAddress());
        customerRepository.save(customer);
        return customer;

    }

    @Transactional
    @Override
    public Customer updateCustomer(CustomerRq customerRq, Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            log.error("Customer ID " + id + " not found");
            throw new NotFoundException("Customer ID " + id + " not found");
        }
        customer.get().setUpdatedDate(new Date());
        customer.get().setName(customerRq.getName());
        customer.get().setEmail(customerRq.getEmail());
        customer.get().setPhone(customerRq.getPhone());
        customer.get().setAddress(customerRq.getAddress());
        customerRepository.save(customer.get());
        return customer.get();

    }

    @Override
    public Customer getCustomerById(Long id) {

        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            log.error("Customer ID " + id + " not found");
            throw new NotFoundException("Customer ID " + id + " not found");
        }
        return customer.get();

    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @Override
    public void deleteCustomer(Long id) {

        Transaction transaction = transactionRepository.findByCustomerId(id);
        if (transaction != null) {
            throw new BadRequestException("Cannot delete customer with existing transactions");
        }

        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            log.error("Customer ID " + id + " not found");
            throw new NotFoundException("Customer ID " + id + " not found");
        }
        customerRepository.delete(customer.get());
        log.info("Customer ID " + id + " deleted.");
    }
}
