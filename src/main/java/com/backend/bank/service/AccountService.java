package com.backend.bank.service;

import com.backend.bank.model.AccountModel;

public interface AccountService {
    AccountModel save(AccountModel accountModel);

    AccountModel findOneById(Integer id);

    AccountModel update(AccountModel accountModel);

    boolean deleteAccount(int id);

    AccountModel accountExists(long parseInt);
}
