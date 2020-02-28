package com.backend.bank.dto;

import com.backend.bank.model.TransactionModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class TransactionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private AccountDTO account;

  /*  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private CategoryDTO category;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="destiny_account")
    private AccountDTO destinyAccount;

    private Date date;

    private BigDecimal amount;

    private int state;

    private int bankId;

    @Column(name = "amount_at_t")
    private BigDecimal amountAtT;

    private int otherAccount;
    private String description;

    public TransactionDTO() {}

    public TransactionDTO(TransactionModel transactionModel) {
        setAmount(transactionModel.getAmount());
        setAmountAtTrans(transactionModel.getAmountAtTrans());
        setBankId(transactionModel.getBank());
        setDate(transactionModel.getDate());
        setId(transactionModel.getId());
        setOtherAccount(transactionModel.getOtherAccount());
        setState(transactionModel.getState());
        setDescription(transactionModel.getDescription());
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

   /* public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }*/

    public AccountDTO getDestinyAccount() {
        return destinyAccount;
    }

    public void setDestinyAccount(AccountDTO destinyAccount) {
        this.destinyAccount = destinyAccount;
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

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bank) {
        this.bankId = bank;
    }

    public BigDecimal getAmountAtTrans() {
        return amountAtT;
    }

    public void setAmountAtTrans(BigDecimal amountAtTrans) {
        this.amountAtT = amountAtTrans;
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

    public BigDecimal getAmountAtT() {
        return amountAtT;
    }

    public void setAmountAtT(BigDecimal amountAtT) {
        this.amountAtT = amountAtT;
    }
}
