package com.backend.bank.service;

import com.backend.bank.model.TransactionModel;

import java.util.List;

public interface TransactionService {
    TransactionModel save(TransactionModel transactionModel);

    TransactionModel findOneById(Integer id);

    TransactionModel update(TransactionModel transactionModel);

    List<TransactionModel> findAll();

    boolean delete(int id);
}
