package com.backend.bank.service.impl;

import com.backend.bank.dto.*;
import com.backend.bank.model.*;
import com.backend.bank.repository.SavingRepository;
import com.backend.bank.service.SavingService;
import org.springframework.stereotype.Service;

@Service
public class SavingServiceImpl implements SavingService {
    SavingRepository savingRepository;
    AccountServiceImpl accountService;

    public SavingServiceImpl(SavingRepository savingRepository, AccountServiceImpl accountService) {
        this.savingRepository = savingRepository;
        this.accountService = accountService;
    }

    @Override
    public SavingModel save(SavingModel savingModel) {
        SavingDTO savingDTO = new SavingDTO(savingModel);
        AccountModel accountModel = this.accountService.findOneById(savingModel.getAccountModel().getId());
        savingDTO.setAccount(new AccountDTO(accountModel));
        SavingDTO response = this.savingRepository.save(savingDTO);
        SavingModel newSavingModel = null;

        if(response != null) {
            accountModel.setBalance(accountModel.getBalance().subtract(savingDTO.getAmount()));
            this.accountService.update(accountModel);

            newSavingModel = new SavingModel(response);
        }

        return newSavingModel;
    }

    @Override
    public SavingModel findOneById(Integer id) {
        SavingDTO savingDTO = savingRepository.findOneById(id);
        SavingModel savingModel = new SavingModel(savingDTO);
        savingModel.setAccountModel(new AccountModel(savingDTO.getAccount()));

        return savingModel;
    }

    @Override
    public SavingModel update(SavingModel savingModel) {
        if(savingModel.getSavingAmountAdded() != null) {
            savingModel.setCurrentBalance(savingModel.getCurrentBalance().add(savingModel.getSavingAmountAdded()));
        }

        SavingDTO savingDTO = new SavingDTO(savingModel);
        savingDTO.setAccount(new AccountDTO(savingModel.getAccountModel()));

        return new SavingModel(this.savingRepository.save(savingDTO));
    }

    @Override
    public boolean delete(int id) {
        boolean successful = true;

        try {
            this.savingRepository.deleteById(id);
        } catch (Exception e) {
            successful = false;
        }

        return successful;
    }
}
