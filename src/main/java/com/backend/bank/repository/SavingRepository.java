package com.backend.bank.repository;

import com.backend.bank.dto.SavingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SavingRepository extends JpaRepository<SavingDTO, Integer> {

    SavingDTO save(SavingDTO accountDTO);

    @Transactional(readOnly = true)
    SavingDTO findOneById(Integer id);

}
