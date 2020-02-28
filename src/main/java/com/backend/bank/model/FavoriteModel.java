package com.backend.bank.model;


import com.backend.bank.dto.FavoriteDTO;

public class FavoriteModel {
   private int id;
   private boolean usingToken;
   private UserModel userModel;
   private AccountModel accountModel;
    private String name;

   public FavoriteModel() {}

    public FavoriteModel(FavoriteDTO favoriteDTO) {
        setId(favoriteDTO.getId());
        setUsingToken(favoriteDTO.isUsingToken());
        setName(favoriteDTO.getName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUsingToken() {
        return usingToken;
    }

    public void setUsingToken(boolean usingToken) {
        this.usingToken = usingToken;
    }

    public AccountModel getAccountModel() {
        return accountModel;
    }

    public void setAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
