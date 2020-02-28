package com.backend.bank.service.impl;

import com.backend.bank.dto.AccountDTO;
import com.backend.bank.dto.UserDTO;
import com.backend.bank.model.AccountModel;
import com.backend.bank.model.UserModel;
import com.backend.bank.repository.AccountRepository;
import com.backend.bank.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountModel save(AccountModel accountModel) {
        AccountDTO accountDTO = new AccountDTO(accountModel);
        accountDTO.setUser(new UserDTO(accountModel.getUserModel()));
        accountDTO.setAccountNumber(this.accountRepository.count());

        return new AccountModel(this.accountRepository.save(accountDTO));
    }

    @Override
    public AccountModel findOneById(Integer id) {
        AccountDTO accountDTO = this.accountRepository.findOneById(id);
        AccountModel accountModel = null;

        if (accountDTO != null) {
            accountModel = new AccountModel(accountDTO);
            accountModel.setUserModel(new UserModel(accountDTO.getUser()));
            accountModel.getSavings(accountDTO.getSavings());
            accountModel.getTransactions(accountDTO.getTransactions());
        }

        return accountModel;
    }

    @Override
    public AccountModel update(AccountModel accountModel) {

        if(accountModel.getSavingAmount() != null) {
            accountModel.setBalance(accountModel.getSavingAmount().add(accountModel.getBalance()));
        }
        if(accountModel.getSavingAmountAdded() != null) {
            accountModel.setBalance(accountModel.getBalance().subtract(accountModel.getSavingAmountAdded()));
        }

        AccountDTO accountDTO = new AccountDTO(accountModel);

        if(accountModel.getUserModel() != null) {
            accountDTO.setUser(new UserDTO(accountModel.getUserModel()));
            accountDTO.getSavings(accountModel.getSavings());
            accountDTO.getTransactions(accountModel.getTransactions());
            accountDTO.getDestinyTransactions(accountModel.getDestinyTransactions());
        }

        return new AccountModel(this.accountRepository.save(accountDTO));

    }

    @Override
    public boolean deleteAccount(int id) {
        boolean successful = true;

        try {
            this.accountRepository.deleteById(id);
        } catch (Exception e) {
            successful = false;
        }

        return successful;
    }

    @Override
    public AccountModel accountExists(long accountNumber) {
        AccountDTO accountDTO = this.accountRepository.findOneByAccountNumber(accountNumber);
        AccountModel accountModel = null;

        if(accountDTO != null ){
            accountModel = new AccountModel(accountDTO);
        }
        return accountModel;
    }

}
