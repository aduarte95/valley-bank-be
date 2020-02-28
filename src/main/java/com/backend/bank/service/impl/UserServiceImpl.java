package com.backend.bank.service.impl;

import com.backend.bank.dto.UserDTO;
import com.backend.bank.model.AccountModel;
import com.backend.bank.model.UserModel;
import com.backend.bank.repository.UserRepository;
import com.backend.bank.service.UserService;
import org.springframework.stereotype.Service;

enum Duplicated {
    USERNAME,
    ID_NUMBER,
    EMAIL
};

enum Status {
    SUCCESS,
};

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel verifyUser(UserModel user) {
        UserDTO userDTO = this.userRepository.findOneByUsername(user.getUsername());
        UserModel response = null;

        if( userDTO != null) {
            if(userDTO.getPassword().equals(user.getPassword())) {
                response = new UserModel(userDTO);
                response.setPassword("");
                response.getAccounts(userDTO.getAccounts());
                response.getFavorites(userDTO.getFavorites());
            }
        }

        return response;
    }

    @Override
    public UserModel save(UserModel user) {
        UserModel userModel;

        try {
            userModel = new UserModel(this.userRepository.save(new UserDTO(user)));
        } catch (Exception e) {
            userModel = null;
        }

       return userModel;
    }

    @Override
    public String update(UserModel user) {
        if (existsByUsername(user.getUsername())) {
            if (existsByIdNumber(user.getIdNumber())) {
                if (existsByEmail(user.getEmail())) {
                    this.userRepository.save(new UserDTO(user));

                    return Status.SUCCESS.toString();
                } else {
                    return Duplicated.EMAIL.toString();
                }
            } else {
                return Duplicated.ID_NUMBER.toString();
            }
        } else {
            return Duplicated.USERNAME.toString();
        }
    }

    @Override
    public Boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByIdNumber(int idNumber) {
        return this.userRepository.existsByIdNumber(idNumber);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByCellphone(int cellphone) {
        return this.userRepository.existsByCellphone(cellphone);
    }

    @Override
    public boolean deleteUser(Integer id) {
        boolean successful = true;

        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            successful = false;
        }

        return successful;
    }

    @Override
    public UserModel findUserById(int id) {
        UserDTO userDTO = this.userRepository.findOneById(id);
        UserModel userModel = null;

        if (userDTO != null) {
            userModel = new UserModel(userDTO);
            userModel.getAccounts(userDTO.getAccounts());
            userModel.getFavorites(userDTO.getFavorites());
            userModel.setPassword("");
        }

        return userModel;
    }

    @Override
    public boolean verifyPassword(UserModel userModel) {
        UserDTO userDTO = this.userRepository.findOneById(userModel.getId());
        boolean samePassword = false;

        if(userDTO != null) {
            if(userDTO.getPassword().equals(userModel.getPassword())) {
                samePassword = true;
            }
        }

        return samePassword;
    }
}
