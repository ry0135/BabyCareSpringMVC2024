package com.example.repository;

import com.example.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsername(String username);
    Account findByUserID(String userID);
    Account findByUserIDAndCode(String userID, String code);
    boolean existsByUsername(String username);
    boolean existsByUserID(String userId);
    boolean existsByEmail(String email);
    List<Account> findAllByRole(int role); // Customize based on your needs
}