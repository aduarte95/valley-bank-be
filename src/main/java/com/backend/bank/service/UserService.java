package com.backend.bank.service;
import com.backend.bank.model.UserModel;

public interface UserService {
    public UserModel verifyUser(UserModel user);

    public UserModel save(UserModel user);

    public String update(UserModel user);

    public Boolean existsByUsername(String username);

    public Boolean existsByIdNumber(int idNumber);

    public Boolean existsByEmail(String email);

    public Boolean existsByCellphone(int cellphone);

    public boolean deleteUser(Integer id);

    UserModel findUserById(int id);

    boolean verifyPassword(UserModel userModel);
}
