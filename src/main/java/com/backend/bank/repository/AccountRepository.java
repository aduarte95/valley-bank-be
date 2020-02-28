package com.backend.bank.repository;

import com.backend.bank.dto.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountRepository extends JpaRepository<AccountDTO, Integer> {

    AccountDTO save(AccountDTO accountDTO);

    @Transactional(readOnly = true)
    AccountDTO findOneById(Integer id);

    AccountDTO findOneByAccountNumber(long accountNumber);
}
