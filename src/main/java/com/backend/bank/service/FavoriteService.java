package com.backend.bank.service;


import com.backend.bank.model.FavoriteModel;

public interface FavoriteService {
    FavoriteModel save(FavoriteModel favoriteModel);

    FavoriteModel findOneById(Integer id);

    FavoriteModel update(FavoriteModel accountModel);

    boolean deleteFavorite(Integer id);
}
