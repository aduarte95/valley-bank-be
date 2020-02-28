package com.backend.bank.dto;

import com.backend.bank.model.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class AccountDTO {
    private static int nextAccountNumber = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserDTO user;

    private BigDecimal balance;

    private long accountNumber;

    private int accountType;

    private int transactionLimit;

    private BigDecimal initialAmount;

    private int currencyId;

    private int privacy;

    private BigDecimal interest;

    private String name;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
    private FavoriteDTO favorite;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<SavingDTO> savings;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<TransactionDTO> transactions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "destinyAccount")
    private  List<TransactionDTO> destinyTransactions;

    public AccountDTO(AccountModel accountModel) {
        setId(accountModel.getId());
        setAccountNumber(accountModel.getAccountNumber());
        setAccountType(accountModel.getAccountType());
        setBalance(accountModel.getBalance());
        setCurrencyId(accountModel.getCurrencyId());
        setId(accountModel.getId());
        setInitialAmount(accountModel.getInitialAmount());
        setInterest(accountModel.getInterest());
        setPrivacy(accountModel.getPrivacy());
        setTransactionLimit(accountModel.getTransactionLimit());
        setName(accountModel.getName());
        setAccountNumber(accountModel.getAccountNumber());
    }

    public AccountDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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

    public void setAccountNumberFormat() {
        String accountNumber = Integer.toString(nextAccountNumber++);

        String fill = "0".repeat(Math.max(0, 17 - accountNumber.length())) +
                accountNumber;

        setAccountNumber(Long.parseLong(fill));
    }

    public List<SavingDTO> getSavings(List<SavingModel> savingModels) {
        savings = new ArrayList<>();
        SavingDTO savingDTO;

        if(savingModels != null) {
            for (SavingModel temp : savingModels) {
                // get accounts without the user model.
                savingDTO = new SavingDTO(temp);
                savingDTO.setAccount(new AccountDTO(temp.getAccountModel()));

                savings.add(savingDTO);
            }
        }

        return savings;
    }

    public List<SavingDTO> getSavings() {
        return savings;
    }

    public void setSavings(List<SavingDTO> savings) {
        this.savings = savings;
    }

    public List<TransactionDTO> getTransactions(List<TransactionModel> transactionModels) {
        transactions = new ArrayList<>();
        TransactionDTO transactionDTO;

        if(transactionModels != null) {
            for (TransactionModel temp : transactionModels) {
                // get accounts without the user model.
                transactionDTO = new TransactionDTO(temp);
                transactionDTO.setDestinyAccount(new AccountDTO(temp.getDestinyAccount()));
                //transactionDTO.setCategory(new CategoryDTO(temp.getCategoryModel()));
                transactionDTO.setAccount(new AccountDTO(temp.getAccountModel()));


                transactions.add(transactionDTO);
            }
        }

        return transactions;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionDTO> getDestinyTransactions(List<TransactionModel> accountModels) {
        return getTransactions(accountModels);
    }

    public List<TransactionDTO> getDestinyTransactions() {
        return destinyTransactions;
    }

    public void setDestinyTransactions(List<TransactionDTO> destinyAccounts) {
        this.destinyTransactions = destinyAccounts;
    }
}
