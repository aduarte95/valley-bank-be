package com.backend.bank.model;

import com.backend.bank.dto.AccountDTO;
import com.backend.bank.dto.CategoryDTO;
import com.backend.bank.dto.SavingDTO;
import com.backend.bank.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountModel {
    private Integer id;
    private BigDecimal balance;
    private long accountNumber;
    private int accountType;
    private int transactionLimit;
    private BigDecimal initialAmount;
    private int currencyId;
    private int privacy;
    private BigDecimal interest;
    private UserModel userModel;
    private List<SavingModel> savings;
    private List<TransactionModel> transactions;
    private List<TransactionModel> destinyTransactions;
    private String name;
    private BigDecimal savingAmount;
    private BigDecimal savingAmountAdded;

    public AccountModel() {

    }

    public AccountModel(AccountDTO accountDTO) {
        setId(accountDTO.getId());
        setAccountType(accountDTO.getAccountType());
        setBalance(accountDTO.getBalance());
        setCurrencyId(accountDTO.getCurrencyId());
        setId(accountDTO.getId());
        setInitialAmount(accountDTO.getInitialAmount());
        setInterest(accountDTO.getInterest());
        setPrivacy(accountDTO.getPrivacy());
        setTransactionLimit(accountDTO.getTransactionLimit());
        setName(accountDTO.getName());
        setAccountNumber(accountDTO.getAccountNumber());
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(int transactionLimit) {
        this.transactionLimit = transactionLimit;
    }

    public BigDecimal getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public List<SavingModel> getSavings(List<SavingDTO> savingDTOS) {
        savings = new ArrayList<>();
        SavingModel savingModel;

        for (SavingDTO temp : savingDTOS) {
            // get accounts without the user model.
            savingModel = new SavingModel(temp);
            savingModel.setAccountModel(new AccountModel(temp.getAccount()));

            savings.add(savingModel);
        }

        return savings;
    }

    public List<SavingModel> getSavings() {
        return savings;
    }

    public void setSavings(List<SavingModel> savings) {
        this.savings = savings;
    }

    public List<TransactionModel> getTransactions(List<TransactionDTO> transactionDTOS) {
        transactions = new ArrayList<>();
        TransactionModel transactionModel;

        for (TransactionDTO temp : transactionDTOS) {
            // get accounts without the user model.
            transactionModel = new TransactionModel(temp);
            transactionModel.setDestinyAccount(new AccountModel(temp.getDestinyAccount()));
        //    transactionModel.setCategoryModel(new CategoryModel(temp.getCategory()));
            transactionModel.setAccountModel(new AccountModel(temp.getAccount()));

            transactions.add(transactionModel);
        }

        return transactions;
    }

    public List<TransactionModel> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionModel> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionModel> getDestinyTransactions(List<TransactionDTO> transactionDTOS) {
        return getTransactions(transactionDTOS);
    }

    public List<TransactionModel> getDestinyTransactions() {
        return destinyTransactions;
    }

    public void setDestinyTransactions(List<TransactionModel> destinyTransactions) {
        this.destinyTransactions = destinyTransactions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSavingAmount() {
        return savingAmount;
    }

    public void setSavingAmount(BigDecimal savingAmount) {
        this.savingAmount = savingAmount;
    }

    public BigDecimal getSavingAmountAdded() {
        return savingAmountAdded;
    }

    public void setSavingAmountAdded(BigDecimal savingAmountAdded) {
        this.savingAmountAdded = savingAmountAdded;
    }
}
