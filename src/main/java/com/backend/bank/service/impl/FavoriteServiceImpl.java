package com.backend.bank.service.impl;

import com.backend.bank.dto.AccountDTO;
import com.backend.bank.dto.FavoriteDTO;
import com.backend.bank.dto.UserDTO;
import com.backend.bank.model.AccountModel;
import com.backend.bank.model.FavoriteModel;
import com.backend.bank.model.UserModel;
import com.backend.bank.repository.FavoriteRepository;
import com.backend.bank.service.FavoriteService;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    FavoriteRepository favoriteRepository;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public FavoriteModel save(FavoriteModel favoriteModel) {
        FavoriteDTO favoriteDTO = new FavoriteDTO(favoriteModel);
        favoriteDTO.setAccount(new AccountDTO(favoriteModel.getAccountModel()));
        favoriteDTO.setUser(new UserDTO(favoriteModel.getUserModel()));

        return new FavoriteModel(this.favoriteRepository.save(favoriteDTO));
    }

    @Override
    public FavoriteModel findOneById(Integer id) {
        FavoriteDTO favoriteDTO = favoriteRepository.findOneById(id);
        FavoriteModel favoriteModel = new FavoriteModel(favoriteDTO);
        favoriteModel.setAccountModel(new AccountModel(favoriteDTO.getAccount()));
        favoriteModel.setUserModel(new UserModel(favoriteDTO.getUser()));

        return favoriteModel;
    }

    @Override
    public FavoriteModel update(FavoriteModel favorite) {
        FavoriteDTO favoriteDTO = new FavoriteDTO(favorite);
        favoriteDTO.setAccount(new AccountDTO(favorite.getAccountModel()));
        favoriteDTO.setUser(new UserDTO(favorite.getUserModel()));



        return new FavoriteModel(this.favoriteRepository.save(favoriteDTO));
    }

    @Override
    public boolean deleteFavorite(Integer id) {
        boolean successful = true;

        try {
            this.favoriteRepository.deleteById(id);
        } catch (Exception e) {
            successful = false;
        }

        return successful;
    }
}
