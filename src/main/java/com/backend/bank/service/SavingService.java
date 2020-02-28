package com.backend.bank.service;

import com.backend.bank.model.SavingModel;

public interface SavingService {
    SavingModel save(SavingModel savingModel);

    SavingModel findOneById(Integer id);

    SavingModel update(SavingModel savingModel);

    boolean delete(int id);
}
