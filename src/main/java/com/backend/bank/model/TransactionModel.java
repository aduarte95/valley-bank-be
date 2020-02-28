package com.backend.bank.model;

import com.backend.bank.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionModel {
    private Integer id;
    private  AccountModel accountModel;
    //private CategoryModel categoryModel;
    private AccountModel destinyAccount;
    private Date date;
    private BigDecimal amount;
    private int state;
    private int bank;
    private BigDecimal amountAtTrans;
    private int otherAccount;
    private String description;

    public TransactionModel() {

    }

    public TransactionModel(TransactionDTO transactionDTO) {
        setAmount(transactionDTO.getAmount());
        setAmountAtTrans(transactionDTO.getAmountAtTrans());
        setBank(transactionDTO.getBankId());
        setDate(transactionDTO.getDate());
        setId(transactionDTO.getId());
        setOtherAccount(transactionDTO.getOtherAccount());
        setState(transactionDTO.getState());
        setDescription(transactionDTO.getDescription());
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public BigDecimal getAmountAtTrans() {
        return amountAtTrans;
    }

    public void setAmountAtTrans(BigDecimal amountAtTrans) {
        this.amountAtTrans = amountAtTrans;
    }

   /* public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }
*/
    public AccountModel getDestinyAccount() {
        return destinyAccount;
    }

    public void setDestinyAccount(AccountModel destinyAccount) {
        this.destinyAccount = destinyAccount;
    }

    public int getOtherAccount() {
        return otherAccount;
    }

    public void setOtherAccount(int otherAccount) {
        this.otherAccount = otherAccount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
