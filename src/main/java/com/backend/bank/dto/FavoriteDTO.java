package com.backend.bank.dto;

import com.backend.bank.model.FavoriteModel;

import javax.persistence.*;

@Entity
@Table(name = "favorite")
public class FavoriteDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "account_id")
    AccountDTO account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    UserDTO user;

    private boolean usingToken;

    private String name;

    public FavoriteDTO() {}

    public FavoriteDTO(FavoriteModel favoriteModel) {
        setId(favoriteModel.getId());
        setUsingToken(favoriteModel.isUsingToken());
        setName(favoriteModel.getName());
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

    public boolean isUsingToken() {
        return usingToken;
    }

    public void setUsingToken(boolean token) {
        this.usingToken = token;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
