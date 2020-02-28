package com.backend.bank.model;

import com.backend.bank.dto.SavingDTO;

import java.math.BigDecimal;
import java.util.Date;

public class SavingModel {
    private Integer id;
    private AccountModel accountModel;
    private String name;
    private BigDecimal amount;
    private BigDecimal currentBalance;
    private BigDecimal savingAmountAdded;

    public SavingModel() {

    }

    public SavingModel(SavingDTO savingDTO) {
        setId(savingDTO.getId());
        setCurrentBalance(savingDTO.getCurrentBalance());
        setName(savingDTO.getName());
        setAmount((savingDTO.getAmount()));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountModel getAccountModel() {
        return accountModel;
    }

    public void setAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getSavingAmountAdded() {
        return savingAmountAdded;
    }

    public void setSavingAmountAdded(BigDecimal savingAmountAdded) {
        this.savingAmountAdded = savingAmountAdded;
    }
}
