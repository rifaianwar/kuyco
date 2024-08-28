package com.r3s.kuyco.repository;

import com.r3s.kuyco.model.entity.Customer;
import com.r3s.kuyco.model.entity.Item;
import com.r3s.kuyco.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT c FROM Transaction c WHERE c.customer.id = :customerId")
    Transaction findByCustomerId(Long customerId);
}
