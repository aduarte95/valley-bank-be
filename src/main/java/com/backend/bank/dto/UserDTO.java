package com.backend.bank.dto;

import com.backend.bank.model.AccountModel;
import com.backend.bank.model.FavoriteModel;
import com.backend.bank.model.UserModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "last_name")
    private String lastName;

    private String direction;

    @Column(name = "id_number", unique=true)
    private int idNumber;

    @Column(name = "telephone")
    private int telephone;

    @Column(name = "cellphone")
    private int cellphone;

    @Column(name = "profile_img")
    private byte[] profileImg;

    @Column(name = "added_by_cellphone")
    private boolean canBeAddedByCellphone;

    @Column(name = "transaction_limit", columnDefinition = "default '40000.0000'")
    private BigDecimal transactionLimit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<AccountDTO> accounts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<FavoriteDTO> favorites;

    private String email;

    private String password;

    private String username;

    public UserDTO(){}

    public UserDTO(UserModel userModel) {
        setCanBeAddedByCellphone(userModel.isCanBeAddedByCellphone());
        setCellphone(userModel.getCellphone());
        setDirection(userModel.getDirection());
        setLastName(userModel.getLastName());
        setId(userModel.getId());
        setIdNumber(userModel.getIdNumber());
        setProfileImg(userModel.getProfileImg());
        setTransactionLimit(userModel.getTransactionLimit());
        setUsername(userModel.getUsername());
        setPassword(userModel.getPassword());
        setTelephone(userModel.getTelephone());
        setGivenName(userModel.getGivenName());
        setEmail(userModel.getEmail());
    }

    private List<AccountDTO> getAccounts(List<AccountModel> accountModels) {
        accounts = new ArrayList<>();

        for (AccountModel temp : accountModels) {
            // get accounts without the user model.
            accounts.add(new AccountDTO(temp));
        }

        return accounts;
    }

    public List<FavoriteDTO> getFavorites(List<FavoriteModel> favoriteModels) {
        favorites = new ArrayList<>();

        for (FavoriteModel temp : favoriteModels) {
            // get accounts without the user model.
            favorites.add(new FavoriteDTO(temp));
        }

        return favorites;
    }

    public List<FavoriteDTO> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<FavoriteDTO> favorites) {
        this.favorites = favorites;
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

    public void setGivenName(String giveName) {
        this.givenName = giveName;
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

    public BigDecimal getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(BigDecimal transactionLimit) {
        if(transactionLimit != null) {
            this.transactionLimit = transactionLimit;
        } else {
            this.transactionLimit = new BigDecimal("40000.0000");
        }
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

    public boolean isCanBeAddedByCellphone() {
        return canBeAddedByCellphone;
    }

    public void setCanBeAddedByCellphone(boolean canBeAddedByCellphone) {
        this.canBeAddedByCellphone = canBeAddedByCellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }
}
