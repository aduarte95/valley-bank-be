package com.backend.bank.model;

import com.backend.bank.dto.AccountDTO;
import com.backend.bank.dto.FavoriteDTO;
import com.backend.bank.dto.UserDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private Integer id;
    private String givenName;
    private String lastName;
    private String direction;
    private int idNumber;
    private int telephone;
    private int cellphone;
    private byte[] profileImg;
    private boolean canBeAddedByCellphone;
    private BigDecimal transactionLimit;
    private String email;
    private String password;
    private String username;
    private List<AccountModel> accounts;
    private List<FavoriteModel> favorites;
    private String token;

    public UserModel() {

    }

    public UserModel(UserDTO userDTO) {
        setCanBeAddedByCellphone(userDTO.isCanBeAddedByCellphone());
        setCellphone(userDTO.getCellphone());
        setDirection(userDTO.getDirection());
        setLastName(userDTO.getLastName());
        setId(userDTO.getId());
        setIdNumber(userDTO.getIdNumber());
        setProfileImg(userDTO.getProfileImg());
        setTransactionLimit(userDTO.getTransactionLimit());
        setUsername(userDTO.getUsername());
        setPassword(userDTO.getPassword());
        setTelephone(userDTO.getTelephone());
        setGivenName(userDTO.getGivenName());
        setEmail(userDTO.getEmail());
    }

    public List<AccountModel> getAccounts(List<AccountDTO> accountDTOS) {
        accounts = new ArrayList<>();

        for (AccountDTO temp : accountDTOS) {
            // get accounts without the user model.
            accounts.add(new AccountModel(temp));
        }

        return accounts;
    }

    public List<FavoriteModel> getFavorites(List<FavoriteDTO> favoriteDTOS) {
        favorites = new ArrayList<>();
        FavoriteModel favoriteModel;

        for (FavoriteDTO temp : favoriteDTOS) {
            // get accounts without the user model.
            favoriteModel = new FavoriteModel(temp);
            favoriteModel.setAccountModel(new AccountModel(temp.getAccount()));
            favoriteModel.getAccountModel().setUserModel(new UserModel(temp.getUser()));

            favorites.add(favoriteModel);
        }

        return favorites;
    }

    public List<FavoriteModel> getFavorites() {
        return  this.favorites;
    }

    public List<AccountModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountModel> accounts) {
        this.accounts = accounts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getCellphone() {
        return cellphone;
    }

    public void setCellphone(int cellphone) {
        this.cellphone = cellphone;
    }

    public byte[] getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(byte[] profileImg) {
        this.profileImg = profileImg;
    }

    public boolean isCanBeAddedByCellphone() {
        return canBeAddedByCellphone;
    }

    public void setCanBeAddedByCellphone(boolean canBeAddedByCellphone) {
        this.canBeAddedByCellphone = canBeAddedByCellphone;
    }

    public BigDecimal getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(BigDecimal transactionLimit) {
        this.transactionLimit = transactionLimit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFavorites(List<FavoriteModel> favorites) {
        this.favorites = favorites;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
