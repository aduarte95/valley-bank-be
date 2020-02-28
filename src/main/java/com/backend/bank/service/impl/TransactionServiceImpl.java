package com.backend.bank.service.impl;

import com.backend.bank.dto.*;
import com.backend.bank.model.*;
import com.backend.bank.repository.TransactionRepository;
import com.backend.bank.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transactionRepository;
    AccountServiceImpl accountService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountServiceImpl accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Override
    public TransactionModel save(TransactionModel transactionModel) {
        TransactionDTO transactionDTO = new TransactionDTO(transactionModel);
        AccountModel accountModel = this.accountService.findOneById(transactionModel.getAccountModel().getId());
        AccountModel destinyAccount = this.accountService.findOneById(transactionModel.getDestinyAccount().getId());
        transactionDTO.setAccount(new AccountDTO(accountModel));
        transactionDTO.setDestinyAccount(new AccountDTO(destinyAccount));
        TransactionDTO response = (this.transactionRepository.save(transactionDTO));
        TransactionModel newTransactionModel = null;

        if(response != null) {
            accountModel.setBalance(accountModel.getBalance().subtract(transactionDTO.getAmount()));
            destinyAccount.setBalance(destinyAccount.getBalance().add(transactionDTO.getAmount()));
            this.accountService.update(accountModel);
            this.accountService.update(destinyAccount);
            newTransactionModel = new TransactionModel(response);
        }


        return newTransactionModel;
    }

    @Override
    public TransactionModel findOneById(Integer id) {
        TransactionDTO transactionDTO = transactionRepository.findOneById(id);
        TransactionModel transactionModel = new TransactionModel(transactionDTO);
     //   transactionModel.setCategoryModel(new CategoryModel(transactionDTO.getCategory()));
        transactionModel.setAccountModel(new AccountModel(transactionDTO.getAccount()));
        transactionModel.setDestinyAccount(new AccountModel(transactionDTO.getDestinyAccount()));

        return transactionModel;
    }

    @Override
    public TransactionModel update(TransactionModel transactionModel) {
        TransactionDTO transactionDTO = new TransactionDTO(transactionModel);
        transactionDTO.setAccount(new AccountDTO(transactionModel.getAccountModel()));
       // transactionDTO.setCategory(new CategoryDTO(transactionModel.getCategoryModel()));
        transactionDTO.setDestinyAccount(new AccountDTO(transactionModel.getAccountModel()));

        return new TransactionModel(this.transactionRepository.save(transactionDTO));
    }

    @Override
    public List<TransactionModel> findAll() {
        List<TransactionModel> transactionModels = new ArrayList<>();
        List<TransactionDTO>  transactionDTOS = this.transactionRepository.findAll();

        for (TransactionDTO temp : transactionDTOS) {
            transactionModels.add(new TransactionModel(temp));
        }
        return  transactionModels;
    }

    @Override
    public boolean delete(int id) {
        boolean successful = true;

        try {
            this.transactionRepository.deleteById(id);
        } catch (Exception e) {
            successful = false;
        }

        return successful;
    }
}
