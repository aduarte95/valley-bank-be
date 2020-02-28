package com.backend.bank.repository;

import com.backend.bank.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDTO, Integer> {
    UserDTO findOneByUsername(String username);

    UserDTO save(UserDTO userDTO);

    boolean existsByUsername(String username);

    boolean existsByIdNumber(int idNumber);

    boolean existsByEmail(String email);

    boolean existsByCellphone(int cellphone);

    void deleteById(Integer id);

    UserDTO findOneById(Integer id);
}
