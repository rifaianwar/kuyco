package com.r3s.kuyco.service.impl;

import com.r3s.kuyco.exception.NotFoundException;
import com.r3s.kuyco.model.entity.Category;
import com.r3s.kuyco.model.entity.Customer;
import com.r3s.kuyco.model.entity.Item;
import com.r3s.kuyco.model.entity.Transaction;
import com.r3s.kuyco.model.request.TrxRq;
import com.r3s.kuyco.repository.CustomerRepository;
import com.r3s.kuyco.repository.ItemRepository;
import com.r3s.kuyco.repository.TransactionRepository;
import com.r3s.kuyco.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final ItemRepository itemRepository;
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public TransactionServiceImpl(ItemRepository itemRepository, TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.itemRepository = itemRepository;
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public Transaction createTrx(TrxRq trxRq) {
        for (Long cat : trxRq.getItem().getItemId()) {
            Optional<Item> findItem = itemRepository.findById(cat);
            if (findItem.isEmpty() || findItem.get().equals("")) {
                log.error("Item Id" + cat + " not found");
                throw new NotFoundException("Item Id " + cat + " not found");
            }
        }

        List<Item> itemList = itemRepository.findByIdIn(trxRq.getItem().getItemId());
        int totalPrice = 0;
        for (Item item : itemList) {
            totalPrice += item.getPrice();
        }

        Customer customer = customerRepository.getCustomerByEmail(trxRq.getEmail());
        if (null == customer || customer.equals("")) {
            log.error("Customer Email " + trxRq.getEmail() + " not found");
            throw new NotFoundException("Customer Email " + trxRq.getEmail() + " not found");
        }
        Transaction transaction = new Transaction();
        transaction.setCustomer(customer);
        transaction.setItems(itemList);
        transaction.setTrxDate(new Date());
        transaction.setTotalAmount(totalPrice);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Transaction getTrx(UUID trxId) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(trxId);
        if (transactionOptional.isEmpty()) {
            log.error("Transaction id " + trxId + " not found");
            throw new NotFoundException("Transaction Id" + trxId + " not found");
        }
        return transactionOptional.get();
    }
}
