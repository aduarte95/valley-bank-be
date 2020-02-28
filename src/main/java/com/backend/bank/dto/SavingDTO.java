package com.backend.bank.dto;

import com.backend.bank.model.SavingModel;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "saving")
public class SavingDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private AccountDTO account;

    private BigDecimal amount;

    private BigDecimal currentBalance;

    private String name;

    public SavingDTO(){}

    public SavingDTO(SavingModel savingModel) {
        setId(savingModel.getId());
        setCurrentBalance(savingModel.getCurrentBalance());
        setAmount(savingModel.getAmount());
        setName(savingModel.getName());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
