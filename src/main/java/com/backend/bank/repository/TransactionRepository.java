package com.backend.bank.repository;

import com.backend.bank.dto.TransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TransactionRepository extends JpaRepository<TransactionDTO, Integer> {

    TransactionDTO save(TransactionDTO transactionDTO);

    @Transactional(readOnly = true)
    TransactionDTO findOneById(Integer id);

}
