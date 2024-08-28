package com.r3s.kuyco.service;

import com.r3s.kuyco.model.entity.Customer;
import com.r3s.kuyco.model.entity.Transaction;
import com.r3s.kuyco.model.request.CustomerRq;
import com.r3s.kuyco.model.request.TrxItemRq;
import com.r3s.kuyco.model.request.TrxRq;

import java.util.UUID;

public interface TransactionService {
    Transaction createTrx(TrxRq trxRq);
    Transaction getTrx(UUID trxId);

}
