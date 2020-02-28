package com.backend.bank.repository;

import com.backend.bank.dto.FavoriteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FavoriteRepository extends JpaRepository<FavoriteDTO, Integer> {

    FavoriteDTO save(FavoriteDTO accountDTO);

    @Transactional(readOnly = true)
    FavoriteDTO findOneById(int id);

}
